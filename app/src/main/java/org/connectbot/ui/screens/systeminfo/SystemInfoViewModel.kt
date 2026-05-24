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

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.connectbot.service.TerminalBridge
import org.connectbot.service.TerminalManager
import org.connectbot.transport.SSH
import org.connectbot.ui.navigation.NavArgs
import timber.log.Timber
import javax.inject.Inject

data class SystemInfo(
    val hostname: String = "",
    val osInfo: String = "",
    val kernelVersion: String = "",
    val cpuModel: String = "",
    val cpuCores: String = "",
    val memoryTotal: String = "",
    val memoryUsed: String = "",
    val memoryFree: String = "",
    val loadAverage: String = "",
    val uptime: String = "",
    val diskInfo: List<DiskInfo> = emptyList(),
    val topProcesses: List<ProcessInfo> = emptyList(),
)

data class DiskInfo(
    val filesystem: String,
    val size: String,
    val used: String,
    val available: String,
    val usePercent: String,
    val mountPoint: String,
)

data class ProcessInfo(
    val pid: String,
    val user: String,
    val cpuPercent: String,
    val memPercent: String,
    val command: String,
)

sealed class SystemInfoUiState {
    object Loading : SystemInfoUiState()
    data class Success(
        val systemInfo: SystemInfo,
        val isRefreshingProcesses: Boolean = false,
    ) : SystemInfoUiState()
    data class Error(val message: String) : SystemInfoUiState()
}

