package org.connectbot.`data`

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class ConnectBotDatabase_AutoMigration_6_7_Impl : Migration {
  public constructor() : super(6, 7)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("ALTER TABLE `hosts` ADD COLUMN `ip_version` TEXT NOT NULL DEFAULT 'IPV4_AND_IPV6'")
  }
}
