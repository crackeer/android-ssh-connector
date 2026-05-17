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

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.InsertDriveFile
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import org.connectbot.R
import org.connectbot.service.RemoteFile
import org.connectbot.ui.LocalTerminalManager
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileBrowserScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FileBrowserViewModel = hiltViewModel(),
) {
    val terminalManager = LocalTerminalManager.current
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(terminalManager) {
        terminalManager?.let { viewModel.setTerminalManager(it) }
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearError()
        }
    }

    // Multi-file picker for upload
    val uploadLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
    ) { uris ->
        if (uris.isNotEmpty()) viewModel.uploadFiles(uris)
    }

    var fileToDelete by remember { mutableStateOf<RemoteFile?>(null) }
    var fileToForceDelete by remember { mutableStateOf<RemoteFile?>(null) }

    // Determine if we can navigate up (not at root)
    val canNavigateUp = uiState.currentPath.isNotEmpty() && uiState.currentPath != "/"

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.button_back))
                    }
                },
                title = {
                    Column {
                        Text(
                            text = uiState.hostNickname.ifEmpty { stringResource(R.string.file_browser_title) },
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        if (uiState.currentPath.isNotEmpty()) {
                            Text(
                                text = uiState.currentPath,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                },
                actions = {
                    // Upload button only — navigate-up is now a list item
                    IconButton(
                        onClick = { uploadLauncher.launch(arrayOf("*/*")) },
                        enabled = !uiState.isLoading && uiState.currentPath.isNotEmpty(),
                    ) {
                        Icon(Icons.Default.Upload, stringResource(R.string.file_browser_upload))
                    }
                },
            )
        },
        modifier = modifier,
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            when {
                uiState.isLoading && uiState.files.isEmpty() && !canNavigateUp -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        // ".." navigate-up row — shown whenever we're not at root
                        if (canNavigateUp) {
                            item(key = "..") {
                                ListItem(
                                    leadingContent = {
                                        Icon(
                                            imageVector = Icons.Default.ArrowUpward,
                                            contentDescription = stringResource(R.string.file_browser_navigate_up),
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(24.dp),
                                        )
                                    },
                                    headlineContent = {
                                        Text(
                                            text = "..",
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = MaterialTheme.colorScheme.primary,
                                        )
                                    },
                                    modifier = Modifier.clickable { viewModel.navigateUp() },
                                )
                            }
                        }

                        if (uiState.isLoading) {
                            item(key = "loading") {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                                }
                            }
                        } else if (uiState.files.isEmpty()) {
                            item(key = "empty") {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(32.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = stringResource(R.string.file_browser_empty),
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                }
                            }
                        } else {
                            items(uiState.files, key = { it.path }) { file ->
                                RemoteFileItem(
                                    file = file,
                                    onNavigate = { if (file.isDirectory) viewModel.loadDirectory(file.path) },
                                    onDelete = { fileToDelete = file },
                                    onDownload = { viewModel.downloadFile(file) },
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Upload / Download progress overlay dialog
    if (uiState.transfers.isNotEmpty()) {
        val uploads = uiState.transfers.values.filter { it.isUpload }
        val downloads = uiState.transfers.values.filter { !it.isUpload }
        val hasActive = uploads.isNotEmpty() || downloads.isNotEmpty()
        if (hasActive) {
            Dialog(
                onDismissRequest = { /* non-dismissible while transferring */ },
                properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = MaterialTheme.shapes.large,
                        )
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    if (uploads.isNotEmpty()) {
                        Text(
                            text = stringResource(R.string.file_browser_uploading),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        uploads.forEach { t -> TransferProgressRow(t) { viewModel.cancelTransfer(t.key) } }
                    }
                    if (downloads.isNotEmpty()) {
                        Text(
                            text = stringResource(R.string.file_browser_downloading),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        downloads.forEach { t -> TransferProgressRow(t) { viewModel.cancelTransfer(t.key) } }
                    }
                }
            }
        }
    }

    // Delete confirmation dialog (first step)
    fileToDelete?.let { file ->
        AlertDialog(
            onDismissRequest = { fileToDelete = null },
            title = { Text(stringResource(R.string.file_browser_delete_title)) },
            text = {
                Text(
                    stringResource(
                        if (file.isDirectory) R.string.file_browser_delete_dir_confirm
                        else R.string.file_browser_delete_confirm,
                        file.name,
                    ),
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    fileToDelete = null
                    if (isSystemPath(file.path)) {
                        // Dangerous system path — require a second confirmation
                        fileToForceDelete = file
                    } else {
                        viewModel.deleteFile(file)
                    }
                }) { Text(stringResource(R.string.button_yes)) }
            },
            dismissButton = {
                TextButton(onClick = { fileToDelete = null }) {
                    Text(stringResource(R.string.button_no))
                }
            },
        )
    }

    // Second confirmation for dangerous system paths
    fileToForceDelete?.let { file ->
        AlertDialog(
            onDismissRequest = { fileToForceDelete = null },
            title = {
                Text(
                    text = stringResource(R.string.file_browser_delete_system_title),
                    color = MaterialTheme.colorScheme.error,
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(R.string.file_browser_delete_system_warning),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = stringResource(R.string.file_browser_delete_system_confirm, file.path),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteFile(file)
                    fileToForceDelete = null
                }) {
                    Text(
                        text = stringResource(R.string.file_browser_delete_system_proceed),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { fileToForceDelete = null }) {
                    Text(stringResource(R.string.button_cancel))
                }
            },
        )
    }

    // Deleting progress dialog
    if (uiState.isDeleting) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.large,
                    )
                    .padding(24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CircularProgressIndicator(modifier = Modifier.size(32.dp))
                Text(
                    text = stringResource(R.string.file_browser_deleting),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Composable
private fun RemoteFileItem(
    file: RemoteFile,
    onNavigate: () -> Unit,
    onDelete: () -> Unit,
    onDownload: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showMenu by remember { mutableStateOf(false) }

    ListItem(
        leadingContent = {
            Icon(
                imageVector = if (file.isDirectory) Icons.Default.Folder else Icons.Default.InsertDriveFile,
                contentDescription = null,
                tint = if (file.isDirectory) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp),
            )
        },
        headlineContent = {
            Text(file.name, maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        supportingContent = {
            val parts = buildList {
                if (!file.isDirectory) add(formatFileSize(file.size))
                if (file.modifiedTime > 0L) add(formatModifiedTime(file.modifiedTime))
            }
            if (parts.isNotEmpty()) {
                Text(parts.joinToString("  ·  "), style = MaterialTheme.typography.bodySmall)
            }
        },
        trailingContent = {
            Box {
                IconButton(onClick = { showMenu = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = stringResource(R.string.button_more_options))
                }
                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                    if (!file.isDirectory) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.file_browser_download)) },
                            leadingIcon = { Icon(Icons.Default.Download, null) },
                            onClick = { showMenu = false; onDownload() },
                        )
                    }
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.file_browser_delete)) },
                        leadingIcon = { Icon(Icons.Default.Delete, null) },
                        onClick = { showMenu = false; onDelete() },
                    )
                }
            }
        },
        modifier = modifier.clickable(onClick = onNavigate),
    )
}

