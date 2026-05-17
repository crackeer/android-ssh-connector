package org.connectbot.`data`.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow
import org.connectbot.`data`.entity.Host

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class HostDao_Impl(
  __db: RoomDatabase,
) : HostDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfHost: EntityInsertAdapter<Host>

  private val __deleteAdapterOfHost: EntityDeleteOrUpdateAdapter<Host>

  private val __updateAdapterOfHost: EntityDeleteOrUpdateAdapter<Host>
  init {
    this.__db = __db
    this.__insertAdapterOfHost = object : EntityInsertAdapter<Host>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `hosts` (`id`,`nickname`,`protocol`,`username`,`hostname`,`port`,`host_key_algo`,`last_connect`,`color`,`use_keys`,`use_auth_agent`,`post_login`,`pubkey_id`,`want_session`,`compression`,`stay_connected`,`quick_disconnect`,`scrollback_lines`,`use_ctrl_alt_as_meta_key`,`jump_host_id`,`profile_id`,`ip_version`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Host) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.nickname)
        statement.bindText(3, entity.protocol)
        statement.bindText(4, entity.username)
        statement.bindText(5, entity.hostname)
        statement.bindLong(6, entity.port.toLong())
        val _tmpHostKeyAlgo: String? = entity.hostKeyAlgo
        if (_tmpHostKeyAlgo == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpHostKeyAlgo)
        }
        statement.bindLong(8, entity.lastConnect)
        val _tmpColor: String? = entity.color
        if (_tmpColor == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpColor)
        }
        val _tmp: Int = if (entity.useKeys) 1 else 0
        statement.bindLong(10, _tmp.toLong())
        val _tmpUseAuthAgent: String? = entity.useAuthAgent
        if (_tmpUseAuthAgent == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpUseAuthAgent)
        }
        val _tmpPostLogin: String? = entity.postLogin
        if (_tmpPostLogin == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpPostLogin)
        }
        statement.bindLong(13, entity.pubkeyId)
        val _tmp_1: Int = if (entity.wantSession) 1 else 0
        statement.bindLong(14, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.compression) 1 else 0
        statement.bindLong(15, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.stayConnected) 1 else 0
        statement.bindLong(16, _tmp_3.toLong())
        val _tmp_4: Int = if (entity.quickDisconnect) 1 else 0
        statement.bindLong(17, _tmp_4.toLong())
        statement.bindLong(18, entity.scrollbackLines.toLong())
        val _tmp_5: Int = if (entity.useCtrlAltAsMetaKey) 1 else 0
        statement.bindLong(19, _tmp_5.toLong())
        val _tmpJumpHostId: Long? = entity.jumpHostId
        if (_tmpJumpHostId == null) {
          statement.bindNull(20)
        } else {
          statement.bindLong(20, _tmpJumpHostId)
        }
        val _tmpProfileId: Long? = entity.profileId
        if (_tmpProfileId == null) {
          statement.bindNull(21)
        } else {
          statement.bindLong(21, _tmpProfileId)
        }
        statement.bindText(22, entity.ipVersion)
      }
    }
    this.__deleteAdapterOfHost = object : EntityDeleteOrUpdateAdapter<Host>() {
      protected override fun createQuery(): String = "DELETE FROM `hosts` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Host) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfHost = object : EntityDeleteOrUpdateAdapter<Host>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `hosts` SET `id` = ?,`nickname` = ?,`protocol` = ?,`username` = ?,`hostname` = ?,`port` = ?,`host_key_algo` = ?,`last_connect` = ?,`color` = ?,`use_keys` = ?,`use_auth_agent` = ?,`post_login` = ?,`pubkey_id` = ?,`want_session` = ?,`compression` = ?,`stay_connected` = ?,`quick_disconnect` = ?,`scrollback_lines` = ?,`use_ctrl_alt_as_meta_key` = ?,`jump_host_id` = ?,`profile_id` = ?,`ip_version` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Host) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.nickname)
        statement.bindText(3, entity.protocol)
        statement.bindText(4, entity.username)
        statement.bindText(5, entity.hostname)
        statement.bindLong(6, entity.port.toLong())
        val _tmpHostKeyAlgo: String? = entity.hostKeyAlgo
        if (_tmpHostKeyAlgo == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpHostKeyAlgo)
        }
        statement.bindLong(8, entity.lastConnect)
        val _tmpColor: String? = entity.color
        if (_tmpColor == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpColor)
        }
        val _tmp: Int = if (entity.useKeys) 1 else 0
        statement.bindLong(10, _tmp.toLong())
        val _tmpUseAuthAgent: String? = entity.useAuthAgent
        if (_tmpUseAuthAgent == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpUseAuthAgent)
        }
        val _tmpPostLogin: String? = entity.postLogin
        if (_tmpPostLogin == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpPostLogin)
        }
        statement.bindLong(13, entity.pubkeyId)
        val _tmp_1: Int = if (entity.wantSession) 1 else 0
        statement.bindLong(14, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.compression) 1 else 0
        statement.bindLong(15, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.stayConnected) 1 else 0
        statement.bindLong(16, _tmp_3.toLong())
        val _tmp_4: Int = if (entity.quickDisconnect) 1 else 0
        statement.bindLong(17, _tmp_4.toLong())
        statement.bindLong(18, entity.scrollbackLines.toLong())
        val _tmp_5: Int = if (entity.useCtrlAltAsMetaKey) 1 else 0
        statement.bindLong(19, _tmp_5.toLong())
        val _tmpJumpHostId: Long? = entity.jumpHostId
        if (_tmpJumpHostId == null) {
          statement.bindNull(20)
        } else {
          statement.bindLong(20, _tmpJumpHostId)
        }
        val _tmpProfileId: Long? = entity.profileId
        if (_tmpProfileId == null) {
          statement.bindNull(21)
        } else {
          statement.bindLong(21, _tmpProfileId)
        }
        statement.bindText(22, entity.ipVersion)
        statement.bindLong(23, entity.id)
      }
    }
  }

  public override suspend fun insert(host: Host): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfHost.insertAndReturnId(_connection, host)
    _result
  }

  public override suspend fun delete(host: Host): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfHost.handle(_connection, host)
  }

  public override suspend fun update(host: Host): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfHost.handle(_connection, host)
  }

  public override fun observeAll(): Flow<List<Host>> {
    val _sql: String = "SELECT * FROM hosts ORDER BY nickname ASC"
    return createFlow(__db, false, arrayOf("hosts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfHostname: Int = getColumnIndexOrThrow(_stmt, "hostname")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfHostKeyAlgo: Int = getColumnIndexOrThrow(_stmt, "host_key_algo")
        val _columnIndexOfLastConnect: Int = getColumnIndexOrThrow(_stmt, "last_connect")
        val _columnIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _columnIndexOfUseKeys: Int = getColumnIndexOrThrow(_stmt, "use_keys")
        val _columnIndexOfUseAuthAgent: Int = getColumnIndexOrThrow(_stmt, "use_auth_agent")
        val _columnIndexOfPostLogin: Int = getColumnIndexOrThrow(_stmt, "post_login")
        val _columnIndexOfPubkeyId: Int = getColumnIndexOrThrow(_stmt, "pubkey_id")
        val _columnIndexOfWantSession: Int = getColumnIndexOrThrow(_stmt, "want_session")
        val _columnIndexOfCompression: Int = getColumnIndexOrThrow(_stmt, "compression")
        val _columnIndexOfStayConnected: Int = getColumnIndexOrThrow(_stmt, "stay_connected")
        val _columnIndexOfQuickDisconnect: Int = getColumnIndexOrThrow(_stmt, "quick_disconnect")
        val _columnIndexOfScrollbackLines: Int = getColumnIndexOrThrow(_stmt, "scrollback_lines")
        val _columnIndexOfUseCtrlAltAsMetaKey: Int = getColumnIndexOrThrow(_stmt, "use_ctrl_alt_as_meta_key")
        val _columnIndexOfJumpHostId: Int = getColumnIndexOrThrow(_stmt, "jump_host_id")
        val _columnIndexOfProfileId: Int = getColumnIndexOrThrow(_stmt, "profile_id")
        val _columnIndexOfIpVersion: Int = getColumnIndexOrThrow(_stmt, "ip_version")
        val _result: MutableList<Host> = mutableListOf()
        while (_stmt.step()) {
          val _item: Host
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpProtocol: String
          _tmpProtocol = _stmt.getText(_columnIndexOfProtocol)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpHostname: String
          _tmpHostname = _stmt.getText(_columnIndexOfHostname)
          val _tmpPort: Int
          _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
          val _tmpHostKeyAlgo: String?
          if (_stmt.isNull(_columnIndexOfHostKeyAlgo)) {
            _tmpHostKeyAlgo = null
          } else {
            _tmpHostKeyAlgo = _stmt.getText(_columnIndexOfHostKeyAlgo)
          }
          val _tmpLastConnect: Long
          _tmpLastConnect = _stmt.getLong(_columnIndexOfLastConnect)
          val _tmpColor: String?
          if (_stmt.isNull(_columnIndexOfColor)) {
            _tmpColor = null
          } else {
            _tmpColor = _stmt.getText(_columnIndexOfColor)
          }
          val _tmpUseKeys: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfUseKeys).toInt()
          _tmpUseKeys = _tmp != 0
          val _tmpUseAuthAgent: String?
          if (_stmt.isNull(_columnIndexOfUseAuthAgent)) {
            _tmpUseAuthAgent = null
          } else {
            _tmpUseAuthAgent = _stmt.getText(_columnIndexOfUseAuthAgent)
          }
          val _tmpPostLogin: String?
          if (_stmt.isNull(_columnIndexOfPostLogin)) {
            _tmpPostLogin = null
          } else {
            _tmpPostLogin = _stmt.getText(_columnIndexOfPostLogin)
          }
          val _tmpPubkeyId: Long
          _tmpPubkeyId = _stmt.getLong(_columnIndexOfPubkeyId)
          val _tmpWantSession: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfWantSession).toInt()
          _tmpWantSession = _tmp_1 != 0
          val _tmpCompression: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfCompression).toInt()
          _tmpCompression = _tmp_2 != 0
          val _tmpStayConnected: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfStayConnected).toInt()
          _tmpStayConnected = _tmp_3 != 0
          val _tmpQuickDisconnect: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfQuickDisconnect).toInt()
          _tmpQuickDisconnect = _tmp_4 != 0
          val _tmpScrollbackLines: Int
          _tmpScrollbackLines = _stmt.getLong(_columnIndexOfScrollbackLines).toInt()
          val _tmpUseCtrlAltAsMetaKey: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfUseCtrlAltAsMetaKey).toInt()
          _tmpUseCtrlAltAsMetaKey = _tmp_5 != 0
          val _tmpJumpHostId: Long?
          if (_stmt.isNull(_columnIndexOfJumpHostId)) {
            _tmpJumpHostId = null
          } else {
            _tmpJumpHostId = _stmt.getLong(_columnIndexOfJumpHostId)
          }
          val _tmpProfileId: Long?
          if (_stmt.isNull(_columnIndexOfProfileId)) {
            _tmpProfileId = null
          } else {
            _tmpProfileId = _stmt.getLong(_columnIndexOfProfileId)
          }
          val _tmpIpVersion: String
          _tmpIpVersion = _stmt.getText(_columnIndexOfIpVersion)
          _item = Host(_tmpId,_tmpNickname,_tmpProtocol,_tmpUsername,_tmpHostname,_tmpPort,_tmpHostKeyAlgo,_tmpLastConnect,_tmpColor,_tmpUseKeys,_tmpUseAuthAgent,_tmpPostLogin,_tmpPubkeyId,_tmpWantSession,_tmpCompression,_tmpStayConnected,_tmpQuickDisconnect,_tmpScrollbackLines,_tmpUseCtrlAltAsMetaKey,_tmpJumpHostId,_tmpProfileId,_tmpIpVersion)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeAllSortedByColor(): Flow<List<Host>> {
    val _sql: String = "SELECT * FROM hosts ORDER BY color, nickname ASC"
    return createFlow(__db, false, arrayOf("hosts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfHostname: Int = getColumnIndexOrThrow(_stmt, "hostname")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfHostKeyAlgo: Int = getColumnIndexOrThrow(_stmt, "host_key_algo")
        val _columnIndexOfLastConnect: Int = getColumnIndexOrThrow(_stmt, "last_connect")
        val _columnIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _columnIndexOfUseKeys: Int = getColumnIndexOrThrow(_stmt, "use_keys")
        val _columnIndexOfUseAuthAgent: Int = getColumnIndexOrThrow(_stmt, "use_auth_agent")
        val _columnIndexOfPostLogin: Int = getColumnIndexOrThrow(_stmt, "post_login")
        val _columnIndexOfPubkeyId: Int = getColumnIndexOrThrow(_stmt, "pubkey_id")
        val _columnIndexOfWantSession: Int = getColumnIndexOrThrow(_stmt, "want_session")
        val _columnIndexOfCompression: Int = getColumnIndexOrThrow(_stmt, "compression")
        val _columnIndexOfStayConnected: Int = getColumnIndexOrThrow(_stmt, "stay_connected")
        val _columnIndexOfQuickDisconnect: Int = getColumnIndexOrThrow(_stmt, "quick_disconnect")
        val _columnIndexOfScrollbackLines: Int = getColumnIndexOrThrow(_stmt, "scrollback_lines")
        val _columnIndexOfUseCtrlAltAsMetaKey: Int = getColumnIndexOrThrow(_stmt, "use_ctrl_alt_as_meta_key")
        val _columnIndexOfJumpHostId: Int = getColumnIndexOrThrow(_stmt, "jump_host_id")
        val _columnIndexOfProfileId: Int = getColumnIndexOrThrow(_stmt, "profile_id")
        val _columnIndexOfIpVersion: Int = getColumnIndexOrThrow(_stmt, "ip_version")
        val _result: MutableList<Host> = mutableListOf()
        while (_stmt.step()) {
          val _item: Host
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpProtocol: String
          _tmpProtocol = _stmt.getText(_columnIndexOfProtocol)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpHostname: String
          _tmpHostname = _stmt.getText(_columnIndexOfHostname)
          val _tmpPort: Int
          _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
          val _tmpHostKeyAlgo: String?
          if (_stmt.isNull(_columnIndexOfHostKeyAlgo)) {
            _tmpHostKeyAlgo = null
          } else {
            _tmpHostKeyAlgo = _stmt.getText(_columnIndexOfHostKeyAlgo)
          }
          val _tmpLastConnect: Long
          _tmpLastConnect = _stmt.getLong(_columnIndexOfLastConnect)
          val _tmpColor: String?
          if (_stmt.isNull(_columnIndexOfColor)) {
            _tmpColor = null
          } else {
            _tmpColor = _stmt.getText(_columnIndexOfColor)
          }
          val _tmpUseKeys: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfUseKeys).toInt()
          _tmpUseKeys = _tmp != 0
          val _tmpUseAuthAgent: String?
          if (_stmt.isNull(_columnIndexOfUseAuthAgent)) {
            _tmpUseAuthAgent = null
          } else {
            _tmpUseAuthAgent = _stmt.getText(_columnIndexOfUseAuthAgent)
          }
          val _tmpPostLogin: String?
          if (_stmt.isNull(_columnIndexOfPostLogin)) {
            _tmpPostLogin = null
          } else {
            _tmpPostLogin = _stmt.getText(_columnIndexOfPostLogin)
          }
          val _tmpPubkeyId: Long
          _tmpPubkeyId = _stmt.getLong(_columnIndexOfPubkeyId)
          val _tmpWantSession: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfWantSession).toInt()
          _tmpWantSession = _tmp_1 != 0
          val _tmpCompression: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfCompression).toInt()
          _tmpCompression = _tmp_2 != 0
          val _tmpStayConnected: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfStayConnected).toInt()
          _tmpStayConnected = _tmp_3 != 0
          val _tmpQuickDisconnect: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfQuickDisconnect).toInt()
          _tmpQuickDisconnect = _tmp_4 != 0
          val _tmpScrollbackLines: Int
          _tmpScrollbackLines = _stmt.getLong(_columnIndexOfScrollbackLines).toInt()
          val _tmpUseCtrlAltAsMetaKey: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfUseCtrlAltAsMetaKey).toInt()
          _tmpUseCtrlAltAsMetaKey = _tmp_5 != 0
          val _tmpJumpHostId: Long?
          if (_stmt.isNull(_columnIndexOfJumpHostId)) {
            _tmpJumpHostId = null
          } else {
            _tmpJumpHostId = _stmt.getLong(_columnIndexOfJumpHostId)
          }
          val _tmpProfileId: Long?
          if (_stmt.isNull(_columnIndexOfProfileId)) {
            _tmpProfileId = null
          } else {
            _tmpProfileId = _stmt.getLong(_columnIndexOfProfileId)
          }
          val _tmpIpVersion: String
          _tmpIpVersion = _stmt.getText(_columnIndexOfIpVersion)
          _item = Host(_tmpId,_tmpNickname,_tmpProtocol,_tmpUsername,_tmpHostname,_tmpPort,_tmpHostKeyAlgo,_tmpLastConnect,_tmpColor,_tmpUseKeys,_tmpUseAuthAgent,_tmpPostLogin,_tmpPubkeyId,_tmpWantSession,_tmpCompression,_tmpStayConnected,_tmpQuickDisconnect,_tmpScrollbackLines,_tmpUseCtrlAltAsMetaKey,_tmpJumpHostId,_tmpProfileId,_tmpIpVersion)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeById(hostId: Long): Flow<Host?> {
    val _sql: String = "SELECT * FROM hosts WHERE id = ?"
    return createFlow(__db, false, arrayOf("hosts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, hostId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfHostname: Int = getColumnIndexOrThrow(_stmt, "hostname")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfHostKeyAlgo: Int = getColumnIndexOrThrow(_stmt, "host_key_algo")
        val _columnIndexOfLastConnect: Int = getColumnIndexOrThrow(_stmt, "last_connect")
        val _columnIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _columnIndexOfUseKeys: Int = getColumnIndexOrThrow(_stmt, "use_keys")
        val _columnIndexOfUseAuthAgent: Int = getColumnIndexOrThrow(_stmt, "use_auth_agent")
        val _columnIndexOfPostLogin: Int = getColumnIndexOrThrow(_stmt, "post_login")
        val _columnIndexOfPubkeyId: Int = getColumnIndexOrThrow(_stmt, "pubkey_id")
        val _columnIndexOfWantSession: Int = getColumnIndexOrThrow(_stmt, "want_session")
        val _columnIndexOfCompression: Int = getColumnIndexOrThrow(_stmt, "compression")
        val _columnIndexOfStayConnected: Int = getColumnIndexOrThrow(_stmt, "stay_connected")
        val _columnIndexOfQuickDisconnect: Int = getColumnIndexOrThrow(_stmt, "quick_disconnect")
        val _columnIndexOfScrollbackLines: Int = getColumnIndexOrThrow(_stmt, "scrollback_lines")
        val _columnIndexOfUseCtrlAltAsMetaKey: Int = getColumnIndexOrThrow(_stmt, "use_ctrl_alt_as_meta_key")
        val _columnIndexOfJumpHostId: Int = getColumnIndexOrThrow(_stmt, "jump_host_id")
        val _columnIndexOfProfileId: Int = getColumnIndexOrThrow(_stmt, "profile_id")
        val _columnIndexOfIpVersion: Int = getColumnIndexOrThrow(_stmt, "ip_version")
        val _result: Host?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpProtocol: String
          _tmpProtocol = _stmt.getText(_columnIndexOfProtocol)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpHostname: String
          _tmpHostname = _stmt.getText(_columnIndexOfHostname)
          val _tmpPort: Int
          _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
          val _tmpHostKeyAlgo: String?
          if (_stmt.isNull(_columnIndexOfHostKeyAlgo)) {
            _tmpHostKeyAlgo = null
          } else {
            _tmpHostKeyAlgo = _stmt.getText(_columnIndexOfHostKeyAlgo)
          }
          val _tmpLastConnect: Long
          _tmpLastConnect = _stmt.getLong(_columnIndexOfLastConnect)
          val _tmpColor: String?
          if (_stmt.isNull(_columnIndexOfColor)) {
            _tmpColor = null
          } else {
            _tmpColor = _stmt.getText(_columnIndexOfColor)
          }
          val _tmpUseKeys: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfUseKeys).toInt()
          _tmpUseKeys = _tmp != 0
          val _tmpUseAuthAgent: String?
          if (_stmt.isNull(_columnIndexOfUseAuthAgent)) {
            _tmpUseAuthAgent = null
          } else {
            _tmpUseAuthAgent = _stmt.getText(_columnIndexOfUseAuthAgent)
          }
          val _tmpPostLogin: String?
          if (_stmt.isNull(_columnIndexOfPostLogin)) {
            _tmpPostLogin = null
          } else {
            _tmpPostLogin = _stmt.getText(_columnIndexOfPostLogin)
          }
          val _tmpPubkeyId: Long
          _tmpPubkeyId = _stmt.getLong(_columnIndexOfPubkeyId)
          val _tmpWantSession: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfWantSession).toInt()
          _tmpWantSession = _tmp_1 != 0
          val _tmpCompression: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfCompression).toInt()
          _tmpCompression = _tmp_2 != 0
          val _tmpStayConnected: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfStayConnected).toInt()
          _tmpStayConnected = _tmp_3 != 0
          val _tmpQuickDisconnect: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfQuickDisconnect).toInt()
          _tmpQuickDisconnect = _tmp_4 != 0
          val _tmpScrollbackLines: Int
          _tmpScrollbackLines = _stmt.getLong(_columnIndexOfScrollbackLines).toInt()
          val _tmpUseCtrlAltAsMetaKey: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfUseCtrlAltAsMetaKey).toInt()
          _tmpUseCtrlAltAsMetaKey = _tmp_5 != 0
          val _tmpJumpHostId: Long?
          if (_stmt.isNull(_columnIndexOfJumpHostId)) {
            _tmpJumpHostId = null
          } else {
            _tmpJumpHostId = _stmt.getLong(_columnIndexOfJumpHostId)
          }
          val _tmpProfileId: Long?
          if (_stmt.isNull(_columnIndexOfProfileId)) {
            _tmpProfileId = null
          } else {
            _tmpProfileId = _stmt.getLong(_columnIndexOfProfileId)
          }
          val _tmpIpVersion: String
          _tmpIpVersion = _stmt.getText(_columnIndexOfIpVersion)
          _result = Host(_tmpId,_tmpNickname,_tmpProtocol,_tmpUsername,_tmpHostname,_tmpPort,_tmpHostKeyAlgo,_tmpLastConnect,_tmpColor,_tmpUseKeys,_tmpUseAuthAgent,_tmpPostLogin,_tmpPubkeyId,_tmpWantSession,_tmpCompression,_tmpStayConnected,_tmpQuickDisconnect,_tmpScrollbackLines,_tmpUseCtrlAltAsMetaKey,_tmpJumpHostId,_tmpProfileId,_tmpIpVersion)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(hostId: Long): Host? {
    val _sql: String = "SELECT * FROM hosts WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, hostId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfHostname: Int = getColumnIndexOrThrow(_stmt, "hostname")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfHostKeyAlgo: Int = getColumnIndexOrThrow(_stmt, "host_key_algo")
        val _columnIndexOfLastConnect: Int = getColumnIndexOrThrow(_stmt, "last_connect")
        val _columnIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _columnIndexOfUseKeys: Int = getColumnIndexOrThrow(_stmt, "use_keys")
        val _columnIndexOfUseAuthAgent: Int = getColumnIndexOrThrow(_stmt, "use_auth_agent")
        val _columnIndexOfPostLogin: Int = getColumnIndexOrThrow(_stmt, "post_login")
        val _columnIndexOfPubkeyId: Int = getColumnIndexOrThrow(_stmt, "pubkey_id")
        val _columnIndexOfWantSession: Int = getColumnIndexOrThrow(_stmt, "want_session")
        val _columnIndexOfCompression: Int = getColumnIndexOrThrow(_stmt, "compression")
        val _columnIndexOfStayConnected: Int = getColumnIndexOrThrow(_stmt, "stay_connected")
        val _columnIndexOfQuickDisconnect: Int = getColumnIndexOrThrow(_stmt, "quick_disconnect")
        val _columnIndexOfScrollbackLines: Int = getColumnIndexOrThrow(_stmt, "scrollback_lines")
        val _columnIndexOfUseCtrlAltAsMetaKey: Int = getColumnIndexOrThrow(_stmt, "use_ctrl_alt_as_meta_key")
        val _columnIndexOfJumpHostId: Int = getColumnIndexOrThrow(_stmt, "jump_host_id")
        val _columnIndexOfProfileId: Int = getColumnIndexOrThrow(_stmt, "profile_id")
        val _columnIndexOfIpVersion: Int = getColumnIndexOrThrow(_stmt, "ip_version")
        val _result: Host?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpProtocol: String
          _tmpProtocol = _stmt.getText(_columnIndexOfProtocol)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpHostname: String
          _tmpHostname = _stmt.getText(_columnIndexOfHostname)
          val _tmpPort: Int
          _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
          val _tmpHostKeyAlgo: String?
          if (_stmt.isNull(_columnIndexOfHostKeyAlgo)) {
            _tmpHostKeyAlgo = null
          } else {
            _tmpHostKeyAlgo = _stmt.getText(_columnIndexOfHostKeyAlgo)
          }
          val _tmpLastConnect: Long
          _tmpLastConnect = _stmt.getLong(_columnIndexOfLastConnect)
          val _tmpColor: String?
          if (_stmt.isNull(_columnIndexOfColor)) {
            _tmpColor = null
          } else {
            _tmpColor = _stmt.getText(_columnIndexOfColor)
          }
          val _tmpUseKeys: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfUseKeys).toInt()
          _tmpUseKeys = _tmp != 0
          val _tmpUseAuthAgent: String?
          if (_stmt.isNull(_columnIndexOfUseAuthAgent)) {
            _tmpUseAuthAgent = null
          } else {
            _tmpUseAuthAgent = _stmt.getText(_columnIndexOfUseAuthAgent)
          }
          val _tmpPostLogin: String?
          if (_stmt.isNull(_columnIndexOfPostLogin)) {
            _tmpPostLogin = null
          } else {
            _tmpPostLogin = _stmt.getText(_columnIndexOfPostLogin)
          }
          val _tmpPubkeyId: Long
          _tmpPubkeyId = _stmt.getLong(_columnIndexOfPubkeyId)
          val _tmpWantSession: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfWantSession).toInt()
          _tmpWantSession = _tmp_1 != 0
          val _tmpCompression: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfCompression).toInt()
          _tmpCompression = _tmp_2 != 0
          val _tmpStayConnected: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfStayConnected).toInt()
          _tmpStayConnected = _tmp_3 != 0
          val _tmpQuickDisconnect: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfQuickDisconnect).toInt()
          _tmpQuickDisconnect = _tmp_4 != 0
          val _tmpScrollbackLines: Int
          _tmpScrollbackLines = _stmt.getLong(_columnIndexOfScrollbackLines).toInt()
          val _tmpUseCtrlAltAsMetaKey: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfUseCtrlAltAsMetaKey).toInt()
          _tmpUseCtrlAltAsMetaKey = _tmp_5 != 0
          val _tmpJumpHostId: Long?
          if (_stmt.isNull(_columnIndexOfJumpHostId)) {
            _tmpJumpHostId = null
          } else {
            _tmpJumpHostId = _stmt.getLong(_columnIndexOfJumpHostId)
          }
          val _tmpProfileId: Long?
          if (_stmt.isNull(_columnIndexOfProfileId)) {
            _tmpProfileId = null
          } else {
            _tmpProfileId = _stmt.getLong(_columnIndexOfProfileId)
          }
          val _tmpIpVersion: String
          _tmpIpVersion = _stmt.getText(_columnIndexOfIpVersion)
          _result = Host(_tmpId,_tmpNickname,_tmpProtocol,_tmpUsername,_tmpHostname,_tmpPort,_tmpHostKeyAlgo,_tmpLastConnect,_tmpColor,_tmpUseKeys,_tmpUseAuthAgent,_tmpPostLogin,_tmpPubkeyId,_tmpWantSession,_tmpCompression,_tmpStayConnected,_tmpQuickDisconnect,_tmpScrollbackLines,_tmpUseCtrlAltAsMetaKey,_tmpJumpHostId,_tmpProfileId,_tmpIpVersion)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAll(): List<Host> {
    val _sql: String = "SELECT * FROM hosts ORDER BY nickname ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfHostname: Int = getColumnIndexOrThrow(_stmt, "hostname")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfHostKeyAlgo: Int = getColumnIndexOrThrow(_stmt, "host_key_algo")
        val _columnIndexOfLastConnect: Int = getColumnIndexOrThrow(_stmt, "last_connect")
        val _columnIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _columnIndexOfUseKeys: Int = getColumnIndexOrThrow(_stmt, "use_keys")
        val _columnIndexOfUseAuthAgent: Int = getColumnIndexOrThrow(_stmt, "use_auth_agent")
        val _columnIndexOfPostLogin: Int = getColumnIndexOrThrow(_stmt, "post_login")
        val _columnIndexOfPubkeyId: Int = getColumnIndexOrThrow(_stmt, "pubkey_id")
        val _columnIndexOfWantSession: Int = getColumnIndexOrThrow(_stmt, "want_session")
        val _columnIndexOfCompression: Int = getColumnIndexOrThrow(_stmt, "compression")
        val _columnIndexOfStayConnected: Int = getColumnIndexOrThrow(_stmt, "stay_connected")
        val _columnIndexOfQuickDisconnect: Int = getColumnIndexOrThrow(_stmt, "quick_disconnect")
        val _columnIndexOfScrollbackLines: Int = getColumnIndexOrThrow(_stmt, "scrollback_lines")
        val _columnIndexOfUseCtrlAltAsMetaKey: Int = getColumnIndexOrThrow(_stmt, "use_ctrl_alt_as_meta_key")
        val _columnIndexOfJumpHostId: Int = getColumnIndexOrThrow(_stmt, "jump_host_id")
        val _columnIndexOfProfileId: Int = getColumnIndexOrThrow(_stmt, "profile_id")
        val _columnIndexOfIpVersion: Int = getColumnIndexOrThrow(_stmt, "ip_version")
        val _result: MutableList<Host> = mutableListOf()
        while (_stmt.step()) {
          val _item: Host
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpProtocol: String
          _tmpProtocol = _stmt.getText(_columnIndexOfProtocol)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpHostname: String
          _tmpHostname = _stmt.getText(_columnIndexOfHostname)
          val _tmpPort: Int
          _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
          val _tmpHostKeyAlgo: String?
          if (_stmt.isNull(_columnIndexOfHostKeyAlgo)) {
            _tmpHostKeyAlgo = null
          } else {
            _tmpHostKeyAlgo = _stmt.getText(_columnIndexOfHostKeyAlgo)
          }
          val _tmpLastConnect: Long
          _tmpLastConnect = _stmt.getLong(_columnIndexOfLastConnect)
          val _tmpColor: String?
          if (_stmt.isNull(_columnIndexOfColor)) {
            _tmpColor = null
          } else {
            _tmpColor = _stmt.getText(_columnIndexOfColor)
          }
          val _tmpUseKeys: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfUseKeys).toInt()
          _tmpUseKeys = _tmp != 0
          val _tmpUseAuthAgent: String?
          if (_stmt.isNull(_columnIndexOfUseAuthAgent)) {
            _tmpUseAuthAgent = null
          } else {
            _tmpUseAuthAgent = _stmt.getText(_columnIndexOfUseAuthAgent)
          }
          val _tmpPostLogin: String?
          if (_stmt.isNull(_columnIndexOfPostLogin)) {
            _tmpPostLogin = null
          } else {
            _tmpPostLogin = _stmt.getText(_columnIndexOfPostLogin)
          }
          val _tmpPubkeyId: Long
          _tmpPubkeyId = _stmt.getLong(_columnIndexOfPubkeyId)
          val _tmpWantSession: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfWantSession).toInt()
          _tmpWantSession = _tmp_1 != 0
          val _tmpCompression: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfCompression).toInt()
          _tmpCompression = _tmp_2 != 0
          val _tmpStayConnected: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfStayConnected).toInt()
          _tmpStayConnected = _tmp_3 != 0
          val _tmpQuickDisconnect: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfQuickDisconnect).toInt()
          _tmpQuickDisconnect = _tmp_4 != 0
          val _tmpScrollbackLines: Int
          _tmpScrollbackLines = _stmt.getLong(_columnIndexOfScrollbackLines).toInt()
          val _tmpUseCtrlAltAsMetaKey: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfUseCtrlAltAsMetaKey).toInt()
          _tmpUseCtrlAltAsMetaKey = _tmp_5 != 0
          val _tmpJumpHostId: Long?
          if (_stmt.isNull(_columnIndexOfJumpHostId)) {
            _tmpJumpHostId = null
          } else {
            _tmpJumpHostId = _stmt.getLong(_columnIndexOfJumpHostId)
          }
          val _tmpProfileId: Long?
          if (_stmt.isNull(_columnIndexOfProfileId)) {
            _tmpProfileId = null
          } else {
            _tmpProfileId = _stmt.getLong(_columnIndexOfProfileId)
          }
          val _tmpIpVersion: String
          _tmpIpVersion = _stmt.getText(_columnIndexOfIpVersion)
          _item = Host(_tmpId,_tmpNickname,_tmpProtocol,_tmpUsername,_tmpHostname,_tmpPort,_tmpHostKeyAlgo,_tmpLastConnect,_tmpColor,_tmpUseKeys,_tmpUseAuthAgent,_tmpPostLogin,_tmpPubkeyId,_tmpWantSession,_tmpCompression,_tmpStayConnected,_tmpQuickDisconnect,_tmpScrollbackLines,_tmpUseCtrlAltAsMetaKey,_tmpJumpHostId,_tmpProfileId,_tmpIpVersion)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSortedByColor(): List<Host> {
    val _sql: String = "SELECT * FROM hosts ORDER BY color, nickname ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfHostname: Int = getColumnIndexOrThrow(_stmt, "hostname")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfHostKeyAlgo: Int = getColumnIndexOrThrow(_stmt, "host_key_algo")
        val _columnIndexOfLastConnect: Int = getColumnIndexOrThrow(_stmt, "last_connect")
        val _columnIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _columnIndexOfUseKeys: Int = getColumnIndexOrThrow(_stmt, "use_keys")
        val _columnIndexOfUseAuthAgent: Int = getColumnIndexOrThrow(_stmt, "use_auth_agent")
        val _columnIndexOfPostLogin: Int = getColumnIndexOrThrow(_stmt, "post_login")
        val _columnIndexOfPubkeyId: Int = getColumnIndexOrThrow(_stmt, "pubkey_id")
        val _columnIndexOfWantSession: Int = getColumnIndexOrThrow(_stmt, "want_session")
        val _columnIndexOfCompression: Int = getColumnIndexOrThrow(_stmt, "compression")
        val _columnIndexOfStayConnected: Int = getColumnIndexOrThrow(_stmt, "stay_connected")
        val _columnIndexOfQuickDisconnect: Int = getColumnIndexOrThrow(_stmt, "quick_disconnect")
        val _columnIndexOfScrollbackLines: Int = getColumnIndexOrThrow(_stmt, "scrollback_lines")
        val _columnIndexOfUseCtrlAltAsMetaKey: Int = getColumnIndexOrThrow(_stmt, "use_ctrl_alt_as_meta_key")
        val _columnIndexOfJumpHostId: Int = getColumnIndexOrThrow(_stmt, "jump_host_id")
        val _columnIndexOfProfileId: Int = getColumnIndexOrThrow(_stmt, "profile_id")
        val _columnIndexOfIpVersion: Int = getColumnIndexOrThrow(_stmt, "ip_version")
        val _result: MutableList<Host> = mutableListOf()
        while (_stmt.step()) {
          val _item: Host
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpProtocol: String
          _tmpProtocol = _stmt.getText(_columnIndexOfProtocol)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpHostname: String
          _tmpHostname = _stmt.getText(_columnIndexOfHostname)
          val _tmpPort: Int
          _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
          val _tmpHostKeyAlgo: String?
          if (_stmt.isNull(_columnIndexOfHostKeyAlgo)) {
            _tmpHostKeyAlgo = null
          } else {
            _tmpHostKeyAlgo = _stmt.getText(_columnIndexOfHostKeyAlgo)
          }
          val _tmpLastConnect: Long
          _tmpLastConnect = _stmt.getLong(_columnIndexOfLastConnect)
          val _tmpColor: String?
          if (_stmt.isNull(_columnIndexOfColor)) {
            _tmpColor = null
          } else {
            _tmpColor = _stmt.getText(_columnIndexOfColor)
          }
          val _tmpUseKeys: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfUseKeys).toInt()
          _tmpUseKeys = _tmp != 0
          val _tmpUseAuthAgent: String?
          if (_stmt.isNull(_columnIndexOfUseAuthAgent)) {
            _tmpUseAuthAgent = null
          } else {
            _tmpUseAuthAgent = _stmt.getText(_columnIndexOfUseAuthAgent)
          }
          val _tmpPostLogin: String?
          if (_stmt.isNull(_columnIndexOfPostLogin)) {
            _tmpPostLogin = null
          } else {
            _tmpPostLogin = _stmt.getText(_columnIndexOfPostLogin)
          }
          val _tmpPubkeyId: Long
          _tmpPubkeyId = _stmt.getLong(_columnIndexOfPubkeyId)
          val _tmpWantSession: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfWantSession).toInt()
          _tmpWantSession = _tmp_1 != 0
          val _tmpCompression: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfCompression).toInt()
          _tmpCompression = _tmp_2 != 0
          val _tmpStayConnected: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfStayConnected).toInt()
          _tmpStayConnected = _tmp_3 != 0
          val _tmpQuickDisconnect: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfQuickDisconnect).toInt()
          _tmpQuickDisconnect = _tmp_4 != 0
          val _tmpScrollbackLines: Int
          _tmpScrollbackLines = _stmt.getLong(_columnIndexOfScrollbackLines).toInt()
          val _tmpUseCtrlAltAsMetaKey: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfUseCtrlAltAsMetaKey).toInt()
          _tmpUseCtrlAltAsMetaKey = _tmp_5 != 0
          val _tmpJumpHostId: Long?
          if (_stmt.isNull(_columnIndexOfJumpHostId)) {
            _tmpJumpHostId = null
          } else {
            _tmpJumpHostId = _stmt.getLong(_columnIndexOfJumpHostId)
          }
          val _tmpProfileId: Long?
          if (_stmt.isNull(_columnIndexOfProfileId)) {
            _tmpProfileId = null
          } else {
            _tmpProfileId = _stmt.getLong(_columnIndexOfProfileId)
          }
          val _tmpIpVersion: String
          _tmpIpVersion = _stmt.getText(_columnIndexOfIpVersion)
          _item = Host(_tmpId,_tmpNickname,_tmpProtocol,_tmpUsername,_tmpHostname,_tmpPort,_tmpHostKeyAlgo,_tmpLastConnect,_tmpColor,_tmpUseKeys,_tmpUseAuthAgent,_tmpPostLogin,_tmpPubkeyId,_tmpWantSession,_tmpCompression,_tmpStayConnected,_tmpQuickDisconnect,_tmpScrollbackLines,_tmpUseCtrlAltAsMetaKey,_tmpJumpHostId,_tmpProfileId,_tmpIpVersion)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun findByKnownHost(hostname: String, port: Int): Host? {
    val _sql: String = """
        |
        |        SELECT h.* FROM hosts h
        |        JOIN known_hosts kh ON h.id = kh.host_id
        |        WHERE kh.hostname = ? AND kh.port = ?
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, hostname)
        _argIndex = 2
        _stmt.bindLong(_argIndex, port.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfHostname: Int = getColumnIndexOrThrow(_stmt, "hostname")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfHostKeyAlgo: Int = getColumnIndexOrThrow(_stmt, "host_key_algo")
        val _columnIndexOfLastConnect: Int = getColumnIndexOrThrow(_stmt, "last_connect")
        val _columnIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _columnIndexOfUseKeys: Int = getColumnIndexOrThrow(_stmt, "use_keys")
        val _columnIndexOfUseAuthAgent: Int = getColumnIndexOrThrow(_stmt, "use_auth_agent")
        val _columnIndexOfPostLogin: Int = getColumnIndexOrThrow(_stmt, "post_login")
        val _columnIndexOfPubkeyId: Int = getColumnIndexOrThrow(_stmt, "pubkey_id")
        val _columnIndexOfWantSession: Int = getColumnIndexOrThrow(_stmt, "want_session")
        val _columnIndexOfCompression: Int = getColumnIndexOrThrow(_stmt, "compression")
        val _columnIndexOfStayConnected: Int = getColumnIndexOrThrow(_stmt, "stay_connected")
        val _columnIndexOfQuickDisconnect: Int = getColumnIndexOrThrow(_stmt, "quick_disconnect")
        val _columnIndexOfScrollbackLines: Int = getColumnIndexOrThrow(_stmt, "scrollback_lines")
        val _columnIndexOfUseCtrlAltAsMetaKey: Int = getColumnIndexOrThrow(_stmt, "use_ctrl_alt_as_meta_key")
        val _columnIndexOfJumpHostId: Int = getColumnIndexOrThrow(_stmt, "jump_host_id")
        val _columnIndexOfProfileId: Int = getColumnIndexOrThrow(_stmt, "profile_id")
        val _columnIndexOfIpVersion: Int = getColumnIndexOrThrow(_stmt, "ip_version")
        val _result: Host?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpProtocol: String
          _tmpProtocol = _stmt.getText(_columnIndexOfProtocol)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpHostname: String
          _tmpHostname = _stmt.getText(_columnIndexOfHostname)
          val _tmpPort: Int
          _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
          val _tmpHostKeyAlgo: String?
          if (_stmt.isNull(_columnIndexOfHostKeyAlgo)) {
            _tmpHostKeyAlgo = null
          } else {
            _tmpHostKeyAlgo = _stmt.getText(_columnIndexOfHostKeyAlgo)
          }
          val _tmpLastConnect: Long
          _tmpLastConnect = _stmt.getLong(_columnIndexOfLastConnect)
          val _tmpColor: String?
          if (_stmt.isNull(_columnIndexOfColor)) {
            _tmpColor = null
          } else {
            _tmpColor = _stmt.getText(_columnIndexOfColor)
          }
          val _tmpUseKeys: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfUseKeys).toInt()
          _tmpUseKeys = _tmp != 0
          val _tmpUseAuthAgent: String?
          if (_stmt.isNull(_columnIndexOfUseAuthAgent)) {
            _tmpUseAuthAgent = null
          } else {
            _tmpUseAuthAgent = _stmt.getText(_columnIndexOfUseAuthAgent)
          }
          val _tmpPostLogin: String?
          if (_stmt.isNull(_columnIndexOfPostLogin)) {
            _tmpPostLogin = null
          } else {
            _tmpPostLogin = _stmt.getText(_columnIndexOfPostLogin)
          }
          val _tmpPubkeyId: Long
          _tmpPubkeyId = _stmt.getLong(_columnIndexOfPubkeyId)
          val _tmpWantSession: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfWantSession).toInt()
          _tmpWantSession = _tmp_1 != 0
          val _tmpCompression: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfCompression).toInt()
          _tmpCompression = _tmp_2 != 0
          val _tmpStayConnected: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfStayConnected).toInt()
          _tmpStayConnected = _tmp_3 != 0
          val _tmpQuickDisconnect: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfQuickDisconnect).toInt()
          _tmpQuickDisconnect = _tmp_4 != 0
          val _tmpScrollbackLines: Int
          _tmpScrollbackLines = _stmt.getLong(_columnIndexOfScrollbackLines).toInt()
          val _tmpUseCtrlAltAsMetaKey: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfUseCtrlAltAsMetaKey).toInt()
          _tmpUseCtrlAltAsMetaKey = _tmp_5 != 0
          val _tmpJumpHostId: Long?
          if (_stmt.isNull(_columnIndexOfJumpHostId)) {
            _tmpJumpHostId = null
          } else {
            _tmpJumpHostId = _stmt.getLong(_columnIndexOfJumpHostId)
          }
          val _tmpProfileId: Long?
          if (_stmt.isNull(_columnIndexOfProfileId)) {
            _tmpProfileId = null
          } else {
            _tmpProfileId = _stmt.getLong(_columnIndexOfProfileId)
          }
          val _tmpIpVersion: String
          _tmpIpVersion = _stmt.getText(_columnIndexOfIpVersion)
          _result = Host(_tmpId,_tmpNickname,_tmpProtocol,_tmpUsername,_tmpHostname,_tmpPort,_tmpHostKeyAlgo,_tmpLastConnect,_tmpColor,_tmpUseKeys,_tmpUseAuthAgent,_tmpPostLogin,_tmpPubkeyId,_tmpWantSession,_tmpCompression,_tmpStayConnected,_tmpQuickDisconnect,_tmpScrollbackLines,_tmpUseCtrlAltAsMetaKey,_tmpJumpHostId,_tmpProfileId,_tmpIpVersion)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSshHosts(): List<Host> {
    val _sql: String = "SELECT * FROM hosts WHERE protocol = 'ssh' ORDER BY nickname ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfHostname: Int = getColumnIndexOrThrow(_stmt, "hostname")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfHostKeyAlgo: Int = getColumnIndexOrThrow(_stmt, "host_key_algo")
        val _columnIndexOfLastConnect: Int = getColumnIndexOrThrow(_stmt, "last_connect")
        val _columnIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _columnIndexOfUseKeys: Int = getColumnIndexOrThrow(_stmt, "use_keys")
        val _columnIndexOfUseAuthAgent: Int = getColumnIndexOrThrow(_stmt, "use_auth_agent")
        val _columnIndexOfPostLogin: Int = getColumnIndexOrThrow(_stmt, "post_login")
        val _columnIndexOfPubkeyId: Int = getColumnIndexOrThrow(_stmt, "pubkey_id")
        val _columnIndexOfWantSession: Int = getColumnIndexOrThrow(_stmt, "want_session")
        val _columnIndexOfCompression: Int = getColumnIndexOrThrow(_stmt, "compression")
        val _columnIndexOfStayConnected: Int = getColumnIndexOrThrow(_stmt, "stay_connected")
        val _columnIndexOfQuickDisconnect: Int = getColumnIndexOrThrow(_stmt, "quick_disconnect")
        val _columnIndexOfScrollbackLines: Int = getColumnIndexOrThrow(_stmt, "scrollback_lines")
        val _columnIndexOfUseCtrlAltAsMetaKey: Int = getColumnIndexOrThrow(_stmt, "use_ctrl_alt_as_meta_key")
        val _columnIndexOfJumpHostId: Int = getColumnIndexOrThrow(_stmt, "jump_host_id")
        val _columnIndexOfProfileId: Int = getColumnIndexOrThrow(_stmt, "profile_id")
        val _columnIndexOfIpVersion: Int = getColumnIndexOrThrow(_stmt, "ip_version")
        val _result: MutableList<Host> = mutableListOf()
        while (_stmt.step()) {
          val _item: Host
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpProtocol: String
          _tmpProtocol = _stmt.getText(_columnIndexOfProtocol)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpHostname: String
          _tmpHostname = _stmt.getText(_columnIndexOfHostname)
          val _tmpPort: Int
          _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
          val _tmpHostKeyAlgo: String?
          if (_stmt.isNull(_columnIndexOfHostKeyAlgo)) {
            _tmpHostKeyAlgo = null
          } else {
            _tmpHostKeyAlgo = _stmt.getText(_columnIndexOfHostKeyAlgo)
          }
          val _tmpLastConnect: Long
          _tmpLastConnect = _stmt.getLong(_columnIndexOfLastConnect)
          val _tmpColor: String?
          if (_stmt.isNull(_columnIndexOfColor)) {
            _tmpColor = null
          } else {
            _tmpColor = _stmt.getText(_columnIndexOfColor)
          }
          val _tmpUseKeys: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfUseKeys).toInt()
          _tmpUseKeys = _tmp != 0
          val _tmpUseAuthAgent: String?
          if (_stmt.isNull(_columnIndexOfUseAuthAgent)) {
            _tmpUseAuthAgent = null
          } else {
            _tmpUseAuthAgent = _stmt.getText(_columnIndexOfUseAuthAgent)
          }
          val _tmpPostLogin: String?
          if (_stmt.isNull(_columnIndexOfPostLogin)) {
            _tmpPostLogin = null
          } else {
            _tmpPostLogin = _stmt.getText(_columnIndexOfPostLogin)
          }
          val _tmpPubkeyId: Long
          _tmpPubkeyId = _stmt.getLong(_columnIndexOfPubkeyId)
          val _tmpWantSession: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfWantSession).toInt()
          _tmpWantSession = _tmp_1 != 0
          val _tmpCompression: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfCompression).toInt()
          _tmpCompression = _tmp_2 != 0
          val _tmpStayConnected: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfStayConnected).toInt()
          _tmpStayConnected = _tmp_3 != 0
          val _tmpQuickDisconnect: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfQuickDisconnect).toInt()
          _tmpQuickDisconnect = _tmp_4 != 0
          val _tmpScrollbackLines: Int
          _tmpScrollbackLines = _stmt.getLong(_columnIndexOfScrollbackLines).toInt()
          val _tmpUseCtrlAltAsMetaKey: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfUseCtrlAltAsMetaKey).toInt()
          _tmpUseCtrlAltAsMetaKey = _tmp_5 != 0
          val _tmpJumpHostId: Long?
          if (_stmt.isNull(_columnIndexOfJumpHostId)) {
            _tmpJumpHostId = null
          } else {
            _tmpJumpHostId = _stmt.getLong(_columnIndexOfJumpHostId)
          }
          val _tmpProfileId: Long?
          if (_stmt.isNull(_columnIndexOfProfileId)) {
            _tmpProfileId = null
          } else {
            _tmpProfileId = _stmt.getLong(_columnIndexOfProfileId)
          }
          val _tmpIpVersion: String
          _tmpIpVersion = _stmt.getText(_columnIndexOfIpVersion)
          _item = Host(_tmpId,_tmpNickname,_tmpProtocol,_tmpUsername,_tmpHostname,_tmpPort,_tmpHostKeyAlgo,_tmpLastConnect,_tmpColor,_tmpUseKeys,_tmpUseAuthAgent,_tmpPostLogin,_tmpPubkeyId,_tmpWantSession,_tmpCompression,_tmpStayConnected,_tmpQuickDisconnect,_tmpScrollbackLines,_tmpUseCtrlAltAsMetaKey,_tmpJumpHostId,_tmpProfileId,_tmpIpVersion)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeSshHosts(): Flow<List<Host>> {
    val _sql: String = "SELECT * FROM hosts WHERE protocol = 'ssh' ORDER BY nickname ASC"
    return createFlow(__db, false, arrayOf("hosts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfHostname: Int = getColumnIndexOrThrow(_stmt, "hostname")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfHostKeyAlgo: Int = getColumnIndexOrThrow(_stmt, "host_key_algo")
        val _columnIndexOfLastConnect: Int = getColumnIndexOrThrow(_stmt, "last_connect")
        val _columnIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _columnIndexOfUseKeys: Int = getColumnIndexOrThrow(_stmt, "use_keys")
        val _columnIndexOfUseAuthAgent: Int = getColumnIndexOrThrow(_stmt, "use_auth_agent")
        val _columnIndexOfPostLogin: Int = getColumnIndexOrThrow(_stmt, "post_login")
        val _columnIndexOfPubkeyId: Int = getColumnIndexOrThrow(_stmt, "pubkey_id")
        val _columnIndexOfWantSession: Int = getColumnIndexOrThrow(_stmt, "want_session")
        val _columnIndexOfCompression: Int = getColumnIndexOrThrow(_stmt, "compression")
        val _columnIndexOfStayConnected: Int = getColumnIndexOrThrow(_stmt, "stay_connected")
        val _columnIndexOfQuickDisconnect: Int = getColumnIndexOrThrow(_stmt, "quick_disconnect")
        val _columnIndexOfScrollbackLines: Int = getColumnIndexOrThrow(_stmt, "scrollback_lines")
        val _columnIndexOfUseCtrlAltAsMetaKey: Int = getColumnIndexOrThrow(_stmt, "use_ctrl_alt_as_meta_key")
        val _columnIndexOfJumpHostId: Int = getColumnIndexOrThrow(_stmt, "jump_host_id")
        val _columnIndexOfProfileId: Int = getColumnIndexOrThrow(_stmt, "profile_id")
        val _columnIndexOfIpVersion: Int = getColumnIndexOrThrow(_stmt, "ip_version")
        val _result: MutableList<Host> = mutableListOf()
        while (_stmt.step()) {
          val _item: Host
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpProtocol: String
          _tmpProtocol = _stmt.getText(_columnIndexOfProtocol)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpHostname: String
          _tmpHostname = _stmt.getText(_columnIndexOfHostname)
          val _tmpPort: Int
          _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
          val _tmpHostKeyAlgo: String?
          if (_stmt.isNull(_columnIndexOfHostKeyAlgo)) {
            _tmpHostKeyAlgo = null
          } else {
            _tmpHostKeyAlgo = _stmt.getText(_columnIndexOfHostKeyAlgo)
          }
          val _tmpLastConnect: Long
          _tmpLastConnect = _stmt.getLong(_columnIndexOfLastConnect)
          val _tmpColor: String?
          if (_stmt.isNull(_columnIndexOfColor)) {
            _tmpColor = null
          } else {
            _tmpColor = _stmt.getText(_columnIndexOfColor)
          }
          val _tmpUseKeys: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfUseKeys).toInt()
          _tmpUseKeys = _tmp != 0
          val _tmpUseAuthAgent: String?
          if (_stmt.isNull(_columnIndexOfUseAuthAgent)) {
            _tmpUseAuthAgent = null
          } else {
            _tmpUseAuthAgent = _stmt.getText(_columnIndexOfUseAuthAgent)
          }
          val _tmpPostLogin: String?
          if (_stmt.isNull(_columnIndexOfPostLogin)) {
            _tmpPostLogin = null
          } else {
            _tmpPostLogin = _stmt.getText(_columnIndexOfPostLogin)
          }
          val _tmpPubkeyId: Long
          _tmpPubkeyId = _stmt.getLong(_columnIndexOfPubkeyId)
          val _tmpWantSession: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfWantSession).toInt()
          _tmpWantSession = _tmp_1 != 0
          val _tmpCompression: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfCompression).toInt()
          _tmpCompression = _tmp_2 != 0
          val _tmpStayConnected: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfStayConnected).toInt()
          _tmpStayConnected = _tmp_3 != 0
          val _tmpQuickDisconnect: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfQuickDisconnect).toInt()
          _tmpQuickDisconnect = _tmp_4 != 0
          val _tmpScrollbackLines: Int
          _tmpScrollbackLines = _stmt.getLong(_columnIndexOfScrollbackLines).toInt()
          val _tmpUseCtrlAltAsMetaKey: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfUseCtrlAltAsMetaKey).toInt()
          _tmpUseCtrlAltAsMetaKey = _tmp_5 != 0
          val _tmpJumpHostId: Long?
          if (_stmt.isNull(_columnIndexOfJumpHostId)) {
            _tmpJumpHostId = null
          } else {
            _tmpJumpHostId = _stmt.getLong(_columnIndexOfJumpHostId)
          }
          val _tmpProfileId: Long?
          if (_stmt.isNull(_columnIndexOfProfileId)) {
            _tmpProfileId = null
          } else {
            _tmpProfileId = _stmt.getLong(_columnIndexOfProfileId)
          }
          val _tmpIpVersion: String
          _tmpIpVersion = _stmt.getText(_columnIndexOfIpVersion)
          _item = Host(_tmpId,_tmpNickname,_tmpProtocol,_tmpUsername,_tmpHostname,_tmpPort,_tmpHostKeyAlgo,_tmpLastConnect,_tmpColor,_tmpUseKeys,_tmpUseAuthAgent,_tmpPostLogin,_tmpPubkeyId,_tmpWantSession,_tmpCompression,_tmpStayConnected,_tmpQuickDisconnect,_tmpScrollbackLines,_tmpUseCtrlAltAsMetaKey,_tmpJumpHostId,_tmpProfileId,_tmpIpVersion)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(hostId: Long) {
    val _sql: String = "DELETE FROM hosts WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, hostId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
