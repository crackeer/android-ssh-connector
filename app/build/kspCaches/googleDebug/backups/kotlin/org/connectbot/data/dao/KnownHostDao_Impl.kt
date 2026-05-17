package org.connectbot.`data`.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.ByteArray
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import org.connectbot.`data`.entity.KnownHost

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class KnownHostDao_Impl(
  __db: RoomDatabase,
) : KnownHostDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfKnownHost: EntityInsertAdapter<KnownHost>

  private val __deleteAdapterOfKnownHost: EntityDeleteOrUpdateAdapter<KnownHost>

  private val __updateAdapterOfKnownHost: EntityDeleteOrUpdateAdapter<KnownHost>
  init {
    this.__db = __db
    this.__insertAdapterOfKnownHost = object : EntityInsertAdapter<KnownHost>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `known_hosts` (`id`,`host_id`,`hostname`,`port`,`host_key_algo`,`host_key`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: KnownHost) {
        statement.bindLong(1, entity.id)
        val _tmpHostId: Long? = entity.hostId
        if (_tmpHostId == null) {
          statement.bindNull(2)
        } else {
          statement.bindLong(2, _tmpHostId)
        }
        statement.bindText(3, entity.hostname)
        statement.bindLong(4, entity.port.toLong())
        statement.bindText(5, entity.hostKeyAlgo)
        statement.bindBlob(6, entity.hostKey)
      }
    }
    this.__deleteAdapterOfKnownHost = object : EntityDeleteOrUpdateAdapter<KnownHost>() {
      protected override fun createQuery(): String = "DELETE FROM `known_hosts` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: KnownHost) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfKnownHost = object : EntityDeleteOrUpdateAdapter<KnownHost>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `known_hosts` SET `id` = ?,`host_id` = ?,`hostname` = ?,`port` = ?,`host_key_algo` = ?,`host_key` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: KnownHost) {
        statement.bindLong(1, entity.id)
        val _tmpHostId: Long? = entity.hostId
        if (_tmpHostId == null) {
          statement.bindNull(2)
        } else {
          statement.bindLong(2, _tmpHostId)
        }
        statement.bindText(3, entity.hostname)
        statement.bindLong(4, entity.port.toLong())
        statement.bindText(5, entity.hostKeyAlgo)
        statement.bindBlob(6, entity.hostKey)
        statement.bindLong(7, entity.id)
      }
    }
  }

  public override suspend fun insert(knownHost: KnownHost): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfKnownHost.insertAndReturnId(_connection, knownHost)
    _result
  }

  public override suspend fun delete(knownHost: KnownHost): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfKnownHost.handle(_connection, knownHost)
  }

  public override suspend fun update(knownHost: KnownHost): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfKnownHost.handle(_connection, knownHost)
  }

  public override suspend fun getByHostId(hostId: Long): List<KnownHost> {
    val _sql: String = "SELECT * FROM known_hosts WHERE host_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, hostId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfHostId: Int = getColumnIndexOrThrow(_stmt, "host_id")
        val _columnIndexOfHostname: Int = getColumnIndexOrThrow(_stmt, "hostname")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfHostKeyAlgo: Int = getColumnIndexOrThrow(_stmt, "host_key_algo")
        val _columnIndexOfHostKey: Int = getColumnIndexOrThrow(_stmt, "host_key")
        val _result: MutableList<KnownHost> = mutableListOf()
        while (_stmt.step()) {
          val _item: KnownHost
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpHostId: Long?
          if (_stmt.isNull(_columnIndexOfHostId)) {
            _tmpHostId = null
          } else {
            _tmpHostId = _stmt.getLong(_columnIndexOfHostId)
          }
          val _tmpHostname: String
          _tmpHostname = _stmt.getText(_columnIndexOfHostname)
          val _tmpPort: Int
          _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
          val _tmpHostKeyAlgo: String
          _tmpHostKeyAlgo = _stmt.getText(_columnIndexOfHostKeyAlgo)
          val _tmpHostKey: ByteArray
          _tmpHostKey = _stmt.getBlob(_columnIndexOfHostKey)
          _item = KnownHost(_tmpId,_tmpHostId,_tmpHostname,_tmpPort,_tmpHostKeyAlgo,_tmpHostKey)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByHostIdAndAlgo(hostId: Long, algo: String): List<KnownHost> {
    val _sql: String = "SELECT * FROM known_hosts WHERE host_id = ? AND host_key_algo = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, hostId)
        _argIndex = 2
        _stmt.bindText(_argIndex, algo)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfHostId: Int = getColumnIndexOrThrow(_stmt, "host_id")
        val _columnIndexOfHostname: Int = getColumnIndexOrThrow(_stmt, "hostname")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfHostKeyAlgo: Int = getColumnIndexOrThrow(_stmt, "host_key_algo")
        val _columnIndexOfHostKey: Int = getColumnIndexOrThrow(_stmt, "host_key")
        val _result: MutableList<KnownHost> = mutableListOf()
        while (_stmt.step()) {
          val _item: KnownHost
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpHostId: Long?
          if (_stmt.isNull(_columnIndexOfHostId)) {
            _tmpHostId = null
          } else {
            _tmpHostId = _stmt.getLong(_columnIndexOfHostId)
          }
          val _tmpHostname: String
          _tmpHostname = _stmt.getText(_columnIndexOfHostname)
          val _tmpPort: Int
          _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
          val _tmpHostKeyAlgo: String
          _tmpHostKeyAlgo = _stmt.getText(_columnIndexOfHostKeyAlgo)
          val _tmpHostKey: ByteArray
          _tmpHostKey = _stmt.getBlob(_columnIndexOfHostKey)
          _item = KnownHost(_tmpId,_tmpHostId,_tmpHostname,_tmpPort,_tmpHostKeyAlgo,_tmpHostKey)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByHostIdAlgoAndKey(
    hostId: Long,
    algo: String,
    key: ByteArray,
  ): KnownHost? {
    val _sql: String = "SELECT * FROM known_hosts WHERE host_id = ? AND host_key_algo = ? AND host_key = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, hostId)
        _argIndex = 2
        _stmt.bindText(_argIndex, algo)
        _argIndex = 3
        _stmt.bindBlob(_argIndex, key)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfHostId: Int = getColumnIndexOrThrow(_stmt, "host_id")
        val _columnIndexOfHostname: Int = getColumnIndexOrThrow(_stmt, "hostname")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfHostKeyAlgo: Int = getColumnIndexOrThrow(_stmt, "host_key_algo")
        val _columnIndexOfHostKey: Int = getColumnIndexOrThrow(_stmt, "host_key")
        val _result: KnownHost?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpHostId: Long?
          if (_stmt.isNull(_columnIndexOfHostId)) {
            _tmpHostId = null
          } else {
            _tmpHostId = _stmt.getLong(_columnIndexOfHostId)
          }
          val _tmpHostname: String
          _tmpHostname = _stmt.getText(_columnIndexOfHostname)
          val _tmpPort: Int
          _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
          val _tmpHostKeyAlgo: String
          _tmpHostKeyAlgo = _stmt.getText(_columnIndexOfHostKeyAlgo)
          val _tmpHostKey: ByteArray
          _tmpHostKey = _stmt.getBlob(_columnIndexOfHostKey)
          _result = KnownHost(_tmpId,_tmpHostId,_tmpHostname,_tmpPort,_tmpHostKeyAlgo,_tmpHostKey)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAll(): List<KnownHost> {
    val _sql: String = "SELECT * FROM known_hosts ORDER BY hostname, port"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfHostId: Int = getColumnIndexOrThrow(_stmt, "host_id")
        val _columnIndexOfHostname: Int = getColumnIndexOrThrow(_stmt, "hostname")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfHostKeyAlgo: Int = getColumnIndexOrThrow(_stmt, "host_key_algo")
        val _columnIndexOfHostKey: Int = getColumnIndexOrThrow(_stmt, "host_key")
        val _result: MutableList<KnownHost> = mutableListOf()
        while (_stmt.step()) {
          val _item: KnownHost
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpHostId: Long?
          if (_stmt.isNull(_columnIndexOfHostId)) {
            _tmpHostId = null
          } else {
            _tmpHostId = _stmt.getLong(_columnIndexOfHostId)
          }
          val _tmpHostname: String
          _tmpHostname = _stmt.getText(_columnIndexOfHostname)
          val _tmpPort: Int
          _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
          val _tmpHostKeyAlgo: String
          _tmpHostKeyAlgo = _stmt.getText(_columnIndexOfHostKeyAlgo)
          val _tmpHostKey: ByteArray
          _tmpHostKey = _stmt.getBlob(_columnIndexOfHostKey)
          _item = KnownHost(_tmpId,_tmpHostId,_tmpHostname,_tmpPort,_tmpHostKeyAlgo,_tmpHostKey)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByHostIdAndAlgo(hostId: Long, algo: String) {
    val _sql: String = "DELETE FROM known_hosts WHERE host_id = ? AND host_key_algo = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, hostId)
        _argIndex = 2
        _stmt.bindText(_argIndex, algo)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByHostnameAndPort(hostname: String, port: Int) {
    val _sql: String = "DELETE FROM known_hosts WHERE hostname = ? AND port = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, hostname)
        _argIndex = 2
        _stmt.bindLong(_argIndex, port.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByHostId(hostId: Long) {
    val _sql: String = "DELETE FROM known_hosts WHERE host_id = ?"
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
