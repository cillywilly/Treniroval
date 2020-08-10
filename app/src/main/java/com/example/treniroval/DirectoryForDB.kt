package com.example.treniroval

import android.database.sqlite.SQLiteDatabase
import com.example.treniroval.DBHelper.Companion.KEY_EXERCISE_NAME
import com.example.treniroval.DBHelper.Companion.KEY_TRAINING_TOPIC
import com.example.treniroval.DBHelper.Companion.TABLE_EXERCISE
import com.example.treniroval.DBHelper.Companion.TABLE_TRAINING_TOPIC

var exercises: ArrayList<String> = ArrayList()
var trainingTopic: ArrayList<String> = ArrayList()

fun addExercise(sqLiteDatabase: SQLiteDatabase) {
    exercises.add("Приседания со штангой"); exercises.add("Жим ногами в тренажёре")
    exercises.add("Выпады"); exercises.add("Выпады"); exercises.add("Разгибание ног")
    exercises.add("Сгибание ног лежа")
    exercises.add("Жим штанги лежа"); exercises.add("Жим гантелей лежа")
    exercises.add("Сведение рук в тренажере")
    exercises.add("Подтягивания"); exercises.add("Тяга верхнего блока")
    exercises.add("Тяга верхнего блока стоя к коленям"); exercises.add("Становая тяга")
    exercises.add("Гиперэкстензия"); exercises.add("Тяга к поясу нижнего блока")
//    exercises.add("")
    for (exercise in exercises) sqLiteDatabase.execSQL("INSERT INTO $TABLE_EXERCISE($KEY_EXERCISE_NAME) VALUES($exercise);")
}

fun addTrainingTopic(sqLiteDatabase: SQLiteDatabase) {
    trainingTopic.add("Ноги")
    trainingTopic.add("Грудь")
    trainingTopic.add("Спина")
    for (trainingTopic in trainingTopic) sqLiteDatabase.execSQL("INSERT INTO $TABLE_TRAINING_TOPIC($KEY_TRAINING_TOPIC) VALUES($trainingTopic);")
}