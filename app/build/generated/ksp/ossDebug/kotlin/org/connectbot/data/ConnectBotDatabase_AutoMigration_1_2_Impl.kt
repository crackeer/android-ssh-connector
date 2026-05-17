package org.connectbot.`data`

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class ConnectBotDatabase_AutoMigration_1_2_Impl : Migration {
  public constructor() : super(1, 2)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("ALTER TABLE `hosts` ADD COLUMN `jump_host_id` INTEGER DEFAULT NULL")
  }
}
