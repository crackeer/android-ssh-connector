package org.connectbot.`data`

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class ConnectBotDatabase_AutoMigration_5_6_Impl : Migration {
  public constructor() : super(5, 6)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("ALTER TABLE `profiles` ADD COLUMN `force_size_rows` INTEGER DEFAULT NULL")
    connection.execSQL("ALTER TABLE `profiles` ADD COLUMN `force_size_columns` INTEGER DEFAULT NULL")
  }
}
