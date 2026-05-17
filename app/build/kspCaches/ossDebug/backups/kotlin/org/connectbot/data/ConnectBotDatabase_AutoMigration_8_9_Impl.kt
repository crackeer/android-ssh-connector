package org.connectbot.`data`

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class ConnectBotDatabase_AutoMigration_8_9_Impl : Migration {
  public constructor() : super(8, 9)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("CREATE TABLE IF NOT EXISTS `quick_commands` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `label` TEXT NOT NULL, `command` TEXT NOT NULL, `sort_order` INTEGER NOT NULL DEFAULT 0)")
    connection.execSQL("CREATE INDEX IF NOT EXISTS `index_quick_commands_sort_order` ON `quick_commands` (`sort_order`)")
  }
}
