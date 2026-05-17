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
import kotlinx.coroutines.flow.Flow
import org.connectbot.`data`.Converters
import org.connectbot.`data`.entity.KeyStorageType
import org.connectbot.`data`.entity.Pubkey

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PubkeyDao_Impl(
  __db: RoomDatabase,
) : PubkeyDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPubkey: EntityInsertAdapter<Pubkey>

  private val __converters: Converters = Converters()

  private val __deleteAdapterOfPubkey: EntityDeleteOrUpdateAdapter<Pubkey>

  private val __updateAdapterOfPubkey: EntityDeleteOrUpdateAdapter<Pubkey>
  init {
    this.__db = __db
    this.__insertAdapterOfPubkey = object : EntityInsertAdapter<Pubkey>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `pubkeys` (`id`,`nickname`,`type`,`private_key`,`public_key`,`encrypted`,`startup`,`confirmation`,`created_date`,`storage_type`,`allow_backup`,`keystore_alias`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Pubkey) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.nickname)
        statement.bindText(3, entity.type)
        val _tmpPrivateKey: ByteArray? = entity.privateKey
        if (_tmpPrivateKey == null) {
          statement.bindNull(4)
        } else {
          statement.bindBlob(4, _tmpPrivateKey)
        }
        statement.bindBlob(5, entity.publicKey)
        val _tmp: Int = if (entity.encrypted) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        val _tmp_1: Int = if (entity.startup) 1 else 0
        statement.bindLong(7, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.confirmation) 1 else 0
        statement.bindLong(8, _tmp_2.toLong())
        statement.bindLong(9, entity.createdDate)
        val _tmp_3: String = __converters.fromKeyStorageType(entity.storageType)
        statement.bindText(10, _tmp_3)
        val _tmp_4: Int = if (entity.allowBackup) 1 else 0
        statement.bindLong(11, _tmp_4.toLong())
        val _tmpKeystoreAlias: String? = entity.keystoreAlias
        if (_tmpKeystoreAlias == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpKeystoreAlias)
        }
      }
    }
    this.__deleteAdapterOfPubkey = object : EntityDeleteOrUpdateAdapter<Pubkey>() {
      protected override fun createQuery(): String = "DELETE FROM `pubkeys` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Pubkey) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfPubkey = object : EntityDeleteOrUpdateAdapter<Pubkey>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `pubkeys` SET `id` = ?,`nickname` = ?,`type` = ?,`private_key` = ?,`public_key` = ?,`encrypted` = ?,`startup` = ?,`confirmation` = ?,`created_date` = ?,`storage_type` = ?,`allow_backup` = ?,`keystore_alias` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Pubkey) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.nickname)
        statement.bindText(3, entity.type)
        val _tmpPrivateKey: ByteArray? = entity.privateKey
        if (_tmpPrivateKey == null) {
          statement.bindNull(4)
        } else {
          statement.bindBlob(4, _tmpPrivateKey)
        }
        statement.bindBlob(5, entity.publicKey)
        val _tmp: Int = if (entity.encrypted) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        val _tmp_1: Int = if (entity.startup) 1 else 0
        statement.bindLong(7, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.confirmation) 1 else 0
        statement.bindLong(8, _tmp_2.toLong())
        statement.bindLong(9, entity.createdDate)
        val _tmp_3: String = __converters.fromKeyStorageType(entity.storageType)
        statement.bindText(10, _tmp_3)
        val _tmp_4: Int = if (entity.allowBackup) 1 else 0
        statement.bindLong(11, _tmp_4.toLong())
        val _tmpKeystoreAlias: String? = entity.keystoreAlias
        if (_tmpKeystoreAlias == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpKeystoreAlias)
        }
        statement.bindLong(13, entity.id)
      }
    }
  }

  public override suspend fun insert(pubkey: Pubkey): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfPubkey.insertAndReturnId(_connection, pubkey)
    _result
  }

  public override suspend fun delete(pubkey: Pubkey): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfPubkey.handle(_connection, pubkey)
  }

  public override suspend fun update(pubkey: Pubkey): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfPubkey.handle(_connection, pubkey)
  }

  public override fun observeAll(): Flow<List<Pubkey>> {
    val _sql: String = "SELECT * FROM pubkeys ORDER BY nickname ASC"
    return createFlow(__db, false, arrayOf("pubkeys")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfPrivateKey: Int = getColumnIndexOrThrow(_stmt, "private_key")
        val _columnIndexOfPublicKey: Int = getColumnIndexOrThrow(_stmt, "public_key")
        val _columnIndexOfEncrypted: Int = getColumnIndexOrThrow(_stmt, "encrypted")
        val _columnIndexOfStartup: Int = getColumnIndexOrThrow(_stmt, "startup")
        val _columnIndexOfConfirmation: Int = getColumnIndexOrThrow(_stmt, "confirmation")
        val _columnIndexOfCreatedDate: Int = getColumnIndexOrThrow(_stmt, "created_date")
        val _columnIndexOfStorageType: Int = getColumnIndexOrThrow(_stmt, "storage_type")
        val _columnIndexOfAllowBackup: Int = getColumnIndexOrThrow(_stmt, "allow_backup")
        val _columnIndexOfKeystoreAlias: Int = getColumnIndexOrThrow(_stmt, "keystore_alias")
        val _result: MutableList<Pubkey> = mutableListOf()
        while (_stmt.step()) {
          val _item: Pubkey
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpPrivateKey: ByteArray?
          if (_stmt.isNull(_columnIndexOfPrivateKey)) {
            _tmpPrivateKey = null
          } else {
            _tmpPrivateKey = _stmt.getBlob(_columnIndexOfPrivateKey)
          }
          val _tmpPublicKey: ByteArray
          _tmpPublicKey = _stmt.getBlob(_columnIndexOfPublicKey)
          val _tmpEncrypted: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEncrypted).toInt()
          _tmpEncrypted = _tmp != 0
          val _tmpStartup: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfStartup).toInt()
          _tmpStartup = _tmp_1 != 0
          val _tmpConfirmation: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfConfirmation).toInt()
          _tmpConfirmation = _tmp_2 != 0
          val _tmpCreatedDate: Long
          _tmpCreatedDate = _stmt.getLong(_columnIndexOfCreatedDate)
          val _tmpStorageType: KeyStorageType
          val _tmp_3: String
          _tmp_3 = _stmt.getText(_columnIndexOfStorageType)
          _tmpStorageType = __converters.toKeyStorageType(_tmp_3)
          val _tmpAllowBackup: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfAllowBackup).toInt()
          _tmpAllowBackup = _tmp_4 != 0
          val _tmpKeystoreAlias: String?
          if (_stmt.isNull(_columnIndexOfKeystoreAlias)) {
            _tmpKeystoreAlias = null
          } else {
            _tmpKeystoreAlias = _stmt.getText(_columnIndexOfKeystoreAlias)
          }
          _item = Pubkey(_tmpId,_tmpNickname,_tmpType,_tmpPrivateKey,_tmpPublicKey,_tmpEncrypted,_tmpStartup,_tmpConfirmation,_tmpCreatedDate,_tmpStorageType,_tmpAllowBackup,_tmpKeystoreAlias)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeByStorageType(type: KeyStorageType): Flow<List<Pubkey>> {
    val _sql: String = "SELECT * FROM pubkeys WHERE storage_type = ? ORDER BY nickname ASC"
    return createFlow(__db, false, arrayOf("pubkeys")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: String = __converters.fromKeyStorageType(type)
        _stmt.bindText(_argIndex, _tmp)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfPrivateKey: Int = getColumnIndexOrThrow(_stmt, "private_key")
        val _columnIndexOfPublicKey: Int = getColumnIndexOrThrow(_stmt, "public_key")
        val _columnIndexOfEncrypted: Int = getColumnIndexOrThrow(_stmt, "encrypted")
        val _columnIndexOfStartup: Int = getColumnIndexOrThrow(_stmt, "startup")
        val _columnIndexOfConfirmation: Int = getColumnIndexOrThrow(_stmt, "confirmation")
        val _columnIndexOfCreatedDate: Int = getColumnIndexOrThrow(_stmt, "created_date")
        val _columnIndexOfStorageType: Int = getColumnIndexOrThrow(_stmt, "storage_type")
        val _columnIndexOfAllowBackup: Int = getColumnIndexOrThrow(_stmt, "allow_backup")
        val _columnIndexOfKeystoreAlias: Int = getColumnIndexOrThrow(_stmt, "keystore_alias")
        val _result: MutableList<Pubkey> = mutableListOf()
        while (_stmt.step()) {
          val _item: Pubkey
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpPrivateKey: ByteArray?
          if (_stmt.isNull(_columnIndexOfPrivateKey)) {
            _tmpPrivateKey = null
          } else {
            _tmpPrivateKey = _stmt.getBlob(_columnIndexOfPrivateKey)
          }
          val _tmpPublicKey: ByteArray
          _tmpPublicKey = _stmt.getBlob(_columnIndexOfPublicKey)
          val _tmpEncrypted: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfEncrypted).toInt()
          _tmpEncrypted = _tmp_1 != 0
          val _tmpStartup: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfStartup).toInt()
          _tmpStartup = _tmp_2 != 0
          val _tmpConfirmation: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfConfirmation).toInt()
          _tmpConfirmation = _tmp_3 != 0
          val _tmpCreatedDate: Long
          _tmpCreatedDate = _stmt.getLong(_columnIndexOfCreatedDate)
          val _tmpStorageType: KeyStorageType
          val _tmp_4: String
          _tmp_4 = _stmt.getText(_columnIndexOfStorageType)
          _tmpStorageType = __converters.toKeyStorageType(_tmp_4)
          val _tmpAllowBackup: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfAllowBackup).toInt()
          _tmpAllowBackup = _tmp_5 != 0
          val _tmpKeystoreAlias: String?
          if (_stmt.isNull(_columnIndexOfKeystoreAlias)) {
            _tmpKeystoreAlias = null
          } else {
            _tmpKeystoreAlias = _stmt.getText(_columnIndexOfKeystoreAlias)
          }
          _item = Pubkey(_tmpId,_tmpNickname,_tmpType,_tmpPrivateKey,_tmpPublicKey,_tmpEncrypted,_tmpStartup,_tmpConfirmation,_tmpCreatedDate,_tmpStorageType,_tmpAllowBackup,_tmpKeystoreAlias)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeById(pubkeyId: Long): Flow<Pubkey?> {
    val _sql: String = "SELECT * FROM pubkeys WHERE id = ?"
    return createFlow(__db, false, arrayOf("pubkeys")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, pubkeyId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfPrivateKey: Int = getColumnIndexOrThrow(_stmt, "private_key")
        val _columnIndexOfPublicKey: Int = getColumnIndexOrThrow(_stmt, "public_key")
        val _columnIndexOfEncrypted: Int = getColumnIndexOrThrow(_stmt, "encrypted")
        val _columnIndexOfStartup: Int = getColumnIndexOrThrow(_stmt, "startup")
        val _columnIndexOfConfirmation: Int = getColumnIndexOrThrow(_stmt, "confirmation")
        val _columnIndexOfCreatedDate: Int = getColumnIndexOrThrow(_stmt, "created_date")
        val _columnIndexOfStorageType: Int = getColumnIndexOrThrow(_stmt, "storage_type")
        val _columnIndexOfAllowBackup: Int = getColumnIndexOrThrow(_stmt, "allow_backup")
        val _columnIndexOfKeystoreAlias: Int = getColumnIndexOrThrow(_stmt, "keystore_alias")
        val _result: Pubkey?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpPrivateKey: ByteArray?
          if (_stmt.isNull(_columnIndexOfPrivateKey)) {
            _tmpPrivateKey = null
          } else {
            _tmpPrivateKey = _stmt.getBlob(_columnIndexOfPrivateKey)
          }
          val _tmpPublicKey: ByteArray
          _tmpPublicKey = _stmt.getBlob(_columnIndexOfPublicKey)
          val _tmpEncrypted: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEncrypted).toInt()
          _tmpEncrypted = _tmp != 0
          val _tmpStartup: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfStartup).toInt()
          _tmpStartup = _tmp_1 != 0
          val _tmpConfirmation: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfConfirmation).toInt()
          _tmpConfirmation = _tmp_2 != 0
          val _tmpCreatedDate: Long
          _tmpCreatedDate = _stmt.getLong(_columnIndexOfCreatedDate)
          val _tmpStorageType: KeyStorageType
          val _tmp_3: String
          _tmp_3 = _stmt.getText(_columnIndexOfStorageType)
          _tmpStorageType = __converters.toKeyStorageType(_tmp_3)
          val _tmpAllowBackup: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfAllowBackup).toInt()
          _tmpAllowBackup = _tmp_4 != 0
          val _tmpKeystoreAlias: String?
          if (_stmt.isNull(_columnIndexOfKeystoreAlias)) {
            _tmpKeystoreAlias = null
          } else {
            _tmpKeystoreAlias = _stmt.getText(_columnIndexOfKeystoreAlias)
          }
          _result = Pubkey(_tmpId,_tmpNickname,_tmpType,_tmpPrivateKey,_tmpPublicKey,_tmpEncrypted,_tmpStartup,_tmpConfirmation,_tmpCreatedDate,_tmpStorageType,_tmpAllowBackup,_tmpKeystoreAlias)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(pubkeyId: Long): Pubkey? {
    val _sql: String = "SELECT * FROM pubkeys WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, pubkeyId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfPrivateKey: Int = getColumnIndexOrThrow(_stmt, "private_key")
        val _columnIndexOfPublicKey: Int = getColumnIndexOrThrow(_stmt, "public_key")
        val _columnIndexOfEncrypted: Int = getColumnIndexOrThrow(_stmt, "encrypted")
        val _columnIndexOfStartup: Int = getColumnIndexOrThrow(_stmt, "startup")
        val _columnIndexOfConfirmation: Int = getColumnIndexOrThrow(_stmt, "confirmation")
        val _columnIndexOfCreatedDate: Int = getColumnIndexOrThrow(_stmt, "created_date")
        val _columnIndexOfStorageType: Int = getColumnIndexOrThrow(_stmt, "storage_type")
        val _columnIndexOfAllowBackup: Int = getColumnIndexOrThrow(_stmt, "allow_backup")
        val _columnIndexOfKeystoreAlias: Int = getColumnIndexOrThrow(_stmt, "keystore_alias")
        val _result: Pubkey?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpPrivateKey: ByteArray?
          if (_stmt.isNull(_columnIndexOfPrivateKey)) {
            _tmpPrivateKey = null
          } else {
            _tmpPrivateKey = _stmt.getBlob(_columnIndexOfPrivateKey)
          }
          val _tmpPublicKey: ByteArray
          _tmpPublicKey = _stmt.getBlob(_columnIndexOfPublicKey)
          val _tmpEncrypted: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEncrypted).toInt()
          _tmpEncrypted = _tmp != 0
          val _tmpStartup: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfStartup).toInt()
          _tmpStartup = _tmp_1 != 0
          val _tmpConfirmation: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfConfirmation).toInt()
          _tmpConfirmation = _tmp_2 != 0
          val _tmpCreatedDate: Long
          _tmpCreatedDate = _stmt.getLong(_columnIndexOfCreatedDate)
          val _tmpStorageType: KeyStorageType
          val _tmp_3: String
          _tmp_3 = _stmt.getText(_columnIndexOfStorageType)
          _tmpStorageType = __converters.toKeyStorageType(_tmp_3)
          val _tmpAllowBackup: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfAllowBackup).toInt()
          _tmpAllowBackup = _tmp_4 != 0
          val _tmpKeystoreAlias: String?
          if (_stmt.isNull(_columnIndexOfKeystoreAlias)) {
            _tmpKeystoreAlias = null
          } else {
            _tmpKeystoreAlias = _stmt.getText(_columnIndexOfKeystoreAlias)
          }
          _result = Pubkey(_tmpId,_tmpNickname,_tmpType,_tmpPrivateKey,_tmpPublicKey,_tmpEncrypted,_tmpStartup,_tmpConfirmation,_tmpCreatedDate,_tmpStorageType,_tmpAllowBackup,_tmpKeystoreAlias)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByNickname(nickname: String): Pubkey? {
    val _sql: String = "SELECT * FROM pubkeys WHERE nickname = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, nickname)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfPrivateKey: Int = getColumnIndexOrThrow(_stmt, "private_key")
        val _columnIndexOfPublicKey: Int = getColumnIndexOrThrow(_stmt, "public_key")
        val _columnIndexOfEncrypted: Int = getColumnIndexOrThrow(_stmt, "encrypted")
        val _columnIndexOfStartup: Int = getColumnIndexOrThrow(_stmt, "startup")
        val _columnIndexOfConfirmation: Int = getColumnIndexOrThrow(_stmt, "confirmation")
        val _columnIndexOfCreatedDate: Int = getColumnIndexOrThrow(_stmt, "created_date")
        val _columnIndexOfStorageType: Int = getColumnIndexOrThrow(_stmt, "storage_type")
        val _columnIndexOfAllowBackup: Int = getColumnIndexOrThrow(_stmt, "allow_backup")
        val _columnIndexOfKeystoreAlias: Int = getColumnIndexOrThrow(_stmt, "keystore_alias")
        val _result: Pubkey?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpPrivateKey: ByteArray?
          if (_stmt.isNull(_columnIndexOfPrivateKey)) {
            _tmpPrivateKey = null
          } else {
            _tmpPrivateKey = _stmt.getBlob(_columnIndexOfPrivateKey)
          }
          val _tmpPublicKey: ByteArray
          _tmpPublicKey = _stmt.getBlob(_columnIndexOfPublicKey)
          val _tmpEncrypted: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEncrypted).toInt()
          _tmpEncrypted = _tmp != 0
          val _tmpStartup: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfStartup).toInt()
          _tmpStartup = _tmp_1 != 0
          val _tmpConfirmation: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfConfirmation).toInt()
          _tmpConfirmation = _tmp_2 != 0
          val _tmpCreatedDate: Long
          _tmpCreatedDate = _stmt.getLong(_columnIndexOfCreatedDate)
          val _tmpStorageType: KeyStorageType
          val _tmp_3: String
          _tmp_3 = _stmt.getText(_columnIndexOfStorageType)
          _tmpStorageType = __converters.toKeyStorageType(_tmp_3)
          val _tmpAllowBackup: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfAllowBackup).toInt()
          _tmpAllowBackup = _tmp_4 != 0
          val _tmpKeystoreAlias: String?
          if (_stmt.isNull(_columnIndexOfKeystoreAlias)) {
            _tmpKeystoreAlias = null
          } else {
            _tmpKeystoreAlias = _stmt.getText(_columnIndexOfKeystoreAlias)
          }
          _result = Pubkey(_tmpId,_tmpNickname,_tmpType,_tmpPrivateKey,_tmpPublicKey,_tmpEncrypted,_tmpStartup,_tmpConfirmation,_tmpCreatedDate,_tmpStorageType,_tmpAllowBackup,_tmpKeystoreAlias)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAll(): List<Pubkey> {
    val _sql: String = "SELECT * FROM pubkeys ORDER BY nickname ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfPrivateKey: Int = getColumnIndexOrThrow(_stmt, "private_key")
        val _columnIndexOfPublicKey: Int = getColumnIndexOrThrow(_stmt, "public_key")
        val _columnIndexOfEncrypted: Int = getColumnIndexOrThrow(_stmt, "encrypted")
        val _columnIndexOfStartup: Int = getColumnIndexOrThrow(_stmt, "startup")
        val _columnIndexOfConfirmation: Int = getColumnIndexOrThrow(_stmt, "confirmation")
        val _columnIndexOfCreatedDate: Int = getColumnIndexOrThrow(_stmt, "created_date")
        val _columnIndexOfStorageType: Int = getColumnIndexOrThrow(_stmt, "storage_type")
        val _columnIndexOfAllowBackup: Int = getColumnIndexOrThrow(_stmt, "allow_backup")
        val _columnIndexOfKeystoreAlias: Int = getColumnIndexOrThrow(_stmt, "keystore_alias")
        val _result: MutableList<Pubkey> = mutableListOf()
        while (_stmt.step()) {
          val _item: Pubkey
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpPrivateKey: ByteArray?
          if (_stmt.isNull(_columnIndexOfPrivateKey)) {
            _tmpPrivateKey = null
          } else {
            _tmpPrivateKey = _stmt.getBlob(_columnIndexOfPrivateKey)
          }
          val _tmpPublicKey: ByteArray
          _tmpPublicKey = _stmt.getBlob(_columnIndexOfPublicKey)
          val _tmpEncrypted: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEncrypted).toInt()
          _tmpEncrypted = _tmp != 0
          val _tmpStartup: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfStartup).toInt()
          _tmpStartup = _tmp_1 != 0
          val _tmpConfirmation: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfConfirmation).toInt()
          _tmpConfirmation = _tmp_2 != 0
          val _tmpCreatedDate: Long
          _tmpCreatedDate = _stmt.getLong(_columnIndexOfCreatedDate)
          val _tmpStorageType: KeyStorageType
          val _tmp_3: String
          _tmp_3 = _stmt.getText(_columnIndexOfStorageType)
          _tmpStorageType = __converters.toKeyStorageType(_tmp_3)
          val _tmpAllowBackup: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfAllowBackup).toInt()
          _tmpAllowBackup = _tmp_4 != 0
          val _tmpKeystoreAlias: String?
          if (_stmt.isNull(_columnIndexOfKeystoreAlias)) {
            _tmpKeystoreAlias = null
          } else {
            _tmpKeystoreAlias = _stmt.getText(_columnIndexOfKeystoreAlias)
          }
          _item = Pubkey(_tmpId,_tmpNickname,_tmpType,_tmpPrivateKey,_tmpPublicKey,_tmpEncrypted,_tmpStartup,_tmpConfirmation,_tmpCreatedDate,_tmpStorageType,_tmpAllowBackup,_tmpKeystoreAlias)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBackupable(): List<Pubkey> {
    val _sql: String = "SELECT * FROM pubkeys WHERE allow_backup = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfPrivateKey: Int = getColumnIndexOrThrow(_stmt, "private_key")
        val _columnIndexOfPublicKey: Int = getColumnIndexOrThrow(_stmt, "public_key")
        val _columnIndexOfEncrypted: Int = getColumnIndexOrThrow(_stmt, "encrypted")
        val _columnIndexOfStartup: Int = getColumnIndexOrThrow(_stmt, "startup")
        val _columnIndexOfConfirmation: Int = getColumnIndexOrThrow(_stmt, "confirmation")
        val _columnIndexOfCreatedDate: Int = getColumnIndexOrThrow(_stmt, "created_date")
        val _columnIndexOfStorageType: Int = getColumnIndexOrThrow(_stmt, "storage_type")
        val _columnIndexOfAllowBackup: Int = getColumnIndexOrThrow(_stmt, "allow_backup")
        val _columnIndexOfKeystoreAlias: Int = getColumnIndexOrThrow(_stmt, "keystore_alias")
        val _result: MutableList<Pubkey> = mutableListOf()
        while (_stmt.step()) {
          val _item: Pubkey
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpPrivateKey: ByteArray?
          if (_stmt.isNull(_columnIndexOfPrivateKey)) {
            _tmpPrivateKey = null
          } else {
            _tmpPrivateKey = _stmt.getBlob(_columnIndexOfPrivateKey)
          }
          val _tmpPublicKey: ByteArray
          _tmpPublicKey = _stmt.getBlob(_columnIndexOfPublicKey)
          val _tmpEncrypted: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEncrypted).toInt()
          _tmpEncrypted = _tmp != 0
          val _tmpStartup: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfStartup).toInt()
          _tmpStartup = _tmp_1 != 0
          val _tmpConfirmation: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfConfirmation).toInt()
          _tmpConfirmation = _tmp_2 != 0
          val _tmpCreatedDate: Long
          _tmpCreatedDate = _stmt.getLong(_columnIndexOfCreatedDate)
          val _tmpStorageType: KeyStorageType
          val _tmp_3: String
          _tmp_3 = _stmt.getText(_columnIndexOfStorageType)
          _tmpStorageType = __converters.toKeyStorageType(_tmp_3)
          val _tmpAllowBackup: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfAllowBackup).toInt()
          _tmpAllowBackup = _tmp_4 != 0
          val _tmpKeystoreAlias: String?
          if (_stmt.isNull(_columnIndexOfKeystoreAlias)) {
            _tmpKeystoreAlias = null
          } else {
            _tmpKeystoreAlias = _stmt.getText(_columnIndexOfKeystoreAlias)
          }
          _item = Pubkey(_tmpId,_tmpNickname,_tmpType,_tmpPrivateKey,_tmpPublicKey,_tmpEncrypted,_tmpStartup,_tmpConfirmation,_tmpCreatedDate,_tmpStorageType,_tmpAllowBackup,_tmpKeystoreAlias)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getExportable(): List<Pubkey> {
    val _sql: String = "SELECT * FROM pubkeys WHERE storage_type = 'EXPORTABLE'"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfPrivateKey: Int = getColumnIndexOrThrow(_stmt, "private_key")
        val _columnIndexOfPublicKey: Int = getColumnIndexOrThrow(_stmt, "public_key")
        val _columnIndexOfEncrypted: Int = getColumnIndexOrThrow(_stmt, "encrypted")
        val _columnIndexOfStartup: Int = getColumnIndexOrThrow(_stmt, "startup")
        val _columnIndexOfConfirmation: Int = getColumnIndexOrThrow(_stmt, "confirmation")
        val _columnIndexOfCreatedDate: Int = getColumnIndexOrThrow(_stmt, "created_date")
        val _columnIndexOfStorageType: Int = getColumnIndexOrThrow(_stmt, "storage_type")
        val _columnIndexOfAllowBackup: Int = getColumnIndexOrThrow(_stmt, "allow_backup")
        val _columnIndexOfKeystoreAlias: Int = getColumnIndexOrThrow(_stmt, "keystore_alias")
        val _result: MutableList<Pubkey> = mutableListOf()
        while (_stmt.step()) {
          val _item: Pubkey
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpPrivateKey: ByteArray?
          if (_stmt.isNull(_columnIndexOfPrivateKey)) {
            _tmpPrivateKey = null
          } else {
            _tmpPrivateKey = _stmt.getBlob(_columnIndexOfPrivateKey)
          }
          val _tmpPublicKey: ByteArray
          _tmpPublicKey = _stmt.getBlob(_columnIndexOfPublicKey)
          val _tmpEncrypted: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEncrypted).toInt()
          _tmpEncrypted = _tmp != 0
          val _tmpStartup: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfStartup).toInt()
          _tmpStartup = _tmp_1 != 0
          val _tmpConfirmation: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfConfirmation).toInt()
          _tmpConfirmation = _tmp_2 != 0
          val _tmpCreatedDate: Long
          _tmpCreatedDate = _stmt.getLong(_columnIndexOfCreatedDate)
          val _tmpStorageType: KeyStorageType
          val _tmp_3: String
          _tmp_3 = _stmt.getText(_columnIndexOfStorageType)
          _tmpStorageType = __converters.toKeyStorageType(_tmp_3)
          val _tmpAllowBackup: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfAllowBackup).toInt()
          _tmpAllowBackup = _tmp_4 != 0
          val _tmpKeystoreAlias: String?
          if (_stmt.isNull(_columnIndexOfKeystoreAlias)) {
            _tmpKeystoreAlias = null
          } else {
            _tmpKeystoreAlias = _stmt.getText(_columnIndexOfKeystoreAlias)
          }
          _item = Pubkey(_tmpId,_tmpNickname,_tmpType,_tmpPrivateKey,_tmpPublicKey,_tmpEncrypted,_tmpStartup,_tmpConfirmation,_tmpCreatedDate,_tmpStorageType,_tmpAllowBackup,_tmpKeystoreAlias)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getStartupKeys(): List<Pubkey> {
    val _sql: String = "SELECT * FROM pubkeys WHERE startup = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNickname: Int = getColumnIndexOrThrow(_stmt, "nickname")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfPrivateKey: Int = getColumnIndexOrThrow(_stmt, "private_key")
        val _columnIndexOfPublicKey: Int = getColumnIndexOrThrow(_stmt, "public_key")
        val _columnIndexOfEncrypted: Int = getColumnIndexOrThrow(_stmt, "encrypted")
        val _columnIndexOfStartup: Int = getColumnIndexOrThrow(_stmt, "startup")
        val _columnIndexOfConfirmation: Int = getColumnIndexOrThrow(_stmt, "confirmation")
        val _columnIndexOfCreatedDate: Int = getColumnIndexOrThrow(_stmt, "created_date")
        val _columnIndexOfStorageType: Int = getColumnIndexOrThrow(_stmt, "storage_type")
        val _columnIndexOfAllowBackup: Int = getColumnIndexOrThrow(_stmt, "allow_backup")
        val _columnIndexOfKeystoreAlias: Int = getColumnIndexOrThrow(_stmt, "keystore_alias")
        val _result: MutableList<Pubkey> = mutableListOf()
        while (_stmt.step()) {
          val _item: Pubkey
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpNickname: String
          _tmpNickname = _stmt.getText(_columnIndexOfNickname)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpPrivateKey: ByteArray?
          if (_stmt.isNull(_columnIndexOfPrivateKey)) {
            _tmpPrivateKey = null
          } else {
            _tmpPrivateKey = _stmt.getBlob(_columnIndexOfPrivateKey)
          }
          val _tmpPublicKey: ByteArray
          _tmpPublicKey = _stmt.getBlob(_columnIndexOfPublicKey)
          val _tmpEncrypted: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEncrypted).toInt()
          _tmpEncrypted = _tmp != 0
          val _tmpStartup: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfStartup).toInt()
          _tmpStartup = _tmp_1 != 0
          val _tmpConfirmation: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfConfirmation).toInt()
          _tmpConfirmation = _tmp_2 != 0
          val _tmpCreatedDate: Long
          _tmpCreatedDate = _stmt.getLong(_columnIndexOfCreatedDate)
          val _tmpStorageType: KeyStorageType
          val _tmp_3: String
          _tmp_3 = _stmt.getText(_columnIndexOfStorageType)
          _tmpStorageType = __converters.toKeyStorageType(_tmp_3)
          val _tmpAllowBackup: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfAllowBackup).toInt()
          _tmpAllowBackup = _tmp_4 != 0
          val _tmpKeystoreAlias: String?
          if (_stmt.isNull(_columnIndexOfKeystoreAlias)) {
            _tmpKeystoreAlias = null
          } else {
            _tmpKeystoreAlias = _stmt.getText(_columnIndexOfKeystoreAlias)
          }
          _item = Pubkey(_tmpId,_tmpNickname,_tmpType,_tmpPrivateKey,_tmpPublicKey,_tmpEncrypted,_tmpStartup,_tmpConfirmation,_tmpCreatedDate,_tmpStorageType,_tmpAllowBackup,_tmpKeystoreAlias)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateBackupPermission(pubkeyId: Long, allowBackup: Boolean) {
    val _sql: String = "UPDATE pubkeys SET allow_backup = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (allowBackup) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, pubkeyId)
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
