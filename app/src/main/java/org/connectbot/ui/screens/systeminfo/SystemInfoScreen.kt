/*
 * ConnectBot: simple, powerful, open-source SSH client for Android
 * Copyright 2025 Kenny Root
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

package org.connectbot.ui.screens.systeminfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import org.connectbot.R
import org.connectbot.ui.LocalTerminalManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SystemInfoScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SystemInfoViewModel = hiltViewModel(),
) {
    val terminalManager = LocalTerminalManager.current
    val uiState by viewModel.uiState.collectAsState()
    val autoRefreshEnabled by viewModel.autoRefreshEnabled.collectAsState()

    LaunchedEffect(terminalManager) {
        terminalManager?.let { viewModel.setTerminalManager(it) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.system_info_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.button_back),
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.refresh() },
                        enabled = uiState !is SystemInfoUiState.Loading,
                    ) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = stringResource(R.string.system_info_refresh),
                        )
                    }
                },
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (val state = uiState) {
                is SystemInfoUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is SystemInfoUiState.Success -> {
                    SystemInfoContent(
                        systemInfo = state.systemInfo,
                        autoRefreshEnabled = autoRefreshEnabled,
                        isRefreshingProcesses = state.isRefreshingProcesses,
                        onAutoRefreshToggle = { viewModel.toggleAutoRefresh(it) },
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                is SystemInfoUiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = stringResource(R.string.system_info_error, state.message),
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SystemInfoContent(
    systemInfo: SystemInfo,
    autoRefreshEnabled: Boolean,
    isRefreshingProcesses: Boolean,
    onAutoRefreshToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // System Overview Card
        InfoCard(title = stringResource(R.string.system_info_overview)) {
            InfoRow(
                label = stringResource(R.string.system_info_hostname),
                value = systemInfo.hostname,
            )
            InfoRow(
                label = stringResource(R.string.system_info_os),
                value = systemInfo.osInfo,
            )
            InfoRow(
                label = stringResource(R.string.system_info_kernel),
                value = systemInfo.kernelVersion,
            )
            InfoRow(
                label = stringResource(R.string.system_info_uptime),
                value = systemInfo.uptime,
            )
        }

        // CPU Card
        InfoCard(title = stringResource(R.string.system_info_cpu)) {
            InfoRow(
                label = stringResource(R.string.system_info_cpu_model),
                value = systemInfo.cpuModel,
            )
            InfoRow(
                label = stringResource(R.string.system_info_cpu_cores),
                value = systemInfo.cpuCores,
            )
            InfoRow(
                label = stringResource(R.string.system_info_load_average),
                value = systemInfo.loadAverage,
            )
        }

        // Memory Card
        InfoCard(title = stringResource(R.string.system_info_memory)) {
            InfoRow(
                label = stringResource(R.string.system_info_memory_total),
                value = systemInfo.memoryTotal,
            )
            InfoRow(
                label = stringResource(R.string.system_info_memory_used),
                value = systemInfo.memoryUsed,
            )
            InfoRow(
                label = stringResource(R.string.system_info_memory_free),
                value = systemInfo.memoryFree,
            )
        }

        // Disk Card
        if (systemInfo.diskInfo.isNotEmpty()) {
            InfoCard(title = stringResource(R.string.system_info_disk)) {
                systemInfo.diskInfo.forEachIndexed { index, disk ->
                    if (index > 0) {
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = disk.mountPoint,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                        )
                        InfoRow(
                            label = stringResource(R.string.system_info_disk_filesystem),
                            value = disk.filesystem,
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            InfoRow(
                                label = stringResource(R.string.system_info_disk_size),
                                value = disk.size,
                                modifier = Modifier.weight(1f),
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            InfoRow(
                                label = stringResource(R.string.system_info_disk_used),
                                value = "${disk.used} (${disk.usePercent})",
                                modifier = Modifier.weight(1f),
                            )
                        }
                        InfoRow(
                            label = stringResource(R.string.system_info_disk_available),
                            value = disk.available,
                        )
                    }
                }
            }
        }

        // Top Processes Card
        if (systemInfo.topProcesses.isNotEmpty()) {
            InfoCard(
                title = stringResource(R.string.system_info_top_processes),
                headerAction = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        if (isRefreshingProcesses) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                strokeWidth = 2.dp,
                            )
                        }
                        Text(
                            text = stringResource(R.string.system_info_auto_refresh),
                            style = MaterialTheme.typography.bodySmall,
                        )
                        Switch(
                            checked = autoRefreshEnabled,
                            onCheckedChange = onAutoRefreshToggle,
                        )
                    }
                },
            ) {
                systemInfo.topProcesses.forEachIndexed { index, process ->
                    if (index > 0) {
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = process.command,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            InfoRow(
                                label = stringResource(R.string.system_info_process_pid),
                                value = process.pid,
                                modifier = Modifier.weight(1f),
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            InfoRow(
                                label = stringResource(R.string.system_info_process_user),
                                value = process.user,
                                modifier = Modifier.weight(1f),
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            InfoRow(
                                label = stringResource(R.string.system_info_process_cpu),
                                value = process.cpuPercent,
                                modifier = Modifier.weight(1f),
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            InfoRow(
                                label = stringResource(R.string.system_info_process_mem),
                                value = process.memPercent,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoCard(
    title: String,
    modifier: Modifier = Modifier,
    headerAction: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
                headerAction?.invoke()
            }
            Spacer(modifier = Modifier.height(4.dp))
            content()
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f),
        )
    }
}
