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
import org.connectbot.`data`.entity.QuickCommand

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class QuickCommandDao_Impl(
  __db: RoomDatabase,
) : QuickCommandDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfQuickCommand: EntityInsertAdapter<QuickCommand>

  private val __deleteAdapterOfQuickCommand: EntityDeleteOrUpdateAdapter<QuickCommand>

  private val __updateAdapterOfQuickCommand: EntityDeleteOrUpdateAdapter<QuickCommand>
  init {
    this.__db = __db
    this.__insertAdapterOfQuickCommand = object : EntityInsertAdapter<QuickCommand>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `quick_commands` (`id`,`label`,`command`,`sort_order`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: QuickCommand) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.label)
        statement.bindText(3, entity.command)
        statement.bindLong(4, entity.sortOrder.toLong())
      }
    }
    this.__deleteAdapterOfQuickCommand = object : EntityDeleteOrUpdateAdapter<QuickCommand>() {
      protected override fun createQuery(): String = "DELETE FROM `quick_commands` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: QuickCommand) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfQuickCommand = object : EntityDeleteOrUpdateAdapter<QuickCommand>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `quick_commands` SET `id` = ?,`label` = ?,`command` = ?,`sort_order` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: QuickCommand) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.label)
        statement.bindText(3, entity.command)
        statement.bindLong(4, entity.sortOrder.toLong())
        statement.bindLong(5, entity.id)
      }
    }
  }

  public override suspend fun insert(command: QuickCommand): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfQuickCommand.insertAndReturnId(_connection, command)
    _result
  }

  public override suspend fun delete(command: QuickCommand): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfQuickCommand.handle(_connection, command)
  }

  public override suspend fun update(command: QuickCommand): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfQuickCommand.handle(_connection, command)
  }

  public override fun observeAll(): Flow<List<QuickCommand>> {
    val _sql: String = "SELECT * FROM quick_commands ORDER BY sort_order ASC, label ASC"
    return createFlow(__db, false, arrayOf("quick_commands")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfLabel: Int = getColumnIndexOrThrow(_stmt, "label")
        val _columnIndexOfCommand: Int = getColumnIndexOrThrow(_stmt, "command")
        val _columnIndexOfSortOrder: Int = getColumnIndexOrThrow(_stmt, "sort_order")
        val _result: MutableList<QuickCommand> = mutableListOf()
        while (_stmt.step()) {
          val _item: QuickCommand
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpLabel: String
          _tmpLabel = _stmt.getText(_columnIndexOfLabel)
          val _tmpCommand: String
          _tmpCommand = _stmt.getText(_columnIndexOfCommand)
          val _tmpSortOrder: Int
          _tmpSortOrder = _stmt.getLong(_columnIndexOfSortOrder).toInt()
          _item = QuickCommand(_tmpId,_tmpLabel,_tmpCommand,_tmpSortOrder)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAll(): List<QuickCommand> {
    val _sql: String = "SELECT * FROM quick_commands ORDER BY sort_order ASC, label ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfLabel: Int = getColumnIndexOrThrow(_stmt, "label")
        val _columnIndexOfCommand: Int = getColumnIndexOrThrow(_stmt, "command")
        val _columnIndexOfSortOrder: Int = getColumnIndexOrThrow(_stmt, "sort_order")
        val _result: MutableList<QuickCommand> = mutableListOf()
        while (_stmt.step()) {
          val _item: QuickCommand
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpLabel: String
          _tmpLabel = _stmt.getText(_columnIndexOfLabel)
          val _tmpCommand: String
          _tmpCommand = _stmt.getText(_columnIndexOfCommand)
          val _tmpSortOrder: Int
          _tmpSortOrder = _stmt.getLong(_columnIndexOfSortOrder).toInt()
          _item = QuickCommand(_tmpId,_tmpLabel,_tmpCommand,_tmpSortOrder)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long): QuickCommand? {
    val _sql: String = "SELECT * FROM quick_commands WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfLabel: Int = getColumnIndexOrThrow(_stmt, "label")
        val _columnIndexOfCommand: Int = getColumnIndexOrThrow(_stmt, "command")
        val _columnIndexOfSortOrder: Int = getColumnIndexOrThrow(_stmt, "sort_order")
        val _result: QuickCommand?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpLabel: String
          _tmpLabel = _stmt.getText(_columnIndexOfLabel)
          val _tmpCommand: String
          _tmpCommand = _stmt.getText(_columnIndexOfCommand)
          val _tmpSortOrder: Int
          _tmpSortOrder = _stmt.getLong(_columnIndexOfSortOrder).toInt()
          _result = QuickCommand(_tmpId,_tmpLabel,_tmpCommand,_tmpSortOrder)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
