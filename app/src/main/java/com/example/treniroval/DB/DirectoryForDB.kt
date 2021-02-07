package com.example.treniroval.DB

import android.database.sqlite.SQLiteDatabase
import com.example.treniroval.DB.DBHelper.Companion.APPROACH
import com.example.treniroval.DB.DBHelper.Companion.EXERCISE_NAME
import com.example.treniroval.DB.DBHelper.Companion.REPEAT
import com.example.treniroval.DB.DBHelper.Companion.TABLE_EXERCISE
import com.example.treniroval.DB.DBHelper.Companion.TABLE_TRAINING_EXERCISE
import com.example.treniroval.DB.DBHelper.Companion.TABLE_TRAINING_TOPIC
import com.example.treniroval.DB.DBHelper.Companion.TRAINING_TOPIC
import com.example.treniroval.DB.DBHelper.Companion.WORKLOAD
import com.example.treniroval.ListItem.ExerciseInTable
import com.example.treniroval.ListItem.ListItemApproachInExercise

var exercises: ArrayList<String> = ArrayList()
var trainingTopic: ArrayList<String> = ArrayList()
var exerciseTraining: ArrayList<ExerciseInTable> = ArrayList()

fun addApproach(sqLiteDatabase: SQLiteDatabase) {
    val approaches: ArrayList<ListItemApproachInExercise> = ArrayList()

    for (i in 1..4) {
        val app: String = i.toString()
        val rep: String = (8..12).random().toString()
        val loa: String = (40..70).random().toString()
        val approachInExercise = ListItemApproachInExercise(app, rep, loa)
        approaches.add(approachInExercise)
    }
    val exercise = ExerciseInTable("Приседания со штангой", approaches)
    exerciseTraining.add(exercise)

    for (appproach in exerciseTraining) {
        for (approach in appproach.listItemApproachInExercise) {
            sqLiteDatabase.execSQL(
                "INSERT INTO $TABLE_TRAINING_EXERCISE" +
                        "(${DBHelper.ID_TRAINING}, ${DBHelper.ID_EXERCISE},$APPROACH,$REPEAT,$WORKLOAD) " +
                        "VALUES('1', '1','${approach.approachNumber}', " +
                        "'${approach.repeatSum}', " +
                        "'${approach.load}');"
            )
        }}
    for (appproach in exerciseTraining) {
        for (approach in appproach.listItemApproachInExercise) {
            sqLiteDatabase.execSQL(
                "INSERT INTO $TABLE_TRAINING_EXERCISE" +
                        "(${DBHelper.ID_TRAINING}, ${DBHelper.ID_EXERCISE},$APPROACH,$REPEAT,$WORKLOAD) " +
                        "VALUES('1', '2','${approach.approachNumber}', " +
                        "'${approach.repeatSum}', " +
                        "'${approach.load}');"
            )
        }}
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
//    exercises.add("")
    for (exercise in exercises) {
        sqLiteDatabase.execSQL("INSERT INTO $TABLE_EXERCISE($EXERCISE_NAME) VALUES('$exercise');")
    }
    //так можно саутить из базы)
//    val s: Cursor = sqLiteDatabase.rawQuery("select * from $TABLE_EXERCISE", null)
//    s.moveToFirst()
//    println(s.getString(0))
//    println(s.getString(1))


}

fun addTrainingTopic(sqLiteDatabase: SQLiteDatabase) {
    trainingTopic.add("Тренировка ног")
    trainingTopic.add("Тренировка груди")
    trainingTopic.add("Тренировка спины")
    for (trainingTopic in trainingTopic) sqLiteDatabase.execSQL("INSERT INTO $TABLE_TRAINING_TOPIC($TRAINING_TOPIC) VALUES('$trainingTopic');")
}

