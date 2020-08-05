package com.example.treniroval

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
    DATABASE_NAME: String = "treniroval",
    val DATABASE_VERSION: Int = 1
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val TABLE_TRAININGS: String = "Trainings"

    val KEY_ID: String = "_id"
    val KEY_TRAINING_TOPIC: String = "training_topic"
    val KEY_DATE: String = "date"
    val KEY_EXERCISE: String = "exercise"
    val KEY_APPROACH: String = "approach"
    val KEY_REPEAT: String = "repeat"
    val KEY_WORKLOAD: String = "workload"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "create table " + TABLE_TRAININGS + "(" + KEY_ID
                    + " integer primary key," + KEY_TRAINING_TOPIC + " text," + KEY_DATE + " text" + KEY_EXERCISE + " text" +
                    KEY_APPROACH + " text" + KEY_REPEAT + " text" + KEY_WORKLOAD + " text" + ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("drop table if exists $TABLE_TRAININGS")

        onCreate(db)
    }
}