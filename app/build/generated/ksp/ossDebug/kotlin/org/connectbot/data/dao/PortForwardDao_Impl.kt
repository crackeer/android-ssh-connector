package org.connectbot.`data`.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
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
import org.connectbot.`data`.entity.PortForward

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PortForwardDao_Impl(
  __db: RoomDatabase,
) : PortForwardDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPortForward: EntityInsertAdapter<PortForward>

  private val __deleteAdapterOfPortForward: EntityDeleteOrUpdateAdapter<PortForward>

  private val __updateAdapterOfPortForward: EntityDeleteOrUpdateAdapter<PortForward>
  init {
    this.__db = __db
    this.__insertAdapterOfPortForward = object : EntityInsertAdapter<PortForward>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `port_forwards` (`id`,`host_id`,`nickname`,`type`,`source_addr`,`source_port`,`dest_addr`,`dest_port`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PortForward) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.hostId)
        statement.bindText(3, entity.nickname)
        statement.bindText(4, entity.type)
        statement.bindText(5, entity.sourceAddr)
        statement.bindLong(6, entity.sourcePort.toLong())
        val _tmpDestAddr: String? = entity.destAddr
        if (_tmpDestAddr == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpDestAddr)
        }
        statement.bindLong(8, entity.destPort.toLong())
      }
    }
    this.__deleteAdapterOfPortForward = object : EntityDeleteOrUpdateAdapter<PortForward>() {
      protected override fun createQuery(): String = "DELETE FROM `port_forwards` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PortForward) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfPortForward = object : EntityDeleteOrUpdateAdapter<PortForward>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `port_forwards` SET `id` = ?,`host_id` = ?,`nickname` = ?,`type` = ?,`source_addr` = ?,`source_port` = ?,`dest_addr` = ?,`dest_port` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PortForward) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.hostId)
        statement.bindText(3, entity.nickname)
        statement.bindText(4, entity.type)
        statement.bindText(5, entity.sourceAddr)
        statement.bindLong(6, entity.sourcePort.toLong())
        val _tmpDestAddr: String? = entity.destAddr
        if (_tmpDestAddr == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpDestAddr)
        }
        statement.bindLong(8, entity.destPort.toLong())
        statement.bindLong(9, entity.id)
      }
    }
  }

  public override suspend fun insert(portForward: PortForward): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfPortForward.insertAndReturnId(_connection, portForward)
    _result
  }

  public override suspend fun delete(portForward: PortForward): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfPortForward.handle(_connection, portForward)
  }

  public override suspend fun update(portForward: PortForward): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfPortForward.handle(_connection, portForward)
  }

  public override fun observeByHost(hostId: Long): Flow<List<PortForward>> {
    val _sql: String = "SELECT * FROM port_forwards WHERE host_id = ? ORDER BY nickname ASC"
    return createFlow(__db, false, arrayOf("port_forwards")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, hostId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfHostId: Int = getColumnIndexOrThrow(_stmt, "host_id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfSourceAddr: Int = getColumnIndexOrThrow(_stmt, "source_addr")
        val _columnIndexOfSourcePort: Int = getColumnIndexOrThrow(_stmt, "source_port")
        val _columnIndexOfDestAddr: Int = getColumnIndexOrThrow(_stmt, "dest_addr")
        val _columnIndexOfDestPort: Int = getColumnIndexOrThrow(_stmt, "dest_port")
        val _result: MutableList<PortForward> = mutableListOf()
        while (_stmt.step()) {
          val _item: PortForward
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpHostId: Long
          _tmpHostId = _stmt.getLong(_columnIndexOfHostId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpSourceAddr: String
          _tmpSourceAddr = _stmt.getText(_columnIndexOfSourceAddr)
          val _tmpSourcePort: Int
          _tmpSourcePort = _stmt.getLong(_columnIndexOfSourcePort).toInt()
          val _tmpDestAddr: String?
          if (_stmt.isNull(_columnIndexOfDestAddr)) {
            _tmpDestAddr = null
          } else {
            _tmpDestAddr = _stmt.getText(_columnIndexOfDestAddr)
          }
          val _tmpDestPort: Int
          _tmpDestPort = _stmt.getLong(_columnIndexOfDestPort).toInt()
          _item = PortForward(_tmpId,_tmpHostId,_tmpNickname,_tmpType,_tmpSourceAddr,_tmpSourcePort,_tmpDestAddr,_tmpDestPort)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByHost(hostId: Long): List<PortForward> {
    val _sql: String = "SELECT * FROM port_forwards WHERE host_id = ? ORDER BY nickname ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, hostId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfHostId: Int = getColumnIndexOrThrow(_stmt, "host_id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfSourceAddr: Int = getColumnIndexOrThrow(_stmt, "source_addr")
        val _columnIndexOfSourcePort: Int = getColumnIndexOrThrow(_stmt, "source_port")
        val _columnIndexOfDestAddr: Int = getColumnIndexOrThrow(_stmt, "dest_addr")
        val _columnIndexOfDestPort: Int = getColumnIndexOrThrow(_stmt, "dest_port")
        val _result: MutableList<PortForward> = mutableListOf()
        while (_stmt.step()) {
          val _item: PortForward
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpHostId: Long
          _tmpHostId = _stmt.getLong(_columnIndexOfHostId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpSourceAddr: String
          _tmpSourceAddr = _stmt.getText(_columnIndexOfSourceAddr)
          val _tmpSourcePort: Int
          _tmpSourcePort = _stmt.getLong(_columnIndexOfSourcePort).toInt()
          val _tmpDestAddr: String?
          if (_stmt.isNull(_columnIndexOfDestAddr)) {
            _tmpDestAddr = null
          } else {
            _tmpDestAddr = _stmt.getText(_columnIndexOfDestAddr)
          }
          val _tmpDestPort: Int
          _tmpDestPort = _stmt.getLong(_columnIndexOfDestPort).toInt()
          _item = PortForward(_tmpId,_tmpHostId,_tmpNickname,_tmpType,_tmpSourceAddr,_tmpSourcePort,_tmpDestAddr,_tmpDestPort)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long): PortForward? {
    val _sql: String = "SELECT * FROM port_forwards WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfHostId: Int = getColumnIndexOrThrow(_stmt, "host_id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfSourceAddr: Int = getColumnIndexOrThrow(_stmt, "source_addr")
        val _columnIndexOfSourcePort: Int = getColumnIndexOrThrow(_stmt, "source_port")
        val _columnIndexOfDestAddr: Int = getColumnIndexOrThrow(_stmt, "dest_addr")
        val _columnIndexOfDestPort: Int = getColumnIndexOrThrow(_stmt, "dest_port")
        val _result: PortForward?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpHostId: Long
          _tmpHostId = _stmt.getLong(_columnIndexOfHostId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpSourceAddr: String
          _tmpSourceAddr = _stmt.getText(_columnIndexOfSourceAddr)
          val _tmpSourcePort: Int
          _tmpSourcePort = _stmt.getLong(_columnIndexOfSourcePort).toInt()
          val _tmpDestAddr: String?
          if (_stmt.isNull(_columnIndexOfDestAddr)) {
            _tmpDestAddr = null
          } else {
            _tmpDestAddr = _stmt.getText(_columnIndexOfDestAddr)
          }
          val _tmpDestPort: Int
          _tmpDestPort = _stmt.getLong(_columnIndexOfDestPort).toInt()
          _result = PortForward(_tmpId,_tmpHostId,_tmpNickname,_tmpType,_tmpSourceAddr,_tmpSourcePort,_tmpDestAddr,_tmpDestPort)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByHost(hostId: Long) {
    val _sql: String = "DELETE FROM port_forwards WHERE host_id = ?"
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
