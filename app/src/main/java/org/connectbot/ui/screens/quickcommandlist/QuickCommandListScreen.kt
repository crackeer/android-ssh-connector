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

package org.connectbot.ui.screens.quickcommandlist

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import kotlinx.coroutines.launch
import org.connectbot.R
import org.connectbot.data.entity.QuickCommand

/**
 * Commands list tab content.
 *
 * This composable does NOT include its own Scaffold or FAB — it is designed to be
 * embedded inside the HostListScreen's Scaffold. The outer screen's FAB triggers
 * the "add command" dialog via [onFabTriggerReady]. The overflow menu actions
 * (export/import) are surfaced via [onExtraMenuItems].
 *
 * @param onFabTriggerReady Called once with a lambda that the outer FAB should invoke
 *   to open the "add command" dialog.
 * @param onExtraMenuItems Called with lambdas for export and import so the outer
 *   toolbar menu can include them.
 */
@Composable
fun QuickCommandListScreen(
    modifier: Modifier = Modifier,
    onFabTriggerReady: (trigger: () -> Unit) -> Unit = {},
    onExtraMenuItems: (onExport: () -> Unit, onImport: () -> Unit) -> Unit = { _, _ -> },
    viewModel: QuickCommandListViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    // File picker for export
    val exportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/json"),
    ) { uri ->
        if (uri != null && uiState.exportedJson != null) {
            scope.launch {
                try {
                    context.contentResolver.openOutputStream(uri)?.use { out ->
                        out.write(uiState.exportedJson!!.toByteArray())
                    }
                    val count = uiState.exportResult?.commandCount ?: 0
                    Toast.makeText(
                        context,
                        context.getString(R.string.export_commands_success, count),
                        Toast.LENGTH_SHORT,
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.export_commands_failed, e.message),
                        Toast.LENGTH_LONG,
                    ).show()
                }
                viewModel.clearExportedJson()
            }
        } else {
            viewModel.clearExportedJson()
        }
    }

    // File picker for import
    val importLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
    ) { uri ->
        if (uri != null) {
            scope.launch {
                try {
                    val json = context.contentResolver.openInputStream(uri)?.use { it.bufferedReader().readText() }
                    if (json != null) viewModel.importCommands(json)
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.import_commands_failed, e.message),
                        Toast.LENGTH_LONG,
                    ).show()
                }
            }
        }
    }

    // Show errors as Toast
    LaunchedEffect(uiState.error) {
        uiState.error?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            viewModel.clearError()
        }
    }

    // Launch file picker when JSON is ready
    LaunchedEffect(uiState.exportedJson) {
        if (uiState.exportedJson != null) {
            exportLauncher.launch(context.getString(R.string.export_commands_filename))
        }
    }

    // Show import result toast
    LaunchedEffect(uiState.importResult) {
        uiState.importResult?.let { result ->
            Toast.makeText(
                context,
                context.getString(R.string.import_commands_success, result.imported, result.skipped),
                Toast.LENGTH_SHORT,
            ).show()
            viewModel.clearImportResult()
        }
    }

    QuickCommandListContent(
        uiState = uiState,
        onFabTriggerReady = onFabTriggerReady,
        onExtraMenuItems = { onExport, onImport ->
            onExtraMenuItems(onExport, onImport)
        },
        onSaveCommand = { label, command, existingId, sortOrder ->
            viewModel.saveCommand(label, command, existingId, sortOrder)
        },
        onDeleteCommand = viewModel::deleteCommand,
        onExportCommands = viewModel::exportCommands,
        onImportCommands = { importLauncher.launch(arrayOf("application/json")) },
        modifier = modifier,
    )
}

