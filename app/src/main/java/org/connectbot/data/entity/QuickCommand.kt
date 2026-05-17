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

package org.connectbot.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * A quick command that can be sent to a terminal session with a single tap.
 * Commands are shown in a list on the home screen and can be selected from
 * the terminal header to send directly to the active terminal.
 */
@Entity(
    tableName = "quick_commands",
    indices = [Index("sort_order")],
)
data class QuickCommand(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    /** Human-readable label shown in the list. */
    val label: String,

    /** The command text that will be sent to the terminal, including a trailing newline if desired. */
    val command: String,

    /** Display order; lower values appear first. */
    @ColumnInfo(name = "sort_order", defaultValue = "0")
    val sortOrder: Int = 0,
)
