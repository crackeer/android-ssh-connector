/*
 * ConnectBot: simple, powerful, open-source SSH client for Android
 * Copyright 2026 Kenny Root
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.connectbot.service

import com.trilead.ssh2.SFTPv3Client
import com.trilead.ssh2.SFTPv3DirectoryEntry
import com.trilead.ssh2.SFTPv3FileAttributes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.InputStream
import java.io.OutputStream

/**
 * Represents a single remote file or directory entry.
 */
data class RemoteFile(
    val name: String,
    val path: String,
    val isDirectory: Boolean,
    val size: Long,
    val permissions: Int,
    val modifiedTime: Long,
)

/**
 * Wraps [SFTPv3Client] with suspend-friendly, progress-reporting operations.
 *
 * @param client The SFTP client to use for file operations.
 * @param execCommand Optional callback to execute a shell command on the remote host.
 *   When provided, directory deletion uses `rm -rf` (fast, single round-trip) instead
 *   of recursive SFTP operations (slow, one round-trip per file).
 */
class SftpManager(
    private val client: SFTPv3Client,
    private val execCommand: ((String) -> Int?)? = null,
) {

    companion object {
        private const val BUFFER_SIZE = 32 * 1024 // 32 KB
    }

    /**
     * List the contents of [remotePath], returning files sorted: directories first, then files.
     */
    suspend fun listDirectory(remotePath: String): List<RemoteFile> = withContext(Dispatchers.IO) {
        val entries = mutableListOf<RemoteFile>()
        try {
            val raw: List<SFTPv3DirectoryEntry> = client.ls(remotePath)
            for (entry in raw) {
                val name = entry.filename ?: continue
                if (name == "." || name == "..") continue
                val attrs: SFTPv3FileAttributes = entry.attributes ?: SFTPv3FileAttributes()
                val isDir = attrs.isDirectory
                val size = attrs.size ?: 0L
                val perms = attrs.permissions ?: 0
                val mtime = (attrs.mtime?.toLong() ?: 0L) * 1000L
                val fullPath = if (remotePath.endsWith("/")) "$remotePath$name" else "$remotePath/$name"
                entries.add(RemoteFile(name, fullPath, isDir, size, perms, mtime))
            }
        } catch (e: Exception) {
            Timber.e(e, "SFTP ls failed for $remotePath")
            throw e
        }
        entries.sortWith(compareByDescending<RemoteFile> { it.isDirectory }.thenBy { it.name.lowercase() })
        entries
    }

    /**
     * Delete a remote file or directory.
     *
     * For directories: if an [execCommand] callback is available, uses `rm -rf` for
     * a single-round-trip delete (fast). Otherwise falls back to recursive SFTP
     * operations (one network round-trip per file — slow for large directories).
     */
    suspend fun deleteFile(remotePath: String, isDirectory: Boolean) = withContext(Dispatchers.IO) {
        try {
            if (isDirectory) {
                val exec = execCommand
                if (exec != null) {
                    // Fast path: single shell command, no per-file round-trips
                    // Shell-quote the path to handle spaces and special characters
                    val quoted = remotePath.replace("'", "'\\''")
                    val exitCode = exec("rm -rf '$quoted'")
                    if (exitCode != null && exitCode != 0) {
                        throw java.io.IOException("rm -rf failed with exit code $exitCode")
                    }
                } else {
                    // Slow path: recursive SFTP (one round-trip per file)
                    deleteDirectoryRecursive(remotePath)
                }
            } else {
                client.rm(remotePath)
            }
        } catch (e: Exception) {
            Timber.e(e, "SFTP delete failed for $remotePath")
            throw e
        }
    }

    /**
     * Recursively delete a remote directory and all its contents.
     */
    private fun deleteDirectoryRecursive(remotePath: String) {
        @Suppress("UNCHECKED_CAST")
        val entries: List<SFTPv3DirectoryEntry> = client.ls(remotePath)
        for (entry in entries) {
            val name = entry.filename ?: continue
            if (name == "." || name == "..") continue
            val childPath = if (remotePath.endsWith("/")) "$remotePath$name" else "$remotePath/$name"
            val attrs = entry.attributes ?: SFTPv3FileAttributes()
            if (attrs.isDirectory) {
                deleteDirectoryRecursive(childPath)
            } else {
                client.rm(childPath)
            }
        }
        client.rmdir(remotePath)
    }

    /**
     * Download [remotePath] writing bytes into [outputStream].
     * Calls [onProgress] with (bytesTransferred, totalSize) periodically.
     * totalSize may be -1 if unknown.
     */
    suspend fun downloadFile(
        remotePath: String,
        totalSize: Long,
        outputStream: OutputStream,
        onProgress: suspend (transferred: Long, total: Long) -> Unit,
    ) = withContext(Dispatchers.IO) {
        val handle = client.openFileRO(remotePath)
        try {
            val buf = ByteArray(BUFFER_SIZE)
            var offset = 0L
            while (true) {
                val read = client.read(handle, offset, buf, 0, buf.size)
                if (read <= 0) break
                outputStream.write(buf, 0, read)
                offset += read
                withContext(Dispatchers.Main) { onProgress(offset, totalSize) }
            }
            outputStream.flush()
        } finally {
            client.closeFile(handle)
        }
    }

    /**
     * Upload bytes from [inputStream] to [remotePath].
     * Calls [onProgress] with (bytesTransferred, totalSize) periodically.
     */
    suspend fun uploadFile(
        remotePath: String,
        totalSize: Long,
        inputStream: InputStream,
        onProgress: suspend (transferred: Long, total: Long) -> Unit,
    ) = withContext(Dispatchers.IO) {
        val handle = client.createFileTruncate(remotePath)
        try {
            val buf = ByteArray(BUFFER_SIZE)
            var offset = 0L
            while (true) {
                val read = inputStream.read(buf)
                if (read <= 0) break
                client.write(handle, offset, buf, 0, read)
                offset += read
                withContext(Dispatchers.Main) { onProgress(offset, totalSize) }
            }
        } finally {
            client.closeFile(handle)
        }
    }

    /**
     * Returns the canonical home/working directory of the remote session.
     * Tries SFTP realpath first, falls back to "/" if unavailable.
     */
    suspend fun getWorkingDirectory(): String = withContext(Dispatchers.IO) {
        try {
            // realpath(".") is the standard SFTP way to get the home directory
            val path = client.canonicalPath(".")
            if (!path.isNullOrBlank()) path else "/"
        } catch (e: Exception) {
            Timber.w(e, "Could not get working directory via SFTP realpath, defaulting to /")
            "/"
        }
    }

    fun close() {
        try { client.close() } catch (_: Exception) {}
    }
}
