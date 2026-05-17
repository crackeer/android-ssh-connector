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
import org.connectbot.`data`.entity.ColorPalette
import org.connectbot.`data`.entity.ColorScheme

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ColorSchemeDao_Impl(
  __db: RoomDatabase,
) : ColorSchemeDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfColorScheme: EntityInsertAdapter<ColorScheme>

  private val __insertAdapterOfColorScheme_1: EntityInsertAdapter<ColorScheme>

  private val __insertAdapterOfColorPalette: EntityInsertAdapter<ColorPalette>

  private val __insertAdapterOfColorPalette_1: EntityInsertAdapter<ColorPalette>

  private val __deleteAdapterOfColorScheme: EntityDeleteOrUpdateAdapter<ColorScheme>

  private val __updateAdapterOfColorScheme: EntityDeleteOrUpdateAdapter<ColorScheme>

  private val __updateAdapterOfColorPalette: EntityDeleteOrUpdateAdapter<ColorPalette>
  init {
    this.__db = __db
    this.__insertAdapterOfColorScheme = object : EntityInsertAdapter<ColorScheme>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `color_schemes` (`id`,`name`,`is_built_in`,`description`,`foreground`,`background`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ColorScheme) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmp: Int = if (entity.isBuiltIn) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        statement.bindText(4, entity.description)
        statement.bindLong(5, entity.foreground.toLong())
        statement.bindLong(6, entity.background.toLong())
      }
    }
    this.__insertAdapterOfColorScheme_1 = object : EntityInsertAdapter<ColorScheme>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `color_schemes` (`id`,`name`,`is_built_in`,`description`,`foreground`,`background`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ColorScheme) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmp: Int = if (entity.isBuiltIn) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        statement.bindText(4, entity.description)
        statement.bindLong(5, entity.foreground.toLong())
        statement.bindLong(6, entity.background.toLong())
      }
    }
    this.__insertAdapterOfColorPalette = object : EntityInsertAdapter<ColorPalette>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `color_palette` (`id`,`scheme_id`,`color_index`,`color`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ColorPalette) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.schemeId)
        statement.bindLong(3, entity.colorIndex.toLong())
        statement.bindLong(4, entity.color.toLong())
      }
    }
    this.__insertAdapterOfColorPalette_1 = object : EntityInsertAdapter<ColorPalette>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `color_palette` (`id`,`scheme_id`,`color_index`,`color`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ColorPalette) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.schemeId)
        statement.bindLong(3, entity.colorIndex.toLong())
        statement.bindLong(4, entity.color.toLong())
      }
    }
    this.__deleteAdapterOfColorScheme = object : EntityDeleteOrUpdateAdapter<ColorScheme>() {
      protected override fun createQuery(): String = "DELETE FROM `color_schemes` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ColorScheme) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfColorScheme = object : EntityDeleteOrUpdateAdapter<ColorScheme>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `color_schemes` SET `id` = ?,`name` = ?,`is_built_in` = ?,`description` = ?,`foreground` = ?,`background` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ColorScheme) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmp: Int = if (entity.isBuiltIn) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        statement.bindText(4, entity.description)
        statement.bindLong(5, entity.foreground.toLong())
        statement.bindLong(6, entity.background.toLong())
        statement.bindLong(7, entity.id)
      }
    }
    this.__updateAdapterOfColorPalette = object : EntityDeleteOrUpdateAdapter<ColorPalette>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `color_palette` SET `id` = ?,`scheme_id` = ?,`color_index` = ?,`color` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ColorPalette) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.schemeId)
        statement.bindLong(3, entity.colorIndex.toLong())
        statement.bindLong(4, entity.color.toLong())
        statement.bindLong(5, entity.id)
      }
    }
  }

  public override suspend fun insert(colorScheme: ColorScheme): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfColorScheme.insertAndReturnId(_connection, colorScheme)
    _result
  }

  public override suspend fun insertOrUpdate(color: ColorScheme): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfColorScheme_1.insertAndReturnId(_connection, color)
    _result
  }

  public override suspend fun insertColor(colorPalette: ColorPalette): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfColorPalette.insertAndReturnId(_connection, colorPalette)
    _result
  }

  public override suspend fun insertOrUpdateColor(color: ColorPalette): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfColorPalette_1.insertAndReturnId(_connection, color)
    _result
  }

  public override suspend fun delete(colorScheme: ColorScheme): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfColorScheme.handle(_connection, colorScheme)
  }

  public override suspend fun update(colorScheme: ColorScheme): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfColorScheme.handle(_connection, colorScheme)
  }

  public override suspend fun updateColor(colorPalette: ColorPalette): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfColorPalette.handle(_connection, colorPalette)
  }

  public override fun observeAll(): Flow<List<ColorScheme>> {
    val _sql: String = "SELECT * FROM color_schemes ORDER BY name ASC"
    return createFlow(__db, false, arrayOf("color_schemes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfIsBuiltIn: Int = getColumnIndexOrThrow(_stmt, "is_built_in")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfForeground: Int = getColumnIndexOrThrow(_stmt, "foreground")
        val _columnIndexOfBackground: Int = getColumnIndexOrThrow(_stmt, "background")
        val _result: MutableList<ColorScheme> = mutableListOf()
        while (_stmt.step()) {
          val _item: ColorScheme
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIsBuiltIn: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsBuiltIn).toInt()
          _tmpIsBuiltIn = _tmp != 0
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpForeground: Int
          _tmpForeground = _stmt.getLong(_columnIndexOfForeground).toInt()
          val _tmpBackground: Int
          _tmpBackground = _stmt.getLong(_columnIndexOfBackground).toInt()
          _item = ColorScheme(_tmpId,_tmpName,_tmpIsBuiltIn,_tmpDescription,_tmpForeground,_tmpBackground)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeById(schemeId: Long): Flow<ColorScheme?> {
    val _sql: String = "SELECT * FROM color_schemes WHERE id = ?"
    return createFlow(__db, false, arrayOf("color_schemes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, schemeId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfIsBuiltIn: Int = getColumnIndexOrThrow(_stmt, "is_built_in")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfForeground: Int = getColumnIndexOrThrow(_stmt, "foreground")
        val _columnIndexOfBackground: Int = getColumnIndexOrThrow(_stmt, "background")
        val _result: ColorScheme?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIsBuiltIn: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsBuiltIn).toInt()
          _tmpIsBuiltIn = _tmp != 0
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpForeground: Int
          _tmpForeground = _stmt.getLong(_columnIndexOfForeground).toInt()
          val _tmpBackground: Int
          _tmpBackground = _stmt.getLong(_columnIndexOfBackground).toInt()
          _result = ColorScheme(_tmpId,_tmpName,_tmpIsBuiltIn,_tmpDescription,_tmpForeground,_tmpBackground)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(schemeId: Long): ColorScheme? {
    val _sql: String = "SELECT * FROM color_schemes WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, schemeId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfIsBuiltIn: Int = getColumnIndexOrThrow(_stmt, "is_built_in")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfForeground: Int = getColumnIndexOrThrow(_stmt, "foreground")
        val _columnIndexOfBackground: Int = getColumnIndexOrThrow(_stmt, "background")
        val _result: ColorScheme?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIsBuiltIn: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsBuiltIn).toInt()
          _tmpIsBuiltIn = _tmp != 0
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpForeground: Int
          _tmpForeground = _stmt.getLong(_columnIndexOfForeground).toInt()
          val _tmpBackground: Int
          _tmpBackground = _stmt.getLong(_columnIndexOfBackground).toInt()
          _result = ColorScheme(_tmpId,_tmpName,_tmpIsBuiltIn,_tmpDescription,_tmpForeground,_tmpBackground)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAll(): List<ColorScheme> {
    val _sql: String = "SELECT * FROM color_schemes ORDER BY name ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfIsBuiltIn: Int = getColumnIndexOrThrow(_stmt, "is_built_in")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfForeground: Int = getColumnIndexOrThrow(_stmt, "foreground")
        val _columnIndexOfBackground: Int = getColumnIndexOrThrow(_stmt, "background")
        val _result: MutableList<ColorScheme> = mutableListOf()
        while (_stmt.step()) {
          val _item: ColorScheme
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIsBuiltIn: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsBuiltIn).toInt()
          _tmpIsBuiltIn = _tmp != 0
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpForeground: Int
          _tmpForeground = _stmt.getLong(_columnIndexOfForeground).toInt()
          val _tmpBackground: Int
          _tmpBackground = _stmt.getLong(_columnIndexOfBackground).toInt()
          _item = ColorScheme(_tmpId,_tmpName,_tmpIsBuiltIn,_tmpDescription,_tmpForeground,_tmpBackground)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getColors(schemeId: Long): List<ColorPalette> {
    val _sql: String = "SELECT * FROM color_palette WHERE scheme_id = ? ORDER BY color_index ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, schemeId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSchemeId: Int = getColumnIndexOrThrow(_stmt, "scheme_id")
        val _columnIndexOfColorIndex: Int = getColumnIndexOrThrow(_stmt, "color_index")
        val _columnIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _result: MutableList<ColorPalette> = mutableListOf()
        while (_stmt.step()) {
          val _item: ColorPalette
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSchemeId: Long
          _tmpSchemeId = _stmt.getLong(_columnIndexOfSchemeId)
          val _tmpColorIndex: Int
          _tmpColorIndex = _stmt.getLong(_columnIndexOfColorIndex).toInt()
          val _tmpColor: Int
          _tmpColor = _stmt.getLong(_columnIndexOfColor).toInt()
          _item = ColorPalette(_tmpId,_tmpSchemeId,_tmpColorIndex,_tmpColor)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeColors(schemeId: Long): Flow<List<ColorPalette>> {
    val _sql: String = "SELECT * FROM color_palette WHERE scheme_id = ? ORDER BY color_index ASC"
    return createFlow(__db, false, arrayOf("color_palette")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, schemeId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSchemeId: Int = getColumnIndexOrThrow(_stmt, "scheme_id")
        val _columnIndexOfColorIndex: Int = getColumnIndexOrThrow(_stmt, "color_index")
        val _columnIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _result: MutableList<ColorPalette> = mutableListOf()
        while (_stmt.step()) {
          val _item: ColorPalette
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSchemeId: Long
          _tmpSchemeId = _stmt.getLong(_columnIndexOfSchemeId)
          val _tmpColorIndex: Int
          _tmpColorIndex = _stmt.getLong(_columnIndexOfColorIndex).toInt()
          val _tmpColor: Int
          _tmpColor = _stmt.getLong(_columnIndexOfColor).toInt()
          _item = ColorPalette(_tmpId,_tmpSchemeId,_tmpColorIndex,_tmpColor)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getColor(schemeId: Long, colorIndex: Int): ColorPalette? {
    val _sql: String = "SELECT * FROM color_palette WHERE scheme_id = ? AND color_index = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, schemeId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, colorIndex.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSchemeId: Int = getColumnIndexOrThrow(_stmt, "scheme_id")
        val _columnIndexOfColorIndex: Int = getColumnIndexOrThrow(_stmt, "color_index")
        val _columnIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _result: ColorPalette?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSchemeId: Long
          _tmpSchemeId = _stmt.getLong(_columnIndexOfSchemeId)
          val _tmpColorIndex: Int
          _tmpColorIndex = _stmt.getLong(_columnIndexOfColorIndex).toInt()
          val _tmpColor: Int
          _tmpColor = _stmt.getLong(_columnIndexOfColor).toInt()
          _result = ColorPalette(_tmpId,_tmpSchemeId,_tmpColorIndex,_tmpColor)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun nameExists(name: String, excludeSchemeId: Long?): Boolean {
    val _sql: String = "SELECT EXISTS(SELECT 1 FROM color_schemes WHERE LOWER(name) = LOWER(?) AND (? IS NULL OR id != ?))"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, name)
        _argIndex = 2
        if (excludeSchemeId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, excludeSchemeId)
        }
        _argIndex = 3
        if (excludeSchemeId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, excludeSchemeId)
        }
        val _result: Boolean
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp != 0
        } else {
          _result = false
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteColor(schemeId: Long, colorIndex: Int) {
    val _sql: String = "DELETE FROM color_palette WHERE scheme_id = ? AND color_index = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, schemeId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, colorIndex.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAllColors(schemeId: Long) {
    val _sql: String = "DELETE FROM color_palette WHERE scheme_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, schemeId)
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
