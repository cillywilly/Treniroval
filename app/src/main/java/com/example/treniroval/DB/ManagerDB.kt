package com.example.treniroval.DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.support.annotation.RequiresApi
import com.example.treniroval.DB.DBHelper.Companion
import com.example.treniroval.DB.DBHelper.Companion.KEY_APPROACH
import com.example.treniroval.DB.DBHelper.Companion.KEY_DATE
import com.example.treniroval.DB.DBHelper.Companion.KEY_EXERCISE_NAME
import com.example.treniroval.DB.DBHelper.Companion.KEY_ID_EXERCISE
import com.example.treniroval.DB.DBHelper.Companion.KEY_ID_TRAINING
import com.example.treniroval.DB.DBHelper.Companion.KEY_ID_TRAINING_EXERCISE
import com.example.treniroval.DB.DBHelper.Companion.KEY_ID_TRAINING_TOPIC
import com.example.treniroval.DB.DBHelper.Companion.KEY_REPEAT
import com.example.treniroval.DB.DBHelper.Companion.KEY_WORKLOAD
import com.example.treniroval.DB.DBHelper.Companion.TABLE_TRAINING
import com.example.treniroval.ListItem.ListItemApproachInExercise
import com.example.treniroval.ListItem.ListItemExercise
import com.example.treniroval.ListItem.ListItemExerciseInTable
import com.example.treniroval.ListItem.ListItemPastTraining
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ManagerDB(context: Context) {
    private val DBHelper = DBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = DBHelper.writableDatabase
    }

    fun dropDB() {
        db?.execSQL("drop table if exists ${Companion.TABLE_TRAINING_EXERCISE}")
        db?.execSQL("drop table if exists $TABLE_TRAINING")
        db?.execSQL("drop table if exists ${Companion.TABLE_TRAINING_TOPIC}")
        db?.execSQL("drop table if exists ${Companion.TABLE_EXERCISE}")
        db?.let { DBHelper.onCreate(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertTraining(trainingTopic: String) {
        val dateTime = LocalDateTime.now()
        val value = ContentValues().apply {
            put(KEY_DATE, dateTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")))
            put(KEY_ID_TRAINING_TOPIC, trainingTopic)
        }
        println(value)

        db?.insert(TABLE_TRAINING, null, value)
    }

    @SuppressLint("Recycle")
    fun getPastTrainings(): ArrayList<ListItemPastTraining> {
        val listItems = ArrayList<ListItemPastTraining>()
        val cursor =
            db?.query(Companion.TABLE_TRAINING, null, null, null, null, null, KEY_ID_TRAINING)
        while (cursor?.moveToNext()!!) {
            val trainingTopic = cursor.getString(cursor.getColumnIndex(KEY_ID_TRAINING_TOPIC))
            val date = cursor.getString(cursor.getColumnIndex(KEY_DATE))
            listItems.add(ListItemPastTraining(trainingTopic, date))
        }
        return listItems
    }

    @SuppressLint("Recycle")
    fun getExercises(): ArrayList<ListItemExercise> {
        val listItemExercise = ArrayList<ListItemExercise>()
        val cursor =
            db?.query(Companion.TABLE_EXERCISE, null, null, null, null, null, KEY_ID_EXERCISE)
        while (cursor?.moveToNext()!!) {
            val exercise = cursor.getString(cursor.getColumnIndex(KEY_EXERCISE_NAME))
            listItemExercise.add(ListItemExercise(exercise))
        }
        return listItemExercise
    }

    @SuppressLint("Recycle")
    fun getCurrentTraining(): ArrayList<ListItemExerciseInTable> {
        val listItemExerciseInTable = ArrayList<ListItemExerciseInTable>()
        val cursor = db?.query(
            Companion.TABLE_TRAINING_EXERCISE, null,
            null, null, null, null, KEY_ID_TRAINING_EXERCISE
        )
        cursor?.moveToFirst()
        while (cursor?.moveToNext()!!) {
                val idTraining = cursor.getString(cursor.getColumnIndex(KEY_ID_TRAINING))
                val idExercise = cursor.getString(cursor.getColumnIndex(KEY_ID_EXERCISE))
            val numOfApproach = cursor.getString(cursor.getColumnIndex(KEY_APPROACH))
            val sumOfRepeats = cursor.getString(cursor.getColumnIndex(KEY_REPEAT))
            val workLoad = cursor.getString(cursor.getColumnIndex(KEY_WORKLOAD))
            listItemExerciseInTable.add(
                ListItemExerciseInTable(
                    idExercise,
                    ListItemApproachInExercise(numOfApproach, sumOfRepeats, workLoad)
                )
            )
        }
        return listItemExerciseInTable
    }

    fun closeDb() {
        db?.close()
    }
}
