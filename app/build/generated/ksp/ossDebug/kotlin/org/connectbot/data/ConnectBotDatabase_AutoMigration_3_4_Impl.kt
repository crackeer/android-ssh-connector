package org.connectbot.`data`

import androidx.room.migration.Migration
import androidx.room.util.foreignKeyCheck
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class ConnectBotDatabase_AutoMigration_3_4_Impl : Migration {
  public constructor() : super(3, 4)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("CREATE TABLE IF NOT EXISTS `_new_known_hosts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `host_id` INTEGER, `hostname` TEXT NOT NULL, `port` INTEGER NOT NULL, `host_key_algo` TEXT NOT NULL, `host_key` BLOB NOT NULL, FOREIGN KEY(`host_id`) REFERENCES `hosts`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
    connection.execSQL("INSERT INTO `_new_known_hosts` (`id`,`host_id`,`hostname`,`port`,`host_key_algo`,`host_key`) SELECT `id`,`host_id`,`hostname`,`port`,`host_key_algo`,`host_key` FROM `known_hosts`")
    connection.execSQL("DROP TABLE `known_hosts`")
    connection.execSQL("ALTER TABLE `_new_known_hosts` RENAME TO `known_hosts`")
    connection.execSQL("CREATE INDEX IF NOT EXISTS `index_known_hosts_host_id` ON `known_hosts` (`host_id`)")
    connection.execSQL("CREATE INDEX IF NOT EXISTS `index_known_hosts_host_id_host_key` ON `known_hosts` (`host_id`, `host_key`)")
    foreignKeyCheck(connection, "known_hosts")
  }
}
