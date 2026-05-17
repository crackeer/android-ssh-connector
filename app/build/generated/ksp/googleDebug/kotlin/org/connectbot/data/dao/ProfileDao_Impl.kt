package org.connectbot.`data`.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
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
import org.connectbot.`data`.entity.Profile

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ProfileDao_Impl(
  __db: RoomDatabase,
) : ProfileDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfProfile: EntityInsertAdapter<Profile>

  private val __insertAdapterOfProfile_1: EntityInsertAdapter<Profile>

  private val __deleteAdapterOfProfile: EntityDeleteOrUpdateAdapter<Profile>

  private val __updateAdapterOfProfile: EntityDeleteOrUpdateAdapter<Profile>
  init {
    this.__db = __db
    this.__insertAdapterOfProfile = object : EntityInsertAdapter<Profile>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `profiles` (`id`,`name`,`icon_color`,`color_scheme_id`,`font_family`,`font_size`,`del_key`,`encoding`,`emulation`,`force_size_rows`,`force_size_columns`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Profile) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmpIconColor: String? = entity.iconColor
        if (_tmpIconColor == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpIconColor)
        }
        statement.bindLong(4, entity.colorSchemeId)
        val _tmpFontFamily: String? = entity.fontFamily
        if (_tmpFontFamily == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpFontFamily)
        }
        statement.bindLong(6, entity.fontSize.toLong())
        statement.bindText(7, entity.delKey)
        statement.bindText(8, entity.encoding)
        statement.bindText(9, entity.emulation)
        val _tmpForceSizeRows: Int? = entity.forceSizeRows
        if (_tmpForceSizeRows == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpForceSizeRows.toLong())
        }
        val _tmpForceSizeColumns: Int? = entity.forceSizeColumns
        if (_tmpForceSizeColumns == null) {
          statement.bindNull(11)
        } else {
          statement.bindLong(11, _tmpForceSizeColumns.toLong())
        }
      }
    }
    this.__insertAdapterOfProfile_1 = object : EntityInsertAdapter<Profile>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `profiles` (`id`,`name`,`icon_color`,`color_scheme_id`,`font_family`,`font_size`,`del_key`,`encoding`,`emulation`,`force_size_rows`,`force_size_columns`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Profile) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmpIconColor: String? = entity.iconColor
        if (_tmpIconColor == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpIconColor)
        }
        statement.bindLong(4, entity.colorSchemeId)
        val _tmpFontFamily: String? = entity.fontFamily
        if (_tmpFontFamily == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpFontFamily)
        }
        statement.bindLong(6, entity.fontSize.toLong())
        statement.bindText(7, entity.delKey)
        statement.bindText(8, entity.encoding)
        statement.bindText(9, entity.emulation)
        val _tmpForceSizeRows: Int? = entity.forceSizeRows
        if (_tmpForceSizeRows == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpForceSizeRows.toLong())
        }
        val _tmpForceSizeColumns: Int? = entity.forceSizeColumns
        if (_tmpForceSizeColumns == null) {
          statement.bindNull(11)
        } else {
          statement.bindLong(11, _tmpForceSizeColumns.toLong())
        }
      }
    }
    this.__deleteAdapterOfProfile = object : EntityDeleteOrUpdateAdapter<Profile>() {
      protected override fun createQuery(): String = "DELETE FROM `profiles` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Profile) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfProfile = object : EntityDeleteOrUpdateAdapter<Profile>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `profiles` SET `id` = ?,`name` = ?,`icon_color` = ?,`color_scheme_id` = ?,`font_family` = ?,`font_size` = ?,`del_key` = ?,`encoding` = ?,`emulation` = ?,`force_size_rows` = ?,`force_size_columns` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Profile) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmpIconColor: String? = entity.iconColor
        if (_tmpIconColor == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpIconColor)
        }
        statement.bindLong(4, entity.colorSchemeId)
        val _tmpFontFamily: String? = entity.fontFamily
        if (_tmpFontFamily == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpFontFamily)
        }
        statement.bindLong(6, entity.fontSize.toLong())
        statement.bindText(7, entity.delKey)
        statement.bindText(8, entity.encoding)
        statement.bindText(9, entity.emulation)
        val _tmpForceSizeRows: Int? = entity.forceSizeRows
        if (_tmpForceSizeRows == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpForceSizeRows.toLong())
        }
        val _tmpForceSizeColumns: Int? = entity.forceSizeColumns
        if (_tmpForceSizeColumns == null) {
          statement.bindNull(11)
        } else {
          statement.bindLong(11, _tmpForceSizeColumns.toLong())
        }
        statement.bindLong(12, entity.id)
      }
    }
  }

  public override suspend fun insert(profile: Profile): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfProfile.insertAndReturnId(_connection, profile)
    _result
  }

  public override suspend fun insertOrUpdate(profile: Profile): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfProfile_1.insertAndReturnId(_connection, profile)
    _result
  }

  public override suspend fun delete(profile: Profile): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfProfile.handle(_connection, profile)
  }

  public override suspend fun update(profile: Profile): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfProfile.handle(_connection, profile)
  }

  public override fun observeAll(): Flow<List<Profile>> {
    val _sql: String = "SELECT * FROM profiles ORDER BY name ASC"
    return createFlow(__db, false, arrayOf("profiles")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfIconColor: Int = getColumnIndexOrThrow(_stmt, "icon_color")
        val _columnIndexOfColorSchemeId: Int = getColumnIndexOrThrow(_stmt, "color_scheme_id")
        val _columnIndexOfFontFamily: Int = getColumnIndexOrThrow(_stmt, "font_family")
        val _columnIndexOfFontSize: Int = getColumnIndexOrThrow(_stmt, "font_size")
        val _columnIndexOfDelKey: Int = getColumnIndexOrThrow(_stmt, "del_key")
        val _columnIndexOfEncoding: Int = getColumnIndexOrThrow(_stmt, "encoding")
        val _columnIndexOfEmulation: Int = getColumnIndexOrThrow(_stmt, "emulation")
        val _columnIndexOfForceSizeRows: Int = getColumnIndexOrThrow(_stmt, "force_size_rows")
        val _columnIndexOfForceSizeColumns: Int = getColumnIndexOrThrow(_stmt, "force_size_columns")
        val _result: MutableList<Profile> = mutableListOf()
        while (_stmt.step()) {
          val _item: Profile
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIconColor: String?
          if (_stmt.isNull(_columnIndexOfIconColor)) {
            _tmpIconColor = null
          } else {
            _tmpIconColor = _stmt.getText(_columnIndexOfIconColor)
          }
          val _tmpColorSchemeId: Long
          _tmpColorSchemeId = _stmt.getLong(_columnIndexOfColorSchemeId)
          val _tmpFontFamily: String?
          if (_stmt.isNull(_columnIndexOfFontFamily)) {
            _tmpFontFamily = null
          } else {
            _tmpFontFamily = _stmt.getText(_columnIndexOfFontFamily)
          }
          val _tmpFontSize: Int
          _tmpFontSize = _stmt.getLong(_columnIndexOfFontSize).toInt()
          val _tmpDelKey: String
          _tmpDelKey = _stmt.getText(_columnIndexOfDelKey)
          val _tmpEncoding: String
          _tmpEncoding = _stmt.getText(_columnIndexOfEncoding)
          val _tmpEmulation: String
          _tmpEmulation = _stmt.getText(_columnIndexOfEmulation)
          val _tmpForceSizeRows: Int?
          if (_stmt.isNull(_columnIndexOfForceSizeRows)) {
            _tmpForceSizeRows = null
          } else {
            _tmpForceSizeRows = _stmt.getLong(_columnIndexOfForceSizeRows).toInt()
          }
          val _tmpForceSizeColumns: Int?
          if (_stmt.isNull(_columnIndexOfForceSizeColumns)) {
            _tmpForceSizeColumns = null
          } else {
            _tmpForceSizeColumns = _stmt.getLong(_columnIndexOfForceSizeColumns).toInt()
          }
          _item = Profile(_tmpId,_tmpName,_tmpIconColor,_tmpColorSchemeId,_tmpFontFamily,_tmpFontSize,_tmpDelKey,_tmpEncoding,_tmpEmulation,_tmpForceSizeRows,_tmpForceSizeColumns)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeById(profileId: Long): Flow<Profile?> {
    val _sql: String = "SELECT * FROM profiles WHERE id = ?"
    return createFlow(__db, false, arrayOf("profiles")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, profileId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfIconColor: Int = getColumnIndexOrThrow(_stmt, "icon_color")
        val _columnIndexOfColorSchemeId: Int = getColumnIndexOrThrow(_stmt, "color_scheme_id")
        val _columnIndexOfFontFamily: Int = getColumnIndexOrThrow(_stmt, "font_family")
        val _columnIndexOfFontSize: Int = getColumnIndexOrThrow(_stmt, "font_size")
        val _columnIndexOfDelKey: Int = getColumnIndexOrThrow(_stmt, "del_key")
        val _columnIndexOfEncoding: Int = getColumnIndexOrThrow(_stmt, "encoding")
        val _columnIndexOfEmulation: Int = getColumnIndexOrThrow(_stmt, "emulation")
        val _columnIndexOfForceSizeRows: Int = getColumnIndexOrThrow(_stmt, "force_size_rows")
        val _columnIndexOfForceSizeColumns: Int = getColumnIndexOrThrow(_stmt, "force_size_columns")
        val _result: Profile?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIconColor: String?
          if (_stmt.isNull(_columnIndexOfIconColor)) {
            _tmpIconColor = null
          } else {
            _tmpIconColor = _stmt.getText(_columnIndexOfIconColor)
          }
          val _tmpColorSchemeId: Long
          _tmpColorSchemeId = _stmt.getLong(_columnIndexOfColorSchemeId)
          val _tmpFontFamily: String?
          if (_stmt.isNull(_columnIndexOfFontFamily)) {
            _tmpFontFamily = null
          } else {
            _tmpFontFamily = _stmt.getText(_columnIndexOfFontFamily)
          }
          val _tmpFontSize: Int
          _tmpFontSize = _stmt.getLong(_columnIndexOfFontSize).toInt()
          val _tmpDelKey: String
          _tmpDelKey = _stmt.getText(_columnIndexOfDelKey)
          val _tmpEncoding: String
          _tmpEncoding = _stmt.getText(_columnIndexOfEncoding)
          val _tmpEmulation: String
          _tmpEmulation = _stmt.getText(_columnIndexOfEmulation)
          val _tmpForceSizeRows: Int?
          if (_stmt.isNull(_columnIndexOfForceSizeRows)) {
            _tmpForceSizeRows = null
          } else {
            _tmpForceSizeRows = _stmt.getLong(_columnIndexOfForceSizeRows).toInt()
          }
          val _tmpForceSizeColumns: Int?
          if (_stmt.isNull(_columnIndexOfForceSizeColumns)) {
            _tmpForceSizeColumns = null
          } else {
            _tmpForceSizeColumns = _stmt.getLong(_columnIndexOfForceSizeColumns).toInt()
          }
          _result = Profile(_tmpId,_tmpName,_tmpIconColor,_tmpColorSchemeId,_tmpFontFamily,_tmpFontSize,_tmpDelKey,_tmpEncoding,_tmpEmulation,_tmpForceSizeRows,_tmpForceSizeColumns)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(profileId: Long): Profile? {
    val _sql: String = "SELECT * FROM profiles WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, profileId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfIconColor: Int = getColumnIndexOrThrow(_stmt, "icon_color")
        val _columnIndexOfColorSchemeId: Int = getColumnIndexOrThrow(_stmt, "color_scheme_id")
        val _columnIndexOfFontFamily: Int = getColumnIndexOrThrow(_stmt, "font_family")
        val _columnIndexOfFontSize: Int = getColumnIndexOrThrow(_stmt, "font_size")
        val _columnIndexOfDelKey: Int = getColumnIndexOrThrow(_stmt, "del_key")
        val _columnIndexOfEncoding: Int = getColumnIndexOrThrow(_stmt, "encoding")
        val _columnIndexOfEmulation: Int = getColumnIndexOrThrow(_stmt, "emulation")
        val _columnIndexOfForceSizeRows: Int = getColumnIndexOrThrow(_stmt, "force_size_rows")
        val _columnIndexOfForceSizeColumns: Int = getColumnIndexOrThrow(_stmt, "force_size_columns")
        val _result: Profile?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIconColor: String?
          if (_stmt.isNull(_columnIndexOfIconColor)) {
            _tmpIconColor = null
          } else {
            _tmpIconColor = _stmt.getText(_columnIndexOfIconColor)
          }
          val _tmpColorSchemeId: Long
          _tmpColorSchemeId = _stmt.getLong(_columnIndexOfColorSchemeId)
          val _tmpFontFamily: String?
          if (_stmt.isNull(_columnIndexOfFontFamily)) {
            _tmpFontFamily = null
          } else {
            _tmpFontFamily = _stmt.getText(_columnIndexOfFontFamily)
          }
          val _tmpFontSize: Int
          _tmpFontSize = _stmt.getLong(_columnIndexOfFontSize).toInt()
          val _tmpDelKey: String
          _tmpDelKey = _stmt.getText(_columnIndexOfDelKey)
          val _tmpEncoding: String
          _tmpEncoding = _stmt.getText(_columnIndexOfEncoding)
          val _tmpEmulation: String
          _tmpEmulation = _stmt.getText(_columnIndexOfEmulation)
          val _tmpForceSizeRows: Int?
          if (_stmt.isNull(_columnIndexOfForceSizeRows)) {
            _tmpForceSizeRows = null
          } else {
            _tmpForceSizeRows = _stmt.getLong(_columnIndexOfForceSizeRows).toInt()
          }
          val _tmpForceSizeColumns: Int?
          if (_stmt.isNull(_columnIndexOfForceSizeColumns)) {
            _tmpForceSizeColumns = null
          } else {
            _tmpForceSizeColumns = _stmt.getLong(_columnIndexOfForceSizeColumns).toInt()
          }
          _result = Profile(_tmpId,_tmpName,_tmpIconColor,_tmpColorSchemeId,_tmpFontFamily,_tmpFontSize,_tmpDelKey,_tmpEncoding,_tmpEmulation,_tmpForceSizeRows,_tmpForceSizeColumns)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAll(): List<Profile> {
    val _sql: String = "SELECT * FROM profiles ORDER BY name ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfIconColor: Int = getColumnIndexOrThrow(_stmt, "icon_color")
        val _columnIndexOfColorSchemeId: Int = getColumnIndexOrThrow(_stmt, "color_scheme_id")
        val _columnIndexOfFontFamily: Int = getColumnIndexOrThrow(_stmt, "font_family")
        val _columnIndexOfFontSize: Int = getColumnIndexOrThrow(_stmt, "font_size")
        val _columnIndexOfDelKey: Int = getColumnIndexOrThrow(_stmt, "del_key")
        val _columnIndexOfEncoding: Int = getColumnIndexOrThrow(_stmt, "encoding")
        val _columnIndexOfEmulation: Int = getColumnIndexOrThrow(_stmt, "emulation")
        val _columnIndexOfForceSizeRows: Int = getColumnIndexOrThrow(_stmt, "force_size_rows")
        val _columnIndexOfForceSizeColumns: Int = getColumnIndexOrThrow(_stmt, "force_size_columns")
        val _result: MutableList<Profile> = mutableListOf()
        while (_stmt.step()) {
          val _item: Profile
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIconColor: String?
          if (_stmt.isNull(_columnIndexOfIconColor)) {
            _tmpIconColor = null
          } else {
            _tmpIconColor = _stmt.getText(_columnIndexOfIconColor)
          }
          val _tmpColorSchemeId: Long
          _tmpColorSchemeId = _stmt.getLong(_columnIndexOfColorSchemeId)
          val _tmpFontFamily: String?
          if (_stmt.isNull(_columnIndexOfFontFamily)) {
            _tmpFontFamily = null
          } else {
            _tmpFontFamily = _stmt.getText(_columnIndexOfFontFamily)
          }
          val _tmpFontSize: Int
          _tmpFontSize = _stmt.getLong(_columnIndexOfFontSize).toInt()
          val _tmpDelKey: String
          _tmpDelKey = _stmt.getText(_columnIndexOfDelKey)
          val _tmpEncoding: String
          _tmpEncoding = _stmt.getText(_columnIndexOfEncoding)
          val _tmpEmulation: String
          _tmpEmulation = _stmt.getText(_columnIndexOfEmulation)
          val _tmpForceSizeRows: Int?
          if (_stmt.isNull(_columnIndexOfForceSizeRows)) {
            _tmpForceSizeRows = null
          } else {
            _tmpForceSizeRows = _stmt.getLong(_columnIndexOfForceSizeRows).toInt()
          }
          val _tmpForceSizeColumns: Int?
          if (_stmt.isNull(_columnIndexOfForceSizeColumns)) {
            _tmpForceSizeColumns = null
          } else {
            _tmpForceSizeColumns = _stmt.getLong(_columnIndexOfForceSizeColumns).toInt()
          }
          _item = Profile(_tmpId,_tmpName,_tmpIconColor,_tmpColorSchemeId,_tmpFontFamily,_tmpFontSize,_tmpDelKey,_tmpEncoding,_tmpEmulation,_tmpForceSizeRows,_tmpForceSizeColumns)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getDefault(): Profile? {
    val _sql: String = "SELECT * FROM profiles WHERE id = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfIconColor: Int = getColumnIndexOrThrow(_stmt, "icon_color")
        val _columnIndexOfColorSchemeId: Int = getColumnIndexOrThrow(_stmt, "color_scheme_id")
        val _columnIndexOfFontFamily: Int = getColumnIndexOrThrow(_stmt, "font_family")
        val _columnIndexOfFontSize: Int = getColumnIndexOrThrow(_stmt, "font_size")
        val _columnIndexOfDelKey: Int = getColumnIndexOrThrow(_stmt, "del_key")
        val _columnIndexOfEncoding: Int = getColumnIndexOrThrow(_stmt, "encoding")
        val _columnIndexOfEmulation: Int = getColumnIndexOrThrow(_stmt, "emulation")
        val _columnIndexOfForceSizeRows: Int = getColumnIndexOrThrow(_stmt, "force_size_rows")
        val _columnIndexOfForceSizeColumns: Int = getColumnIndexOrThrow(_stmt, "force_size_columns")
        val _result: Profile?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIconColor: String?
          if (_stmt.isNull(_columnIndexOfIconColor)) {
            _tmpIconColor = null
          } else {
            _tmpIconColor = _stmt.getText(_columnIndexOfIconColor)
          }
          val _tmpColorSchemeId: Long
          _tmpColorSchemeId = _stmt.getLong(_columnIndexOfColorSchemeId)
          val _tmpFontFamily: String?
          if (_stmt.isNull(_columnIndexOfFontFamily)) {
            _tmpFontFamily = null
          } else {
            _tmpFontFamily = _stmt.getText(_columnIndexOfFontFamily)
          }
          val _tmpFontSize: Int
          _tmpFontSize = _stmt.getLong(_columnIndexOfFontSize).toInt()
          val _tmpDelKey: String
          _tmpDelKey = _stmt.getText(_columnIndexOfDelKey)
          val _tmpEncoding: String
          _tmpEncoding = _stmt.getText(_columnIndexOfEncoding)
          val _tmpEmulation: String
          _tmpEmulation = _stmt.getText(_columnIndexOfEmulation)
          val _tmpForceSizeRows: Int?
          if (_stmt.isNull(_columnIndexOfForceSizeRows)) {
            _tmpForceSizeRows = null
          } else {
            _tmpForceSizeRows = _stmt.getLong(_columnIndexOfForceSizeRows).toInt()
          }
          val _tmpForceSizeColumns: Int?
          if (_stmt.isNull(_columnIndexOfForceSizeColumns)) {
            _tmpForceSizeColumns = null
          } else {
            _tmpForceSizeColumns = _stmt.getLong(_columnIndexOfForceSizeColumns).toInt()
          }
          _result = Profile(_tmpId,_tmpName,_tmpIconColor,_tmpColorSchemeId,_tmpFontFamily,_tmpFontSize,_tmpDelKey,_tmpEncoding,_tmpEmulation,_tmpForceSizeRows,_tmpForceSizeColumns)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun nameExists(name: String, excludeProfileId: Long?): Boolean {
    val _sql: String = "SELECT EXISTS(SELECT 1 FROM profiles WHERE LOWER(name) = LOWER(?) AND (? IS NULL OR id != ?))"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, name)
        _argIndex = 2
        if (excludeProfileId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, excludeProfileId)
        }
        _argIndex = 3
        if (excludeProfileId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, excludeProfileId)
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

  public override suspend fun getHostsUsingProfile(profileId: Long): Int {
    val _sql: String = "SELECT COUNT(*) FROM hosts WHERE profile_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, profileId)
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(profileId: Long): Int {
    val _sql: String = "DELETE FROM profiles WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, profileId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
