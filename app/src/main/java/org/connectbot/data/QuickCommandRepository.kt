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

package org.connectbot.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import org.connectbot.data.dao.QuickCommandDao
import org.connectbot.data.entity.QuickCommand
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Result of exporting commands.
 */
data class CommandExportCounts(val commandCount: Int)

/**
 * Result of importing commands.
 */
data class CommandImportCounts(val imported: Int, val skipped: Int)

/**
 * Repository for quick commands.
 * Provides a clean API over [QuickCommandDao] for ViewModels,
 * including JSON export/import.
 */
@Singleton
class QuickCommandRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val quickCommandDao: QuickCommandDao,
) {
    /** Observe all quick commands, ordered by sort_order then label. */
    fun observeAll(): Flow<List<QuickCommand>> = quickCommandDao.observeAll()

    /** Get all quick commands (one-time). */
    suspend fun getAll(): List<QuickCommand> = quickCommandDao.getAll()

    /**
     * Save a quick command (insert if id == 0, update otherwise).
     * @return The saved command with its assigned ID.
     */
    suspend fun save(command: QuickCommand): QuickCommand {
        return if (command.id == 0L) {
            val newId = quickCommandDao.insert(command)
            command.copy(id = newId)
        } else {
            quickCommandDao.update(command)
            command
        }
    }

    /** Delete a quick command. */
    suspend fun delete(command: QuickCommand) {
        quickCommandDao.delete(command)
    }

    // ============================================================================
    // Export / Import
    // ============================================================================

    /**
     * Export all commands to a JSON string.
     *
     * Format:
     * ```json
     * {
     *   "version": 1,
     *   "commands": [
     *     { "label": "...", "command": "...", "sort_order": 0 },
     *     ...
     *   ]
     * }
     * ```
     *
     * @return Pair of JSON string and export counts.
     */
    suspend fun exportToJson(): Pair<String, CommandExportCounts> {
        val commands = quickCommandDao.getAll()
        val array = JSONArray()
        for (cmd in commands) {
            val obj = JSONObject()
            obj.put("label", cmd.label)
            obj.put("command", cmd.command)
            obj.put("sort_order", cmd.sortOrder)
            array.put(obj)
        }
        val root = JSONObject()
        root.put("version", 1)
        root.put("commands", array)
        return Pair(root.toString(2), CommandExportCounts(commands.size))
    }

    /**
     * Import commands from a JSON string.
     * Commands whose label already exists are skipped (deduplication by label).
     *
     * @return Import counts.
     */
    suspend fun importFromJson(jsonString: String): CommandImportCounts {
        val root = JSONObject(jsonString)
        val array = root.getJSONArray("commands")
        val existing = quickCommandDao.getAll().map { it.label }.toHashSet()

        var imported = 0
        var skipped = 0

        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            val label = obj.getString("label")
            val command = obj.getString("command")
            val sortOrder = obj.optInt("sort_order", 0)

            if (label in existing) {
                skipped++
            } else {
                quickCommandDao.insert(QuickCommand(label = label, command = command, sortOrder = sortOrder))
                existing.add(label)
                imported++
            }
        }

        return CommandImportCounts(imported, skipped)
    }
}
