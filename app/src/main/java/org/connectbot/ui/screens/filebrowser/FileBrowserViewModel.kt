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

package org.connectbot.ui.screens.filebrowser

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.connectbot.di.CoroutineDispatchers
import org.connectbot.service.RemoteFile
import org.connectbot.service.SftpManager
import org.connectbot.service.TerminalManager
import org.connectbot.transport.SSH
import timber.log.Timber
import java.io.File
import javax.inject.Inject

/** Per-file transfer progress (0f..1f, or -1f = indeterminate). */
data class TransferProgress(
    val fileName: String,
    val progress: Float,
    val isUpload: Boolean,
    /** Unique key matching the map key in uiState.transfers, used to cancel. */
    val key: String,
)

data class FileBrowserUiState(
    val isLoading: Boolean = true,
    val currentPath: String = "",
    val hostNickname: String = "",
    val files: List<RemoteFile> = emptyList(),
    val error: String? = null,
    /** Active transfers keyed by remote path. */
    val transfers: Map<String, TransferProgress> = emptyMap(),
    /** True while a delete operation is in progress. */
    val isDeleting: Boolean = false,
)

@HiltViewModel
class FileBrowserViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {

    private val bridgeName: String = savedStateHandle.get<String>("bridgeName") ?: ""

    private val _uiState = MutableStateFlow(FileBrowserUiState())
    val uiState: StateFlow<FileBrowserUiState> = _uiState.asStateFlow()

    private var sftpManager: SftpManager? = null
    private var terminalManager: TerminalManager? = null

    /** Active transfer jobs keyed by remote path, used for cancellation. */
    private val transferJobs = mutableMapOf<String, kotlinx.coroutines.Job>()

    /**
     * Rebuild the SFTPv3Client after a transfer error or cancellation.
     *
     * The SFTP protocol is stateful: a mid-stream cancel or I/O error leaves
     * unread bytes in the channel buffer. Any subsequent SFTP operation will
     * read those stale bytes as a packet header and throw "illegal sftp packet len".
     * The only safe recovery is to discard the old client and open a fresh one
     * on the same SSH Connection.
     *
     * Must be called from the IO dispatcher.
     */
    private fun resetSftpClient() {
        val manager = terminalManager ?: return
        val bridge = manager.bridgesFlow.value.find { it.host.nickname == bridgeName } ?: return
        val ssh = bridge.transport as? SSH ?: return
        try {
            val newClient = ssh.openSftpClient() ?: return
            sftpManager = SftpManager(
                client = newClient,
                execCommand = { cmd -> ssh.execCommand(cmd) },
            )
        } catch (e: Exception) {
            Timber.w(e, "Failed to reset SFTP client")
        }
    }

    /** SharedPreferences used to persist the last visited directory per host. */
    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    /** Returns the pref key for the last directory of this host. */
    private val lastDirKey: String get() = "last_dir_${bridgeName.replace(' ', '_')}"

    /** Save [path] as the last visited directory for this host. */
    private fun saveLastDirectory(path: String) {
        prefs.edit().putString(lastDirKey, path).apply()
    }

    /** Returns the last visited directory for this host, or null if none saved. */
    private fun loadLastDirectory(): String? = prefs.getString(lastDirKey, null)

    fun setTerminalManager(manager: TerminalManager) {
        if (terminalManager == manager) return
        terminalManager = manager
        initSftp(manager)
    }

