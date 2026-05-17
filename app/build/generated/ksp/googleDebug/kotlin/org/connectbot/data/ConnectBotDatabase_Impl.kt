package org.connectbot.`data`

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass
import org.connectbot.`data`.dao.ColorSchemeDao
import org.connectbot.`data`.dao.ColorSchemeDao_Impl
import org.connectbot.`data`.dao.HostDao
import org.connectbot.`data`.dao.HostDao_Impl
import org.connectbot.`data`.dao.KnownHostDao
import org.connectbot.`data`.dao.KnownHostDao_Impl
import org.connectbot.`data`.dao.PortForwardDao
import org.connectbot.`data`.dao.PortForwardDao_Impl
import org.connectbot.`data`.dao.ProfileDao
import org.connectbot.`data`.dao.ProfileDao_Impl
import org.connectbot.`data`.dao.PubkeyDao
import org.connectbot.`data`.dao.PubkeyDao_Impl
import org.connectbot.`data`.dao.QuickCommandDao
import org.connectbot.`data`.dao.QuickCommandDao_Impl

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ConnectBotDatabase_Impl : ConnectBotDatabase() {
  private val _hostDao: Lazy<HostDao> = lazy {
    HostDao_Impl(this)
  }

  private val _pubkeyDao: Lazy<PubkeyDao> = lazy {
    PubkeyDao_Impl(this)
  }

  private val _portForwardDao: Lazy<PortForwardDao> = lazy {
    PortForwardDao_Impl(this)
  }

  private val _knownHostDao: Lazy<KnownHostDao> = lazy {
    KnownHostDao_Impl(this)
  }

  private val _colorSchemeDao: Lazy<ColorSchemeDao> = lazy {
    ColorSchemeDao_Impl(this)
  }

  private val _profileDao: Lazy<ProfileDao> = lazy {
    ProfileDao_Impl(this)
  }

  private val _quickCommandDao: Lazy<QuickCommandDao> = lazy {
    QuickCommandDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(9, "d302dbd37a004c1152fc2a6b14a7c7e4", "4dfa928d41cce160096f541e2eade9b4") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `hosts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nickname` TEXT NOT NULL, `protocol` TEXT NOT NULL, `username` TEXT NOT NULL, `hostname` TEXT NOT NULL, `port` INTEGER NOT NULL, `host_key_algo` TEXT, `last_connect` INTEGER NOT NULL, `color` TEXT, `use_keys` INTEGER NOT NULL, `use_auth_agent` TEXT, `post_login` TEXT, `pubkey_id` INTEGER NOT NULL, `want_session` INTEGER NOT NULL, `compression` INTEGER NOT NULL, `stay_connected` INTEGER NOT NULL, `quick_disconnect` INTEGER NOT NULL, `scrollback_lines` INTEGER NOT NULL, `use_ctrl_alt_as_meta_key` INTEGER NOT NULL, `jump_host_id` INTEGER, `profile_id` INTEGER, `ip_version` TEXT NOT NULL DEFAULT 'IPV4_AND_IPV6')")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_hosts_nickname` ON `hosts` (`nickname`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_hosts_protocol_username_hostname_port` ON `hosts` (`protocol`, `username`, `hostname`, `port`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `pubkeys` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nickname` TEXT NOT NULL, `type` TEXT NOT NULL, `private_key` BLOB, `public_key` BLOB NOT NULL, `encrypted` INTEGER NOT NULL, `startup` INTEGER NOT NULL, `confirmation` INTEGER NOT NULL, `created_date` INTEGER NOT NULL, `storage_type` TEXT NOT NULL, `allow_backup` INTEGER NOT NULL, `keystore_alias` TEXT)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_pubkeys_nickname` ON `pubkeys` (`nickname`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_pubkeys_storage_type` ON `pubkeys` (`storage_type`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_pubkeys_allow_backup` ON `pubkeys` (`allow_backup`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `port_forwards` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `host_id` INTEGER NOT NULL, `nickname` TEXT NOT NULL, `type` TEXT NOT NULL, `source_addr` TEXT NOT NULL DEFAULT 'localhost', `source_port` INTEGER NOT NULL, `dest_addr` TEXT, `dest_port` INTEGER NOT NULL, FOREIGN KEY(`host_id`) REFERENCES `hosts`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_port_forwards_host_id` ON `port_forwards` (`host_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `known_hosts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `host_id` INTEGER, `hostname` TEXT NOT NULL, `port` INTEGER NOT NULL, `host_key_algo` TEXT NOT NULL, `host_key` BLOB NOT NULL, FOREIGN KEY(`host_id`) REFERENCES `hosts`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_known_hosts_host_id` ON `known_hosts` (`host_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_known_hosts_host_id_host_key` ON `known_hosts` (`host_id`, `host_key`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `color_schemes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `is_built_in` INTEGER NOT NULL, `description` TEXT NOT NULL, `foreground` INTEGER NOT NULL, `background` INTEGER NOT NULL)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_color_schemes_name` ON `color_schemes` (`name`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `color_palette` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `scheme_id` INTEGER NOT NULL, `color_index` INTEGER NOT NULL, `color` INTEGER NOT NULL, FOREIGN KEY(`scheme_id`) REFERENCES `color_schemes`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_color_palette_scheme_id` ON `color_palette` (`scheme_id`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_color_palette_scheme_id_color_index` ON `color_palette` (`scheme_id`, `color_index`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `profiles` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `icon_color` TEXT, `color_scheme_id` INTEGER NOT NULL DEFAULT -1, `font_family` TEXT, `font_size` INTEGER NOT NULL DEFAULT 10, `del_key` TEXT NOT NULL DEFAULT 'del', `encoding` TEXT NOT NULL DEFAULT 'UTF-8', `emulation` TEXT NOT NULL DEFAULT 'xterm-256color', `force_size_rows` INTEGER, `force_size_columns` INTEGER)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_profiles_name` ON `profiles` (`name`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `quick_commands` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `label` TEXT NOT NULL, `command` TEXT NOT NULL, `sort_order` INTEGER NOT NULL DEFAULT 0)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_quick_commands_sort_order` ON `quick_commands` (`sort_order`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd302dbd37a004c1152fc2a6b14a7c7e4')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `hosts`")
        connection.execSQL("DROP TABLE IF EXISTS `pubkeys`")
        connection.execSQL("DROP TABLE IF EXISTS `port_forwards`")
        connection.execSQL("DROP TABLE IF EXISTS `known_hosts`")
        connection.execSQL("DROP TABLE IF EXISTS `color_schemes`")
        connection.execSQL("DROP TABLE IF EXISTS `color_palette`")
        connection.execSQL("DROP TABLE IF EXISTS `profiles`")
        connection.execSQL("DROP TABLE IF EXISTS `quick_commands`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        connection.execSQL("PRAGMA foreign_keys = ON")
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsHosts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsHosts.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("nickname", TableInfo.Column("nickname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("protocol", TableInfo.Column("protocol", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("username", TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("hostname", TableInfo.Column("hostname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("port", TableInfo.Column("port", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("host_key_algo", TableInfo.Column("host_key_algo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("last_connect", TableInfo.Column("last_connect", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("color", TableInfo.Column("color", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("use_keys", TableInfo.Column("use_keys", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("use_auth_agent", TableInfo.Column("use_auth_agent", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("post_login", TableInfo.Column("post_login", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("pubkey_id", TableInfo.Column("pubkey_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("want_session", TableInfo.Column("want_session", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("compression", TableInfo.Column("compression", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("stay_connected", TableInfo.Column("stay_connected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("quick_disconnect", TableInfo.Column("quick_disconnect", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("scrollback_lines", TableInfo.Column("scrollback_lines", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("use_ctrl_alt_as_meta_key", TableInfo.Column("use_ctrl_alt_as_meta_key", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("jump_host_id", TableInfo.Column("jump_host_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("profile_id", TableInfo.Column("profile_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHosts.put("ip_version", TableInfo.Column("ip_version", "TEXT", true, 0, "'IPV4_AND_IPV6'", TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysHosts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesHosts: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesHosts.add(TableInfo.Index("index_hosts_nickname", true, listOf("nickname"), listOf("ASC")))
        _indicesHosts.add(TableInfo.Index("index_hosts_protocol_username_hostname_port", false, listOf("protocol", "username", "hostname", "port"), listOf("ASC", "ASC", "ASC", "ASC")))
        val _infoHosts: TableInfo = TableInfo("hosts", _columnsHosts, _foreignKeysHosts, _indicesHosts)
        val _existingHosts: TableInfo = read(connection, "hosts")
        if (!_infoHosts.equals(_existingHosts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |hosts(org.connectbot.data.entity.Host).
              | Expected:
              |""".trimMargin() + _infoHosts + """
              |
              | Found:
              |""".trimMargin() + _existingHosts)
        }
        val _columnsPubkeys: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPubkeys.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPubkeys.put("nickname", TableInfo.Column("nickname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPubkeys.put("type", TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPubkeys.put("private_key", TableInfo.Column("private_key", "BLOB", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPubkeys.put("public_key", TableInfo.Column("public_key", "BLOB", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPubkeys.put("encrypted", TableInfo.Column("encrypted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPubkeys.put("startup", TableInfo.Column("startup", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPubkeys.put("confirmation", TableInfo.Column("confirmation", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPubkeys.put("created_date", TableInfo.Column("created_date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPubkeys.put("storage_type", TableInfo.Column("storage_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPubkeys.put("allow_backup", TableInfo.Column("allow_backup", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPubkeys.put("keystore_alias", TableInfo.Column("keystore_alias", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPubkeys: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPubkeys: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesPubkeys.add(TableInfo.Index("index_pubkeys_nickname", true, listOf("nickname"), listOf("ASC")))
        _indicesPubkeys.add(TableInfo.Index("index_pubkeys_storage_type", false, listOf("storage_type"), listOf("ASC")))
        _indicesPubkeys.add(TableInfo.Index("index_pubkeys_allow_backup", false, listOf("allow_backup"), listOf("ASC")))
        val _infoPubkeys: TableInfo = TableInfo("pubkeys", _columnsPubkeys, _foreignKeysPubkeys, _indicesPubkeys)
        val _existingPubkeys: TableInfo = read(connection, "pubkeys")
        if (!_infoPubkeys.equals(_existingPubkeys)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |pubkeys(org.connectbot.data.entity.Pubkey).
              | Expected:
              |""".trimMargin() + _infoPubkeys + """
              |
              | Found:
              |""".trimMargin() + _existingPubkeys)
        }
        val _columnsPortForwards: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPortForwards.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPortForwards.put("host_id", TableInfo.Column("host_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPortForwards.put("nickname", TableInfo.Column("nickname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPortForwards.put("type", TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPortForwards.put("source_addr", TableInfo.Column("source_addr", "TEXT", true, 0, "'localhost'", TableInfo.CREATED_FROM_ENTITY))
        _columnsPortForwards.put("source_port", TableInfo.Column("source_port", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPortForwards.put("dest_addr", TableInfo.Column("dest_addr", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPortForwards.put("dest_port", TableInfo.Column("dest_port", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPortForwards: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysPortForwards.add(TableInfo.ForeignKey("hosts", "CASCADE", "NO ACTION", listOf("host_id"), listOf("id")))
        val _indicesPortForwards: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesPortForwards.add(TableInfo.Index("index_port_forwards_host_id", false, listOf("host_id"), listOf("ASC")))
        val _infoPortForwards: TableInfo = TableInfo("port_forwards", _columnsPortForwards, _foreignKeysPortForwards, _indicesPortForwards)
        val _existingPortForwards: TableInfo = read(connection, "port_forwards")
        if (!_infoPortForwards.equals(_existingPortForwards)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |port_forwards(org.connectbot.data.entity.PortForward).
              | Expected:
              |""".trimMargin() + _infoPortForwards + """
              |
              | Found:
              |""".trimMargin() + _existingPortForwards)
        }
        val _columnsKnownHosts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsKnownHosts.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKnownHosts.put("host_id", TableInfo.Column("host_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKnownHosts.put("hostname", TableInfo.Column("hostname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKnownHosts.put("port", TableInfo.Column("port", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKnownHosts.put("host_key_algo", TableInfo.Column("host_key_algo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKnownHosts.put("host_key", TableInfo.Column("host_key", "BLOB", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysKnownHosts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysKnownHosts.add(TableInfo.ForeignKey("hosts", "CASCADE", "NO ACTION", listOf("host_id"), listOf("id")))
        val _indicesKnownHosts: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesKnownHosts.add(TableInfo.Index("index_known_hosts_host_id", false, listOf("host_id"), listOf("ASC")))
        _indicesKnownHosts.add(TableInfo.Index("index_known_hosts_host_id_host_key", false, listOf("host_id", "host_key"), listOf("ASC", "ASC")))
        val _infoKnownHosts: TableInfo = TableInfo("known_hosts", _columnsKnownHosts, _foreignKeysKnownHosts, _indicesKnownHosts)
        val _existingKnownHosts: TableInfo = read(connection, "known_hosts")
        if (!_infoKnownHosts.equals(_existingKnownHosts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |known_hosts(org.connectbot.data.entity.KnownHost).
              | Expected:
              |""".trimMargin() + _infoKnownHosts + """
              |
              | Found:
              |""".trimMargin() + _existingKnownHosts)
        }
        val _columnsColorSchemes: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsColorSchemes.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsColorSchemes.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsColorSchemes.put("is_built_in", TableInfo.Column("is_built_in", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsColorSchemes.put("description", TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsColorSchemes.put("foreground", TableInfo.Column("foreground", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsColorSchemes.put("background", TableInfo.Column("background", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysColorSchemes: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesColorSchemes: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesColorSchemes.add(TableInfo.Index("index_color_schemes_name", true, listOf("name"), listOf("ASC")))
        val _infoColorSchemes: TableInfo = TableInfo("color_schemes", _columnsColorSchemes, _foreignKeysColorSchemes, _indicesColorSchemes)
        val _existingColorSchemes: TableInfo = read(connection, "color_schemes")
        if (!_infoColorSchemes.equals(_existingColorSchemes)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |color_schemes(org.connectbot.data.entity.ColorScheme).
              | Expected:
              |""".trimMargin() + _infoColorSchemes + """
              |
              | Found:
              |""".trimMargin() + _existingColorSchemes)
        }
        val _columnsColorPalette: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsColorPalette.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsColorPalette.put("scheme_id", TableInfo.Column("scheme_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsColorPalette.put("color_index", TableInfo.Column("color_index", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsColorPalette.put("color", TableInfo.Column("color", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysColorPalette: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysColorPalette.add(TableInfo.ForeignKey("color_schemes", "CASCADE", "NO ACTION", listOf("scheme_id"), listOf("id")))
        val _indicesColorPalette: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesColorPalette.add(TableInfo.Index("index_color_palette_scheme_id", false, listOf("scheme_id"), listOf("ASC")))
        _indicesColorPalette.add(TableInfo.Index("index_color_palette_scheme_id_color_index", true, listOf("scheme_id", "color_index"), listOf("ASC", "ASC")))
        val _infoColorPalette: TableInfo = TableInfo("color_palette", _columnsColorPalette, _foreignKeysColorPalette, _indicesColorPalette)
        val _existingColorPalette: TableInfo = read(connection, "color_palette")
        if (!_infoColorPalette.equals(_existingColorPalette)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |color_palette(org.connectbot.data.entity.ColorPalette).
              | Expected:
              |""".trimMargin() + _infoColorPalette + """
              |
              | Found:
              |""".trimMargin() + _existingColorPalette)
        }
        val _columnsProfiles: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsProfiles.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProfiles.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProfiles.put("icon_color", TableInfo.Column("icon_color", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProfiles.put("color_scheme_id", TableInfo.Column("color_scheme_id", "INTEGER", true, 0, "-1", TableInfo.CREATED_FROM_ENTITY))
        _columnsProfiles.put("font_family", TableInfo.Column("font_family", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProfiles.put("font_size", TableInfo.Column("font_size", "INTEGER", true, 0, "10", TableInfo.CREATED_FROM_ENTITY))
        _columnsProfiles.put("del_key", TableInfo.Column("del_key", "TEXT", true, 0, "'del'", TableInfo.CREATED_FROM_ENTITY))
        _columnsProfiles.put("encoding", TableInfo.Column("encoding", "TEXT", true, 0, "'UTF-8'", TableInfo.CREATED_FROM_ENTITY))
        _columnsProfiles.put("emulation", TableInfo.Column("emulation", "TEXT", true, 0, "'xterm-256color'", TableInfo.CREATED_FROM_ENTITY))
        _columnsProfiles.put("force_size_rows", TableInfo.Column("force_size_rows", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProfiles.put("force_size_columns", TableInfo.Column("force_size_columns", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysProfiles: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesProfiles: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesProfiles.add(TableInfo.Index("index_profiles_name", true, listOf("name"), listOf("ASC")))
        val _infoProfiles: TableInfo = TableInfo("profiles", _columnsProfiles, _foreignKeysProfiles, _indicesProfiles)
        val _existingProfiles: TableInfo = read(connection, "profiles")
        if (!_infoProfiles.equals(_existingProfiles)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |profiles(org.connectbot.data.entity.Profile).
              | Expected:
              |""".trimMargin() + _infoProfiles + """
              |
              | Found:
              |""".trimMargin() + _existingProfiles)
        }
        val _columnsQuickCommands: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsQuickCommands.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsQuickCommands.put("label", TableInfo.Column("label", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsQuickCommands.put("command", TableInfo.Column("command", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsQuickCommands.put("sort_order", TableInfo.Column("sort_order", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysQuickCommands: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesQuickCommands: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesQuickCommands.add(TableInfo.Index("index_quick_commands_sort_order", false, listOf("sort_order"), listOf("ASC")))
        val _infoQuickCommands: TableInfo = TableInfo("quick_commands", _columnsQuickCommands, _foreignKeysQuickCommands, _indicesQuickCommands)
        val _existingQuickCommands: TableInfo = read(connection, "quick_commands")
        if (!_infoQuickCommands.equals(_existingQuickCommands)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |quick_commands(org.connectbot.data.entity.QuickCommand).
              | Expected:
              |""".trimMargin() + _infoQuickCommands + """
              |
              | Found:
              |""".trimMargin() + _existingQuickCommands)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "hosts", "pubkeys", "port_forwards", "known_hosts", "color_schemes", "color_palette", "profiles", "quick_commands")
  }

  public override fun clearAllTables() {
    super.performClear(true, "hosts", "pubkeys", "port_forwards", "known_hosts", "color_schemes", "color_palette", "profiles", "quick_commands")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(HostDao::class, HostDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PubkeyDao::class, PubkeyDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PortForwardDao::class, PortForwardDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(KnownHostDao::class, KnownHostDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ColorSchemeDao::class, ColorSchemeDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ProfileDao::class, ProfileDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(QuickCommandDao::class, QuickCommandDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    _autoMigrations.add(ConnectBotDatabase_AutoMigration_1_2_Impl())
    _autoMigrations.add(ConnectBotDatabase_AutoMigration_2_4_Impl())
    _autoMigrations.add(ConnectBotDatabase_AutoMigration_3_4_Impl())
    _autoMigrations.add(ConnectBotDatabase_AutoMigration_5_6_Impl())
    _autoMigrations.add(ConnectBotDatabase_AutoMigration_6_7_Impl())
    _autoMigrations.add(ConnectBotDatabase_AutoMigration_7_8_Impl())
    _autoMigrations.add(ConnectBotDatabase_AutoMigration_8_9_Impl())
    return _autoMigrations
  }

  public override fun hostDao(): HostDao = _hostDao.value

  public override fun pubkeyDao(): PubkeyDao = _pubkeyDao.value

  public override fun portForwardDao(): PortForwardDao = _portForwardDao.value

  public override fun knownHostDao(): KnownHostDao = _knownHostDao.value

  public override fun colorSchemeDao(): ColorSchemeDao = _colorSchemeDao.value

  public override fun profileDao(): ProfileDao = _profileDao.value

  public override fun quickCommandDao(): QuickCommandDao = _quickCommandDao.value
}