@HiltViewModel
class SystemInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val bridgeName: String = checkNotNull(savedStateHandle[NavArgs.BRIDGE_NAME])

    private val _uiState = MutableStateFlow<SystemInfoUiState>(SystemInfoUiState.Loading)
    val uiState: StateFlow<SystemInfoUiState> = _uiState.asStateFlow()

    private val _autoRefreshEnabled = MutableStateFlow(false)
    val autoRefreshEnabled: StateFlow<Boolean> = _autoRefreshEnabled.asStateFlow()

    private var terminalManager: TerminalManager? = null
    private var sshConnection: SSH? = null
    private var autoRefreshJob: Job? = null

    companion object {
        private const val AUTO_REFRESH_INTERVAL_MS = 5000L // 5 seconds
    }

    fun setTerminalManager(manager: TerminalManager) {
        if (terminalManager == manager) return
        terminalManager = manager
        loadSystemInfo()
    }

    fun refresh() {
        loadSystemInfo()
    }

    fun toggleAutoRefresh(enabled: Boolean) {
        _autoRefreshEnabled.value = enabled
        if (enabled) {
            startAutoRefresh()
        } else {
            stopAutoRefresh()
        }
    }

    private fun startAutoRefresh() {
        stopAutoRefresh() // Cancel any existing job
        autoRefreshJob = viewModelScope.launch {
            while (_autoRefreshEnabled.value) {
                delay(AUTO_REFRESH_INTERVAL_MS)
                if (_autoRefreshEnabled.value) {
                    refreshProcessesOnly()
                }
            }
        }
    }

    private fun stopAutoRefresh() {
        autoRefreshJob?.cancel()
        autoRefreshJob = null
    }

    private fun refreshProcessesOnly() {
        val currentState = _uiState.value
        if (currentState !is SystemInfoUiState.Success) return
        
        val ssh = sshConnection ?: return
        
        viewModelScope.launch {
            // Set refreshing flag
            _uiState.update { 
                if (it is SystemInfoUiState.Success) {
                    it.copy(isRefreshingProcesses = true)
                } else {
                    it
                }
            }
            
            try {
                val newProcesses = withContext(Dispatchers.IO) {
                    collectTopProcesses(ssh)
                }
                
                _uiState.update { state ->
                    if (state is SystemInfoUiState.Success) {
                        state.copy(
                            systemInfo = state.systemInfo.copy(topProcesses = newProcesses),
                            isRefreshingProcesses = false,
                        )
                    } else {
                        state
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "Failed to refresh processes")
                // Clear refreshing flag on error
                _uiState.update { 
                    if (it is SystemInfoUiState.Success) {
                        it.copy(isRefreshingProcesses = false)
                    } else {
                        it
                    }
                }
            }
        }
    }

    private fun loadSystemInfo() {
        val manager = terminalManager ?: return
        viewModelScope.launch {
            _uiState.value = SystemInfoUiState.Loading
            try {
                val info = withContext(Dispatchers.IO) {
                    collectSystemInfo(manager)
                }
                _uiState.value = SystemInfoUiState.Success(info)
            } catch (e: Exception) {
                Timber.e(e, "Failed to load system info")
                _uiState.value = SystemInfoUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private suspend fun collectSystemInfo(manager: TerminalManager): SystemInfo {
        // Find the host by nickname
        val hosts = manager.hostRepository.getHosts()
        val host = hosts.find { it.nickname == bridgeName }
            ?: throw IllegalStateException("Host not found: $bridgeName")
        
        // Create a direct SSH connection (not managed by TerminalManager)
        // Pass manager for authentication but no bridge to avoid tracking
        // Set wantSession to false since we only need to execute commands, not an interactive terminal
        Timber.d("Creating direct SSH connection for system info: $bridgeName")
        val hostForCommands = host.copy(wantSession = false)
        val ssh = SSH(hostForCommands, null, manager)
        sshConnection = ssh
        
        // Connect
        ssh.connect()
        
        // Wait for connection to establish
        var attempts = 0
        while (attempts < 50 && !ssh.isConnected() && !ssh.isSessionOpen()) {
            kotlinx.coroutines.delay(100)
            attempts++
        }
        
        if (!ssh.isConnected()) {
            throw IllegalStateException("Connection failed to $bridgeName")
        }

        // Collect system information using various commands
        val hostname = ssh.execCommandWithOutput("hostname")?.trim() ?: "Unknown"
        
        // OS information
        val osInfo = ssh.execCommandWithOutput("cat /etc/os-release 2>/dev/null | grep PRETTY_NAME | cut -d'\"' -f2")?.trim()
            ?: ssh.execCommandWithOutput("uname -s")?.trim()
            ?: "Unknown"
        
        // Kernel version
        val kernelVersion = ssh.execCommandWithOutput("uname -r")?.trim() ?: "Unknown"
        
        // CPU information
        val cpuModel = ssh.execCommandWithOutput("cat /proc/cpuinfo 2>/dev/null | grep 'model name' | head -1 | cut -d':' -f2")?.trim()
            ?: ssh.execCommandWithOutput("uname -m")?.trim()
            ?: "Unknown"
        
        val cpuCores = ssh.execCommandWithOutput("nproc 2>/dev/null")?.trim()
            ?: ssh.execCommandWithOutput("cat /proc/cpuinfo 2>/dev/null | grep processor | wc -l")?.trim()
            ?: "Unknown"
        
        // Memory information
        val memInfo = ssh.execCommandWithOutput("free -h 2>/dev/null | grep Mem")?.trim() ?: ""
        val memParts = memInfo.split(Regex("\\s+"))
        val memoryTotal = if (memParts.size > 1) memParts[1] else "Unknown"
        val memoryUsed = if (memParts.size > 2) memParts[2] else "Unknown"
        val memoryFree = if (memParts.size > 3) memParts[3] else "Unknown"
        
        // Load average
        val loadAverage = ssh.execCommandWithOutput("cat /proc/loadavg 2>/dev/null | awk '{print $1, $2, $3}'")?.trim()
            ?: ssh.execCommandWithOutput("uptime | grep -oP 'load average: \\K.*'")?.trim()
            ?: "Unknown"
        
        // Uptime
        val uptimeRaw = ssh.execCommandWithOutput("uptime -p 2>/dev/null")?.trim()
            ?: ssh.execCommandWithOutput("uptime | awk -F'up ' '{print $2}' | awk -F',' '{print $1}'")?.trim()
            ?: "Unknown"
        val uptime = uptimeRaw.removePrefix("up ")
        
        // Disk information
        val diskOutput = ssh.execCommandWithOutput("df -h 2>/dev/null | grep -E '^/dev/'")?.trim() ?: ""
        val diskInfo = parseDiskInfo(diskOutput)

        // Top 10 processes by CPU and Memory
        val topProcesses = collectTopProcesses(ssh)

        return SystemInfo(
            hostname = hostname,
            osInfo = osInfo,
            kernelVersion = kernelVersion,
            cpuModel = cpuModel,
            cpuCores = cpuCores,
            memoryTotal = memoryTotal,
            memoryUsed = memoryUsed,
            memoryFree = memoryFree,
            loadAverage = loadAverage,
            uptime = uptime,
            diskInfo = diskInfo,
            topProcesses = topProcesses,
        )
    }

    private suspend fun collectTopProcesses(transport: SSH): List<ProcessInfo> {
        // Use ps command to get top processes by CPU and memory
        // Get 12 lines (header + 11 processes) to ensure we have 10 after filtering out ps/awk
        // Format: PID USER %CPU %MEM COMMAND
        val psOutput = transport.execCommandWithOutput(
            "ps aux --sort=-%cpu,-%mem | head -12 | tail -11 | awk '{print $2\"|\"$1\"|\"$3\"|\"$4\"|\"$11}'"
        )?.trim()
        
        if (psOutput.isNullOrBlank()) {
            return emptyList()
        }
        
        return psOutput.lines().mapNotNull { line ->
            val parts = line.split("|")
            if (parts.size >= 5) {
                val command = parts[4]
                // Filter out ps and awk processes used for collecting the data
                if (command.contains("ps") || command.contains("awk")) {
                    return@mapNotNull null
                }
                ProcessInfo(
                    pid = parts[0],
                    user = parts[1],
                    cpuPercent = parts[2] + "%",
                    memPercent = parts[3] + "%",
                    command = command,
                )
            } else {
                null
            }
        }.take(10) // Ensure we return at most 10 processes
    }

    private fun parseDiskInfo(output: String): List<DiskInfo> {
        if (output.isBlank()) return emptyList()
        
        return output.lines().mapNotNull { line ->
            val parts = line.split(Regex("\\s+"))
            if (parts.size >= 6) {
                val filesystem = parts[0]
                // Filter out loop devices (e.g., /dev/loop0, /dev/loop1, etc.)
                if (filesystem.startsWith("/dev/loop")) {
                    return@mapNotNull null
                }
                DiskInfo(
                    filesystem = filesystem,
                    size = parts[1],
                    used = parts[2],
                    available = parts[3],
                    usePercent = parts[4],
                    mountPoint = parts[5],
                )
            } else {
                null
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Stop auto-refresh
        stopAutoRefresh()
        
        // Close the direct SSH connection on IO thread to avoid NetworkOnMainThreadException
        sshConnection?.let { ssh ->
            Timber.d("Closing direct SSH connection for system info: $bridgeName")
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    ssh.close()
                } catch (e: Exception) {
                    Timber.e(e, "Error closing SSH connection")
                }
            }
        }
        sshConnection = null
    }
}