@Composable
private fun TransferProgressRow(t: TransferProgress, onCancel: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = t.fileName,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = if (t.progress < 0f) "…" else "${(t.progress * 100).toInt()}%",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
            TextButton(
                onClick = onCancel,
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 0.dp),
            ) {
                Text(
                    text = stringResource(R.string.button_cancel),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }
        if (t.progress < 0f) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        } else {
            LinearProgressIndicator(
                progress = { t.progress },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

private fun formatFileSize(bytes: Long): String {
    if (bytes < 0) return ""
    if (bytes < 1024) return "$bytes B"
    val kb = bytes / 1024.0
    if (kb < 1024) return "${DecimalFormat("#.#").format(kb)} KB"
    val mb = kb / 1024.0
    if (mb < 1024) return "${DecimalFormat("#.#").format(mb)} MB"
    return "${DecimalFormat("#.#").format(mb / 1024.0)} GB"
}

private fun formatModifiedTime(epochMs: Long): String {
    if (epochMs <= 0L) return ""
    val now = java.util.Date()
    val date = java.util.Date(epochMs)
    val cal = java.util.Calendar.getInstance()

    cal.time = now
    val todayYear = cal.get(java.util.Calendar.YEAR)
    val todayDay = cal.get(java.util.Calendar.DAY_OF_YEAR)

    cal.time = date
    val fileYear = cal.get(java.util.Calendar.YEAR)
    val fileDay = cal.get(java.util.Calendar.DAY_OF_YEAR)

    return when {
        // Today — show time only
        fileYear == todayYear && fileDay == todayDay ->
            java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault()).format(date)
        // This year — show month and day
        fileYear == todayYear ->
            java.text.SimpleDateFormat("MMM dd", java.util.Locale.getDefault()).format(date)
        // Older — show full date
        else ->
            java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(date)
    }
}

/**
 * Returns true if [path] is a Linux system directory that should require
 * an extra confirmation before deletion.
 *
 * Matches exact root-level system directories (e.g. /etc, /bin) and their
 * direct children (e.g. /etc/passwd), but not deeper paths like /home/user/etc.
 */
private fun isSystemPath(path: String): Boolean {
    val systemDirs = setOf(
        "bin", "boot", "dev", "etc", "lib", "lib32", "lib64", "libx32",
        "proc", "root", "run", "sbin", "srv", "sys", "tmp", "usr", "var",
    )
    // Normalise: strip trailing slash, split into segments
    val parts = path.trimEnd('/').split('/').filter { it.isNotEmpty() }
    // Only flag paths that are at the root level: /etc  or one level deep: /etc/passwd
    return parts.size in 1..2 && parts.first() in systemDirs
}
