package com.example.treniroval

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.treniroval.DBHelper.Companion.KEY_EXERCISE_NAME
import com.example.treniroval.DBHelper.Companion.TABLE_EXERCISE


class SelectTrainingTopicActivity : Activity() {

    private var dbHelper = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_training_topic)

    }

//    fun clickAdd(view: View) {
//        val dataBase: SQLiteDatabase = dbHelper.writableDatabase
//        lateinit var contentValues: ContentValues
//        when (view.id) {
//            R.id.addyery -> {
//                contentValues.put(KEY_EXERCISE_NAME, "Grud")
//                dataBase.insert(TABLE_EXERCISE, null, contentValues)
//            }
//        }
//        dbHelper.close()
//    }

    fun clickAdd(view: View) {
        val dataBase = dbHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_EXERCISE_NAME, "Grud")
        contentValues.put(KEY_EXERCISE_NAME, "nogi")
        contentValues.put(KEY_EXERCISE_NAME, "spina")
        dataBase.insert(TABLE_EXERCISE, null, contentValues)
        dataBase.close()
    }

    fun clickRead(view: View) {
        val dataBase: SQLiteDatabase = dbHelper.writableDatabase
        Thread.sleep(1000)
        val cursor: Cursor = dataBase.query(TABLE_EXERCISE, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            Log.d("mLog", cursor.getString(cursor.getColumnIndex(KEY_EXERCISE_NAME)))
        }
        cursor.close()
        dbHelper.close()

    }

    fun onClickButtonChest(view: View) {
        val intent = Intent(this, TrainingConstructorActivity::class.java)
        startActivity(intent)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}