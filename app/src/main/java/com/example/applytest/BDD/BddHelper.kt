package com.example.applytest.BDD

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BddHelper(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME, null, DATABASE_VERSION) {

    //Permet de créer la table aliments
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Contracts.Aliments.SQL_CREATE_TABLE)
    }

    //Permet de mettre à jour la table aliments
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {
        db.execSQL(Contracts.Aliments.SQL_DELETE_TABLE)
        onCreate(db)
    }

    //Contient les informations relatives à la bdd
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "BddAliment.db"
    }
}