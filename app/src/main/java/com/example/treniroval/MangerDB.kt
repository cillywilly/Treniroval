package com.example.treniroval

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.treniroval.DBHelper.Companion.TABLE_TRAINING_EXERCISE

class MangerDB(context: Context) {
    val DBHelper = DBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = DBHelper.writableDatabase
    }

    fun insertTraining(title: String, content: Training) {
        val value = ContentValues().apply {
            put(TABLE_TRAINING_EXERCISE,"")
        }


    }
}