@Composable
fun QuickCommandListContent(
    uiState: QuickCommandListUiState,
    onSaveCommand: (label: String, command: String, existingId: Long, sortOrder: Int) -> Unit,
    onDeleteCommand: (QuickCommand) -> Unit,
    onExportCommands: () -> Unit,
    onImportCommands: () -> Unit,
    modifier: Modifier = Modifier,
    onFabTriggerReady: (trigger: () -> Unit) -> Unit = {},
    onExtraMenuItems: (onExport: () -> Unit, onImport: () -> Unit) -> Unit = { _, _ -> },
) {
    var showEditorDialog by remember { mutableStateOf(false) }
    var editingCommand by remember { mutableStateOf<QuickCommand?>(null) }

    // Expose the "open add dialog" trigger to the parent FAB
    SideEffect {
        onFabTriggerReady {
            editingCommand = null
            showEditorDialog = true
        }
        onExtraMenuItems(onExportCommands, onImportCommands)
    }

    Box(modifier = modifier) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            uiState.commands.isEmpty() -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(R.string.quick_command_empty),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                    TextButton(onClick = {
                        editingCommand = null
                        showEditorDialog = true
                    }) {
                        Text(stringResource(R.string.quick_command_add))
                    }
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 104.dp,
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(
                        items = uiState.commands,
                        key = { it.id },
                    ) { cmd ->
                        QuickCommandItem(
                            command = cmd,
                            onEdit = {
                                editingCommand = cmd
                                showEditorDialog = true
                            },
                            onDelete = { onDeleteCommand(cmd) },
                        )
                    }
                }
            }
        }
    }

    if (showEditorDialog) {
        QuickCommandEditorDialog(
            existing = editingCommand,
            onDismiss = { showEditorDialog = false },
            onConfirm = { label, command ->
                onSaveCommand(
                    label,
                    command,
                    editingCommand?.id ?: 0L,
                    editingCommand?.sortOrder ?: 0,
                )
                showEditorDialog = false
            },
        )
    }
}

@Composable
private fun QuickCommandItem(
    command: QuickCommand,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showMenu by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    ListItem(
        headlineContent = { Text(command.label) },
        supportingContent = {
            Text(
                text = command.command,
                maxLines = 2,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
        trailingContent = {
            Box {
                IconButton(onClick = { showMenu = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = stringResource(R.string.button_more_options))
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false },
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.quick_command_edit)) },
                        onClick = {
                            showMenu = false
                            onEdit()
                        },
                        leadingIcon = { Icon(Icons.Default.Edit, null) },
                    )
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.quick_command_delete)) },
                        onClick = {
                            showMenu = false
                            showDeleteDialog = true
                        },
                        leadingIcon = { Icon(Icons.Default.Delete, null) },
                    )
                }
            }
        },
        modifier = modifier,
    )

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            text = { Text(stringResource(R.string.quick_command_delete_confirm, command.label)) },
            confirmButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    onDelete()
                }) {
                    Text(stringResource(R.string.button_yes))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text(stringResource(R.string.button_no))
                }
            },
        )
    }
}

@Composable
internal fun QuickCommandEditorDialog(
    existing: QuickCommand?,
    onDismiss: () -> Unit,
    onConfirm: (label: String, command: String) -> Unit,
) {
    var label by remember(existing) { mutableStateOf(existing?.label ?: "") }
    var command by remember(existing) { mutableStateOf(existing?.command ?: "") }
    var labelError by remember { mutableStateOf(false) }
    var commandError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                if (existing == null) {
                    stringResource(R.string.quick_command_add)
                } else {
                    stringResource(R.string.quick_command_edit)
                },
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = label,
                    onValueChange = {
                        label = it
                        labelError = false
                    },
                    label = { Text(stringResource(R.string.quick_command_label)) },
                    isError = labelError,
                    supportingText = if (labelError) {
                        { Text(stringResource(R.string.quick_command_label_required)) }
                    } else {
                        null
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = command,
                    onValueChange = {
                        command = it
                        commandError = false
                    },
                    label = { Text(stringResource(R.string.quick_command_command)) },
                    isError = commandError,
                    supportingText = if (commandError) {
                        { Text(stringResource(R.string.quick_command_command_required)) }
                    } else {
                        null
                    },
                    minLines = 2,
                    maxLines = 5,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                labelError = label.isBlank()
                commandError = command.isBlank()
                if (!labelError && !commandError) {
                    onConfirm(label.trim(), command)
                }
            }) {
                Text(stringResource(R.string.button_save))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.button_cancel))
            }
        },
    )
}
