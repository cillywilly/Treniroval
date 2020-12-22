package com.example.treniroval.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    @Override
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val SQL_CREATE_TABLE_EXERCISE =
            ("CREATE TABLE $TABLE_EXERCISE " +
                    "($KEY_ID_EXERCISE INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$KEY_EXERCISE_NAME TEXT NOT NULL) ;")
        val SQL_CREATE_TABLE_TRAINING_TOPIC =
            ("CREATE TABLE $TABLE_TRAINING_TOPIC " +
                    "($KEY_ID_TRAINING_TOPIC INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$KEY_TRAINING_TOPIC TEXT NOT NULL) ;")
        val SQL_CREATE_TABLE_TRAINING =
            ("CREATE TABLE $TABLE_TRAINING " +
                    "($KEY_ID_TRAINING INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$KEY_ID_TRAINING_TOPIC TEXT NOT NULL, " +
                    "$KEY_DATE TEXT NOT NULL, " +
                    "FOREIGN KEY ($KEY_ID_TRAINING_TOPIC) REFERENCES $TABLE_TRAINING_TOPIC($KEY_ID_TRAINING_TOPIC)) ;")
        val SQL_CREATE_TABLE_TRAINING_EXERCISE =
            ("CREATE TABLE $TABLE_TRAINING_EXERCISE" +
                    " ($KEY_ID_TRAINING_EXERCISE INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$KEY_ID_TRAINING TEXT NOT NULL, " +
                    "$KEY_ID_EXERCISE TEXT NOT NULL, " +
                    "$KEY_APPROACH TEXT NOT NULL, " +
                    "$KEY_REPEAT TEXT NOT NULL, " +
                    "$KEY_WORKLOAD TEXT NOT NULL, " +
                    "FOREIGN KEY ($KEY_ID_TRAINING) REFERENCES $TABLE_TRAINING($KEY_ID_TRAINING) " +
                    "FOREIGN KEY ($KEY_ID_EXERCISE) REFERENCES $TABLE_EXERCISE($KEY_ID_EXERCISE)) ;")


//        val SQL_CREATE_TABLE_TRAINING_EXERCISE =
//            ("CREATE TABLE `TABLE_TRAINING_EXERCISE` ("+
//            "`KEY_ID_TRAINING_EXERCISE` INTEGER NULL AUTOINCREMENT DEFAULT NULL,"+
//                    "`KEY_ID_TRAINING` INTEGER NULL DEFAULT NULL,"+
//                    "`KEY_ID_EXERCISE` INTEGER NULL DEFAULT NULL,"+
//                    "`KEY_APPROACH` INTEGER NULL DEFAULT NULL,"+
//                    "`KEY_REPEAT` INTEGER NULL DEFAULT NULL,"+
//                    "`KEY_WORKLOAD` INTEGER NULL DEFAULT NULL,"+
//                    "PRIMARY KEY (`KEY_ID_TRAINING_EXERCISE`) ;"+
//                    "FOREIGN KEY ($KEY_ID_TRAINING) REFERENCES $TABLE_TRAINING($KEY_ID_TRAINING) " +
//                    "FOREIGN KEY ($KEY_ID_EXERCISE) REFERENCES $TABLE_EXERCISE($KEY_ID_EXERCISE)) ;")


//        ALTER TABLE `TABLE_TRAINING_EXERCISE` ADD FOREIGN KEY (KEY_ID_TRAINING) REFERENCES `TABLE_TRAINING` (`KEY_ID_TRAINING`);
//        ALTER TABLE `TABLE_TRAINING_EXERCISE` ADD FOREIGN KEY (KEY_ID_EXERCISE) REFERENCES `TABLE_EXERCISE` (`KEY_ID_EXERCISE`);

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_EXERCISE)
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TRAINING_TOPIC)
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TRAINING)
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TRAINING_EXERCISE)
        addExercise(sqLiteDatabase)
        addTrainingTopic(sqLiteDatabase)
    }

    override fun onUpgrade(
        db: SQLiteDatabase, oldVersion: Int, newVersion: Int
    ) {
        db.execSQL("drop table if exists $TABLE_TRAINING_EXERCISE")
        db.execSQL("drop table if exists $TABLE_TRAINING")
        db.execSQL("drop table if exists $TABLE_TRAINING_TOPIC")
        db.execSQL("drop table if exists $TABLE_EXERCISE")
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 3
        const val DATABASE_NAME = "treniroval"

        const val TABLE_EXERCISE: String = "exercise"
        const val KEY_ID_EXERCISE: String = "id_exercise"
        const val KEY_EXERCISE_NAME: String = "exercise_name"

        const val TABLE_TRAINING: String = "training"
        const val KEY_ID_TRAINING: String = "id_training"
        const val KEY_ID_TRAINING_TOPIC: String = "id_training_topic"
        const val KEY_DATE: String = "date"

        const val TABLE_TRAINING_TOPIC: String = "training_topic"
        const val KEY_TRAINING_TOPIC: String = "training_topic"

        const val TABLE_TRAINING_EXERCISE: String = "training_exercise"
        const val KEY_ID_TRAINING_EXERCISE: String = "id_training_exercise"
        const val KEY_APPROACH: String = "approach"
        const val KEY_REPEAT: String = "repeat"
        const val KEY_WORKLOAD: String = "workload"
    }
}
//"FOREIGN": syntax error (code 1 SQLITE_ERROR): , while compiling:
var ttt = "CREATE TABLE training_exercise (_id_training_exercise INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "_id_training TEXT NOT NULL, _id_exercise TEXT NOT NULL, approach TEXT NOT NULL," +
        " repeat TEXT NOT NULL, workload TEXT NOT NULL " +
        "FOREIGN KEY (_id_training) REFERENCES training(_id_training) " +
        "FOREIGN KEY (_id_exercise) REFERENCES exercise(_id_exercise)) ;"