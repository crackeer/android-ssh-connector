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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.connectbot.data.CommandExportCounts
import org.connectbot.data.CommandImportCounts
import org.connectbot.data.QuickCommandRepository
import org.connectbot.data.entity.QuickCommand
import org.connectbot.di.CoroutineDispatchers
import javax.inject.Inject

data class QuickCommandListUiState(
    val commands: List<QuickCommand> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    /** Non-null when JSON is ready to be written to a file. */
    val exportedJson: String? = null,
    val exportResult: CommandExportCounts? = null,
    val importResult: CommandImportCounts? = null,
)

@HiltViewModel
class QuickCommandListViewModel @Inject constructor(
    private val repository: QuickCommandRepository,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuickCommandListUiState())
    val uiState: StateFlow<QuickCommandListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.observeAll().collect { commands ->
                _uiState.update { it.copy(commands = commands, isLoading = false) }
            }
        }
    }

    fun saveCommand(label: String, command: String, existingId: Long = 0L, sortOrder: Int = 0) {
        viewModelScope.launch {
            try {
                val entity = QuickCommand(
                    id = existingId,
                    label = label.trim(),
                    command = command,
                    sortOrder = sortOrder,
                )
                withContext(dispatchers.io) { repository.save(entity) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message ?: "Failed to save command") }
            }
        }
    }

    fun deleteCommand(command: QuickCommand) {
        viewModelScope.launch {
            try {
                withContext(dispatchers.io) { repository.delete(command) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message ?: "Failed to delete command") }
            }
        }
    }

    fun exportCommands() {
        viewModelScope.launch {
            try {
                val (json, counts) = withContext(dispatchers.io) { repository.exportToJson() }
                _uiState.update { it.copy(exportedJson = json, exportResult = counts) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message ?: "Failed to export commands") }
            }
        }
    }

    fun importCommands(jsonString: String) {
        viewModelScope.launch {
            try {
                val counts = withContext(dispatchers.io) { repository.importFromJson(jsonString) }
                _uiState.update { it.copy(importResult = counts) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message ?: "Failed to import commands") }
            }
        }
    }

    fun clearExportedJson() {
        _uiState.update { it.copy(exportedJson = null, exportResult = null) }
    }

    fun clearImportResult() {
        _uiState.update { it.copy(importResult = null) }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
