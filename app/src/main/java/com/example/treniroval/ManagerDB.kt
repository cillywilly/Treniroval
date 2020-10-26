package com.example.treniroval

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.support.annotation.RequiresApi
import com.example.treniroval.DBHelper.Companion
import com.example.treniroval.DBHelper.Companion.KEY_DATE
import com.example.treniroval.DBHelper.Companion.KEY_ID_TRAINING_TOPIC
import com.example.treniroval.DBHelper.Companion.TABLE_TRAINING
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar.APRIL

class ManagerDB(context: Context) {
    private val DBHelper = DBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = DBHelper.writableDatabase
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertTraining(trainingTopic: String) {
        val dateTime = LocalDateTime.now()
        val value = ContentValues().apply {
            put(KEY_ID_TRAINING_TOPIC, trainingTopic)
            put(KEY_DATE, dateTime.format(DateTimeFormatter.ofPattern("H:m d/m/y")))
        }
        println(value)

        db?.insert(TABLE_TRAINING, null,value)
    }

    @SuppressLint("Recycle")
    fun getPastTrainings() : ArrayList<ListItem> {
        val listItems = ArrayList<ListItem>()
        val cursor = db?.query(Companion.TABLE_TRAINING,null,null,null,null,null,Companion.KEY_DATE)
        while (cursor?.moveToNext()!!) {
            val trainingTopic = cursor.getString(cursor.getColumnIndex(KEY_ID_TRAINING_TOPIC))
            val date = cursor.getString(cursor.getColumnIndex(KEY_DATE))
            listItems.add(ListItem(trainingTopic,date))
        }
        return listItems
    }


    fun closeDb() {
        db?.close()
    }
}
