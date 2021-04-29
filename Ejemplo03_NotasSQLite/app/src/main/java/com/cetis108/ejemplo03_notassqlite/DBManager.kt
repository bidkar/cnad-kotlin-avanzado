package com.cetis108.ejemplo03_notassqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class DBManager {
    val dbNombre = "MisNotas"
    val dbTabla = "Notas"
    val columnId = "Id"
    val columnTitulo = "Titulo"
    val columnDescripcion = "Descripcion"
    val dbVersion = 1

    val sqlCreateTabla = "CREATE TABLE IF NOT EXIST $dbTabla (" +
            "$columnId INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$columnTitulo TEXT NOT NULL," +
            "$columnDescripcion TEXT NOT NULL)"

    var sqlDB: SQLiteDatabase? = null

    inner class DBHelperNotas(_contexto: Context) :
        SQLiteOpenHelper(_contexto, dbNombre, null, dbVersion) {
        private val contexto: Context = _contexto
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTabla)
            Toast.makeText(this.contexto, "Base de datos creada", Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS $dbTabla")
        }

    }

    fun insert(values: ContentValues): Long {
        val id = sqlDB!!.insert(dbTabla, "", values)
        return id
    }

    fun query(projection: Array<String>, selection: String, selectionArgs: Array<String>, orderBy: String): Cursor {
        val consulta = SQLiteQueryBuilder().apply {
            tables = dbTabla
        }
        val cursor = consulta.query(sqlDB, projection, selection, selectionArgs, null, null, orderBy)
        return cursor
    }

    fun borrar(selection: String, selectionArgs: Array<String>): Int {
        val contador = sqlDB!!.delete(dbTabla, selection, selectionArgs)
        return contador
    }

    fun actualizar(values: ContentValues, selection: String, selectionArgs: Array<String>): Int {
        val contador = sqlDB!!.update(dbTabla, values, selection, selectionArgs)
        return contador
    }

    constructor(contexto: Context) {
        val db = DBHelperNotas(contexto)
        sqlDB = db.writableDatabase
    }
}