    private fun initSftp(manager: TerminalManager) {
        viewModelScope.launch(dispatchers.io) {
            val bridge = manager.bridgesFlow.value.find { it.host.nickname == bridgeName }
            if (bridge == null) {
                _uiState.update { it.copy(isLoading = false, error = "Connection not found: $bridgeName") }
                return@launch
            }
            val ssh = bridge.transport as? SSH
            if (ssh == null) {
                _uiState.update { it.copy(isLoading = false, error = "SFTP requires an SSH connection") }
                return@launch
            }
            val client = try {
                ssh.openSftpClient()
            } catch (e: Exception) {
                Timber.e(e, "Failed to open SFTP client")
                _uiState.update { it.copy(isLoading = false, error = "Could not open SFTP session: ${e.message}") }
                return@launch
            }
            if (client == null) {
                _uiState.update { it.copy(isLoading = false, error = "Could not open SFTP session") }
                return@launch
            }
            val mgr = SftpManager(
                client = client,
                execCommand = { cmd -> ssh.execCommand(cmd) },
            )
            sftpManager = mgr
            try {
                // Use the last visited directory if available, otherwise use the home directory
                val savedDir = loadLastDirectory()
                val startDir = if (!savedDir.isNullOrBlank()) {
                    // Verify the saved directory still exists; fall back to home if not
                    try {
                        mgr.listDirectory(savedDir)
                        savedDir
                    } catch (e: Exception) {
                        Timber.w(e, "Saved directory $savedDir no longer accessible, falling back to home")
                        mgr.getWorkingDirectory()
                    }
                } else {
                    mgr.getWorkingDirectory()
                }
                _uiState.update { it.copy(hostNickname = bridge.host.nickname) }
                loadDirectory(startDir)
            } catch (e: Exception) {
                Timber.e(e, "SFTP init failed")
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        hostNickname = bridge.host.nickname,
                        error = "SFTP error: ${e.message}",
                    )
                }
            }
        }
    }

    fun loadDirectory(path: String) {
        viewModelScope.launch(dispatchers.io) {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val files = sftpManager?.listDirectory(path) ?: emptyList()
                _uiState.update { it.copy(isLoading = false, currentPath = path, files = files) }
                saveLastDirectory(path)
            } catch (e: Exception) {
                // If listing fails due to a corrupted SFTP state, reset the client
                resetSftpClient()
                _uiState.update { it.copy(isLoading = false, error = e.message ?: "Failed to list directory") }
            }
        }
    }

    fun navigateUp() {
        val current = _uiState.value.currentPath
        if (current == "/" || current.isEmpty()) return
        val parent = current.trimEnd('/').substringBeforeLast('/').ifEmpty { "/" }
        loadDirectory(parent)
    }

    fun deleteFile(file: RemoteFile) {
        viewModelScope.launch(dispatchers.io) {
            _uiState.update { it.copy(isDeleting = true, error = null) }
            try {
                sftpManager?.deleteFile(file.path, file.isDirectory)
                loadDirectory(_uiState.value.currentPath)
            } catch (e: Exception) {
                resetSftpClient()
                _uiState.update { it.copy(error = e.message ?: "Delete failed") }
            } finally {
                _uiState.update { it.copy(isDeleting = false) }
            }
        }
    }

    fun downloadFile(file: RemoteFile) {
        val key = file.path
        // Show indeterminate progress immediately
        _uiState.update { state ->
            state.copy(transfers = state.transfers + (key to TransferProgress(file.name, -1f, false, key)))
        }
        val job = viewModelScope.launch(dispatchers.io) {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            downloadsDir.mkdirs()
            val destFile = File(downloadsDir, file.name)
            try {
                destFile.outputStream().use { out ->
                    sftpManager?.downloadFile(
                        remotePath = file.path,
                        totalSize = file.size,
                        outputStream = out,
                        onProgress = { transferred, total ->
                            val progress = if (total > 0) transferred.toFloat() / total else -1f
                            _uiState.update { state ->
                                state.copy(
                                    transfers = state.transfers + (key to TransferProgress(file.name, progress, false, key)),
                                )
                            }
                        },
                    )
                }
            } catch (e: kotlinx.coroutines.CancellationException) {
                destFile.delete()
                // Cancelled by user — reset SFTP client so next operation is clean
                resetSftpClient()
            } catch (e: Exception) {
                destFile.delete()
                resetSftpClient()
                _uiState.update { state -> state.copy(error = "Download failed: ${e.message}") }
            } finally {
                transferJobs.remove(key)
                _uiState.update { state -> state.copy(transfers = state.transfers - key) }
            }
        }
        transferJobs[key] = job
    }

    fun uploadFiles(uris: List<Uri>) {
        for (uri in uris) {
            val fileName = resolveFileName(uri) ?: continue
            val key = "upload_${System.nanoTime()}_$fileName"
            val remotePath = buildRemotePath(_uiState.value.currentPath, fileName)
            val size = resolveFileSize(uri)
            // Show indeterminate progress immediately
            _uiState.update { state ->
                state.copy(transfers = state.transfers + (key to TransferProgress(fileName, -1f, true, key)))
            }
            val job = viewModelScope.launch(dispatchers.io) {
                try {
                    context.contentResolver.openInputStream(uri)?.use { input ->
                        sftpManager?.uploadFile(
                            remotePath = remotePath,
                            totalSize = size,
                            inputStream = input,
                            onProgress = { transferred, total ->
                                val progress = if (total > 0) transferred.toFloat() / total else -1f
                                _uiState.update { state ->
                                    state.copy(
                                        transfers = state.transfers + (key to TransferProgress(fileName, progress, true, key)),
                                    )
                                }
                            },
                        )
                    }
                    loadDirectory(_uiState.value.currentPath)
                } catch (e: kotlinx.coroutines.CancellationException) {
                    // Cancelled by user — try to remove partial remote file silently
                    try { sftpManager?.deleteFile(remotePath, false) } catch (_: Exception) {}
                    resetSftpClient()
                } catch (e: Exception) {
                    resetSftpClient()
                    _uiState.update { state ->
                        state.copy(error = "Upload failed ($fileName): ${e.message}")
                    }
                } finally {
                    transferJobs.remove(key)
                    _uiState.update { state -> state.copy(transfers = state.transfers - key) }
                }
            }
            transferJobs[key] = job
        }
    }

    /** Cancel a specific transfer by its key. */
    fun cancelTransfer(key: String) {
        transferJobs[key]?.cancel()
    }

    fun clearError() = _uiState.update { it.copy(error = null) }

    private fun resolveFileName(uri: Uri): String? {
        var name: String? = null
        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val idx = cursor.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
            if (cursor.moveToFirst() && idx >= 0) name = cursor.getString(idx)
        }
        return name ?: uri.lastPathSegment
    }

    private fun resolveFileSize(uri: Uri): Long {
        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val idx = cursor.getColumnIndex(android.provider.OpenableColumns.SIZE)
            if (cursor.moveToFirst() && idx >= 0) return cursor.getLong(idx)
        }
        return -1L
    }

    private fun buildRemotePath(dir: String, name: String): String =
        if (dir.endsWith("/")) "$dir$name" else "$dir/$name"

    override fun onCleared() {
        super.onCleared()
        // Cancel any in-flight transfers
        transferJobs.values.forEach { it.cancel() }
        transferJobs.clear()
        // Do NOT call sftpManager.close() here — see comment above.
        sftpManager = null
    }

    companion object {
        private const val PREFS_NAME = "file_browser_prefs"
    }
}
