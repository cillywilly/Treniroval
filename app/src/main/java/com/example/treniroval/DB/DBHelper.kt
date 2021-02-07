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
                    "($ID_EXERCISE INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$EXERCISE_NAME TEXT NOT NULL) ;")
        val SQL_CREATE_TABLE_TRAINING_TOPIC =
            ("CREATE TABLE $TABLE_TRAINING_TOPIC " +
                    "($ID_TRAINING_TOPIC INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$TRAINING_TOPIC TEXT NOT NULL) ;")
        val SQL_CREATE_TABLE_TRAINING =
            ("CREATE TABLE $TABLE_TRAINING " +
                    "($ID_TRAINING INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$ID_TRAINING_TOPIC INTEGER NOT NULL, " +
                    "$DATE TEXT NOT NULL, " +
                    "FOREIGN KEY ($ID_TRAINING_TOPIC) REFERENCES $TABLE_TRAINING_TOPIC($ID_TRAINING_TOPIC)) ;")
        val SQL_CREATE_TABLE_TRAINING_EXERCISE =
            ("CREATE TABLE $TABLE_TRAINING_EXERCISE" +
                    " ($ID_TRAINING_EXERCISE INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$ID_TRAINING INTEGER NOT NULL, " +
                    "$ID_EXERCISE INTEGER NOT NULL, " +
                    "$APPROACH TEXT NOT NULL, " +
                    "$REPEAT TEXT NOT NULL, " +
                    "$WORKLOAD TEXT NOT NULL, " +
                    "FOREIGN KEY ($ID_TRAINING) REFERENCES $TABLE_TRAINING($ID_TRAINING), " +
                    "FOREIGN KEY ($ID_EXERCISE) REFERENCES $TABLE_EXERCISE($ID_EXERCISE)) ;")


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
//        addExercise(sqLiteDatabase)
        addTrainingTopic(sqLiteDatabase)
        addApproach(sqLiteDatabase)

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

        const val TABLE_EXERCISE: String = "exercise" //таблица названий упражнений
        const val ID_EXERCISE: String = "id_exercise" //ИД используется 2 раза это +(1)+
        const val EXERCISE_NAME: String = "exercise_name" //название упражнения

        const val TABLE_TRAINING: String =
            "training" //таблица УНИКАЛЬНЫХ тренировок
        const val ID_TRAINING: String = "id_training" //ИД
        const val ID_TRAINING_TOPIC: String =
            "id_training_topic" //ИД используется 2 раза это -(1)-
        const val DATE: String = "date" //дата тренировки

        const val TABLE_TRAINING_TOPIC: String = "training_topic"  //таблица названий тренировок
        const val TRAINING_TOPIC: String = "training_topic" //название тренировки + -(2)-

        const val TABLE_TRAINING_EXERCISE: String =
            "training_exercise" //таблица УНИКАЛЬНЫХ упражнений для ВСЕХ тренировок
        const val ID_TRAINING_EXERCISE: String = "id_training_exercise" //ИД + +(2)+
        const val APPROACH: String = "approach" //номер подхода
        const val REPEAT: String = "repeat" //количество повторений
        const val WORKLOAD: String = "workload" // нагрузка
    }
}

/**в главной таблице TABLE_TRAINING_EXERCISE хранятся все ПОДХОДЫ
 * 1 тренировка - это дата+название (сортировка истории по дате)
 *в нее входят i УНИКАЛЬНЫХ подходов. каждый подход содержит количество повторений и нагрузку по время повторения
 * подходы объединяются названием упражнения
 * упражнения объединяются уникальной тренировкой
 * https://ondras.zarovi.cz/sql/demo/
 */
//"FOREIGN": syntax error (code 1 SQLITE_ERROR): , while compiling:
var ttt =
    "CREATE TABLE training_exercise (_id_training_exercise INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "_id_training TEXT NOT NULL, _id_exercise TEXT NOT NULL, approach TEXT NOT NULL," +
            " repeat TEXT NOT NULL, workload TEXT NOT NULL " +
            "FOREIGN KEY (_id_training) REFERENCES training(_id_training) " +
            "FOREIGN KEY (_id_exercise) REFERENCES exercise(_id_exercise)) ;"
