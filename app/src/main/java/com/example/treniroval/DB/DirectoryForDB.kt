package com.example.treniroval.DB

import android.database.sqlite.SQLiteDatabase
import com.example.treniroval.DB.DBHelper.Companion.KEY_APPROACH
import com.example.treniroval.DB.DBHelper.Companion.KEY_EXERCISE_NAME
import com.example.treniroval.DB.DBHelper.Companion.KEY_REPEAT
import com.example.treniroval.DB.DBHelper.Companion.KEY_TRAINING_TOPIC
import com.example.treniroval.DB.DBHelper.Companion.KEY_WORKLOAD
import com.example.treniroval.DB.DBHelper.Companion.TABLE_EXERCISE
import com.example.treniroval.DB.DBHelper.Companion.TABLE_TRAINING_EXERCISE
import com.example.treniroval.DB.DBHelper.Companion.TABLE_TRAINING_TOPIC
import com.example.treniroval.ListItem.ListItemApproachInExercise
import com.example.treniroval.ListItem.ListItemExerciseInTable

var exercises: ArrayList<String> = ArrayList()
var trainingTopic: ArrayList<String> = ArrayList()
var exerciseTraining: ArrayList<ListItemExerciseInTable> = ArrayList()

fun addApproach(sqLiteDatabase: SQLiteDatabase) {
    for (i in 1..4) {
        val app: String = i.toString()
        val rep: String = (8..12).random().toString()
        val loa: String = (40..70).random().toString()
        val list = ListItemExerciseInTable("Приседания",
            ListItemApproachInExercise(app, rep, loa)
        )
        exerciseTraining.add(list)
    }
    for (exercise in exerciseTraining) {
        sqLiteDatabase.execSQL("INSERT INTO $TABLE_TRAINING_EXERCISE" +
                "($KEY_APPROACH,$KEY_REPEAT,$KEY_WORKLOAD) " +
                "VALUES('${exercise.listItemApproachInExercise?.approachNumber}," +
                "${exercise.listItemApproachInExercise?.repeatSum}," +
                "${exercise.listItemApproachInExercise?.load}');")
    }
}

fun addExercise(sqLiteDatabase: SQLiteDatabase) {
    exercises.add("Приседания со штангой")
    exercises.add("Жим ногами в тренажёре")
    exercises.add("Выпады")
    exercises.add("Разгибание ног")
    exercises.add("Сгибание ног лежа")

    exercises.add("Жим штанги лежа")
    exercises.add("Жим гантелей лежа")
    exercises.add("Сведение рук в тренажере")
    exercises.add("Армейский жим")

    exercises.add("Становая тяга")
    exercises.add("Подтягивания")
    exercises.add("Тяга верхнего блока")
    exercises.add("Тяга верхнего блока стоя к коленям")
    exercises.add("Гиперэкстензия")
    exercises.add("Тяга нижнего блока к поясу")

    exercises.add("Пресс")
    exercises.add("")
    for (exercise in exercises) {
        sqLiteDatabase.execSQL("INSERT INTO $TABLE_EXERCISE($KEY_EXERCISE_NAME) VALUES('$exercise');")
    }
}

fun addTrainingTopic(sqLiteDatabase: SQLiteDatabase) {
    trainingTopic.add("Тренировка ног")
    trainingTopic.add("Тренировка груди")
    trainingTopic.add("Тренировка спины")
    for (trainingTopic in trainingTopic) sqLiteDatabase.execSQL("INSERT INTO $TABLE_TRAINING_TOPIC($KEY_TRAINING_TOPIC) VALUES('$trainingTopic');")
}

