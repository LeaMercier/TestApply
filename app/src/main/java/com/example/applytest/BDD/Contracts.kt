package com.example.applytest.BDD

import android.provider.BaseColumns

object Contracts {

    object Aliments: BaseColumns {
        const val TABLE_NAME = "aliments"
        const val COLUMN_NAME_NAME = "nom"
        const val COLUMN_NAME_FAMILLE = "famille"

        const val SQL_CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME (" + "$COLUMN_NAME_NAME TEXT PRIMARY KEY, " +
                    "$COLUMN_NAME_FAMILLE TEXT)"

        const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${Aliments.TABLE_NAME}"
    }
}