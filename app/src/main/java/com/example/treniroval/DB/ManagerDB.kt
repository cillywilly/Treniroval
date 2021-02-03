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
    lateinit var db: SQLiteDatabase

    fun openDb() {
        db = DBHelper.writableDatabase
    }

    fun dropDB() {
        db.execSQL("drop table if exists ${Companion.TABLE_TRAINING_EXERCISE}")
        db.execSQL("drop table if exists $TABLE_TRAINING")
        db.execSQL("drop table if exists ${Companion.TABLE_TRAINING_TOPIC}")
        db.execSQL("drop table if exists ${Companion.TABLE_EXERCISE}")
        db.let { DBHelper.onCreate(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertTraining(trainingTopic: String) {
        val dateTime = LocalDateTime.now()
        val value = ContentValues().apply {
            put(KEY_DATE, dateTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")))
            when (trainingTopic) {
                "Тренировка ног" -> {
                    put(KEY_ID_TRAINING_TOPIC, 1)
                }
                "Тренировка груди" -> {
                    put(KEY_ID_TRAINING_TOPIC, 2)
                }
                "Тренировка спины" -> {
                    put(KEY_ID_TRAINING_TOPIC, 3)
                }
            }
        }
        println(value)

        db.insert(TABLE_TRAINING, null, value)
    }

    @SuppressLint("Recycle")
    fun getPastTrainings(): ArrayList<ListItemPastTraining> {
        val listItems = ArrayList<ListItemPastTraining>()
        val cursor =
            db.query(Companion.TABLE_TRAINING, null, null, null, null, null, KEY_ID_TRAINING)
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
            db.query(Companion.TABLE_EXERCISE, null, null, null, null, null, KEY_ID_EXERCISE)
        while (cursor?.moveToNext()!!) {
            val exercise = cursor.getString(cursor.getColumnIndex(KEY_EXERCISE_NAME))
            listItemExercise.add(ListItemExercise(exercise))
        }
        return listItemExercise
    }

    @SuppressLint("Recycle")
    fun getCurrentTraining(trainingId: String, exerciseId:String): ArrayList<ListItemExerciseInTable> {
        val listItemExerciseInTable = ArrayList<ListItemExerciseInTable>()
        val cursor = db.query(
            Companion.TABLE_TRAINING_EXERCISE, null,
            "$KEY_ID_TRAINING='$trainingId' and $KEY_ID_EXERCISE='$exerciseId'",
            null, null, null, KEY_ID_TRAINING_EXERCISE
        )
        cursor?.moveToFirst()
        while (cursor?.moveToNext()!!) {
                val idTraining = cursor.getString(cursor.getColumnIndex(KEY_ID_TRAINING))
                val idExercise = cursor.getString(cursor.getColumnIndex(KEY_ID_EXERCISE))
            val exerciseName = getExerciseName(db, idExercise)
            val numOfApproach = cursor.getString(cursor.getColumnIndex(KEY_APPROACH))
            val sumOfRepeats = cursor.getString(cursor.getColumnIndex(KEY_REPEAT))
            val workLoad = cursor.getString(cursor.getColumnIndex(KEY_WORKLOAD))
            listItemExerciseInTable.add(
                ListItemExerciseInTable(
                    exerciseName,
                    ListItemApproachInExercise(numOfApproach, sumOfRepeats, workLoad)
                )
            )
        }
        return listItemExerciseInTable
    }

    fun closeDb() {
        db.close()
    }

    @SuppressLint("Recycle")
    fun getExerciseName(db: SQLiteDatabase, exerciseId :String): String {
        val cursor = db.query(Companion.TABLE_EXERCISE, null,"$KEY_ID_EXERCISE='$exerciseId'",
            null, null, null, null)
        cursor.moveToFirst()
        return cursor.getString(cursor.getColumnIndex(KEY_EXERCISE_NAME))
    }
}

//-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
//-- SET FOREIGN_KEY_CHECKS=0;
//
//-- ---
//-- Table 'TABLE_TRAINING'
//--
//-- ---
//
//DROP TABLE IF EXISTS `TABLE_TRAINING`;
//
//CREATE TABLE `TABLE_TRAINING` (
//`KEY_ID_TRAINING` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
//`KEY_ID_TRAINING_TOPIC` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
//`KEY_DATE` INTEGER NULL DEFAULT NULL,
//PRIMARY KEY (`KEY_ID_TRAINING`)
//);
//
//-- ---
//-- Table 'TABLE_EXERCISE'
//--
//-- ---
//
//DROP TABLE IF EXISTS `TABLE_EXERCISE`;
//
//CREATE TABLE `TABLE_EXERCISE` (
//`KEY_ID_EXERCISE` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
//`KEY_EXERCISE_NAME` INTEGER NULL DEFAULT NULL,
//PRIMARY KEY (`KEY_ID_EXERCISE`)
//);
//
//-- ---
//-- Table 'TABLE_TRAINING_EXERCISE'
//--
//-- ---
//
//DROP TABLE IF EXISTS `TABLE_TRAINING_EXERCISE`;
//
//CREATE TABLE `TABLE_TRAINING_EXERCISE` (
//`KEY_ID_TRAINING_EXERCISE` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
//`KEY_ID_TRAINING` INTEGER NULL DEFAULT NULL,
//`KEY_ID_EXERCISE` INTEGER NULL DEFAULT NULL,
//`KEY_APPROACH` INTEGER NULL DEFAULT NULL,
//`KEY_REPEAT` INTEGER NULL DEFAULT NULL,
//`KEY_WORKLOAD` INTEGER NULL DEFAULT NULL,
//PRIMARY KEY (`KEY_ID_TRAINING_EXERCISE`)
//);
//
//-- ---
//-- Table 'TABLE_TRAINING_TOPIC'
//--
//-- ---
//
//DROP TABLE IF EXISTS `TABLE_TRAINING_TOPIC`;
//
//CREATE TABLE `TABLE_TRAINING_TOPIC` (
//`KEY_ID_TRAINING_TOPIC` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
//`KEY_TRAINING_TOPIC` INTEGER NULL DEFAULT NULL,
//PRIMARY KEY (`KEY_ID_TRAINING_TOPIC`)
//);
//
//-- ---
//-- Foreign Keys
//-- ---
//
//ALTER TABLE `TABLE_TRAINING` ADD FOREIGN KEY (KEY_ID_TRAINING_TOPIC) REFERENCES `TABLE_TRAINING_TOPIC` (`KEY_ID_TRAINING_TOPIC`);
//ALTER TABLE `TABLE_TRAINING_EXERCISE` ADD FOREIGN KEY (KEY_ID_TRAINING) REFERENCES `TABLE_TRAINING` (`KEY_ID_TRAINING`);
//ALTER TABLE `TABLE_TRAINING_EXERCISE` ADD FOREIGN KEY (KEY_ID_EXERCISE) REFERENCES `TABLE_EXERCISE` (`KEY_ID_EXERCISE`);
//
//-- ---
//-- Table Properties
//-- ---
//
//-- ALTER TABLE `TABLE_TRAINING` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
//-- ALTER TABLE `TABLE_EXERCISE` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
//-- ALTER TABLE `TABLE_TRAINING_EXERCISE` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
//-- ALTER TABLE `TABLE_TRAINING_TOPIC` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
//
//-- ---
//-- Test Data
//-- ---
//
//-- INSERT INTO `TABLE_TRAINING` (`KEY_ID_TRAINING`,`KEY_ID_TRAINING_TOPIC`,`KEY_DATE`) VALUES (1, 1, 01/01/2020);
//-- ('','','');
//-- INSERT INTO `TABLE_EXERCISE` (`KEY_ID_EXERCISE`,`KEY_EXERCISE_NAME`) VALUES (1, 'prised');
//-- ('','');
//-- INSERT INTO `TABLE_TRAINING_EXERCISE` (`KEY_ID_TRAINING_EXERCISE`,`KEY_ID_TRAINING`,`KEY_ID_EXERCISE`,`KEY_APPROACH`,`KEY_REPEAT`,`KEY_WORKLOAD`) VALUES (1, 1, 1, 1, 1, 1);
//-- ('','','','','','');
//-- INSERT INTO `TABLE_TRAINING_TOPIC` (`KEY_ID_TRAINING_TOPIC`,`KEY_TRAINING_TOPIC`) VALUES (1, 'topic');
//-- ('','');
