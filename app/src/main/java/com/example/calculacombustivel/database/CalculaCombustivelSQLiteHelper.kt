package com.example.calculacombustivel.database

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class CalculaCombustivelSQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NOME, null, DATABASE_VERSAO) {

    companion object {
        private const val DATABASE_NOME = "calcula_combustivel.db"
        private const val DATABASE_VERSAO = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE combustivel (id INTEGER PRIMARY KEY AUTOINCREMENT, valor_gasolina REAL NOT NULL, valor_etanol REAL NOT NULL, data_registro TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}
