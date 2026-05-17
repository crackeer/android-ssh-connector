package org.connectbot.`data`

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class ConnectBotDatabase_AutoMigration_7_8_Impl : Migration {
  public constructor() : super(7, 8)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("ALTER TABLE `port_forwards` ADD COLUMN `source_addr` TEXT NOT NULL DEFAULT 'localhost'")
  }
}
