package com.example.treniroval.DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.support.annotation.RequiresApi
import com.example.treniroval.DB.DBHelper.Companion
import com.example.treniroval.DB.DBHelper.Companion.APPROACH
import com.example.treniroval.DB.DBHelper.Companion.DATE
import com.example.treniroval.DB.DBHelper.Companion.EXERCISE_NAME
import com.example.treniroval.DB.DBHelper.Companion.ID_EXERCISE
import com.example.treniroval.DB.DBHelper.Companion.ID_TRAINING
import com.example.treniroval.DB.DBHelper.Companion.ID_TRAINING_EXERCISE
import com.example.treniroval.DB.DBHelper.Companion.ID_TRAINING_TOPIC
import com.example.treniroval.DB.DBHelper.Companion.REPEAT
import com.example.treniroval.DB.DBHelper.Companion.TABLE_TRAINING
import com.example.treniroval.DB.DBHelper.Companion.TRAINING_TOPIC
import com.example.treniroval.DB.DBHelper.Companion.WORKLOAD
import com.example.treniroval.ListItem.ApproachInExerciseListItem
import com.example.treniroval.ListItem.Exercise
import com.example.treniroval.ListItem.ExerciseInTable
import com.example.treniroval.ListItem.PastTraining

class ManagerDB(context: Context) {
    private val DBHelper = DBHelper(context)
    lateinit var db: SQLiteDatabase


    @SuppressLint("Recycle")
    fun getLastTrainingID(): String? {
        val cursor = db.query(
            TABLE_TRAINING,
            null, null, null, null, null, null
        )
        cursor.moveToLast()
        var s = "0"
        s = cursor.getString(cursor.getColumnIndex(ID_TRAINING))
        return s
    }

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
    fun insertTraining(trainingTopic: String, date:String) {
        val value = ContentValues().apply {
            put(DATE, date)
            when (trainingTopic) {
                "Тренировка ног" -> {
                    put(ID_TRAINING_TOPIC, 1)
                }
                "Тренировка груди" -> {
                    put(ID_TRAINING_TOPIC, 2)
                }
                "Тренировка спины" -> {
                    put(ID_TRAINING_TOPIC, 3)
                }
            }
        }
        println(value)

        db.insert(TABLE_TRAINING, null, value)
    }

    @SuppressLint("Recycle")
    fun getPastTrainings(): ArrayList<PastTraining> {
        val listItems = ArrayList<PastTraining>()
        val cursor =
            db.query(Companion.TABLE_TRAINING, null, null, null, null, null, ID_TRAINING)
        while (cursor?.moveToNext()!!) {
            val trainingTopicID = cursor.getString(cursor.getColumnIndex(ID_TRAINING_TOPIC))
            val cursor1 = db.query(
                Companion.TABLE_TRAINING_TOPIC,
                null, "$ID_TRAINING_TOPIC = $trainingTopicID", null, null, null, null
            )
            cursor1?.moveToFirst()!!
            val trainingTopic = cursor1.getString(cursor1.getColumnIndex(TRAINING_TOPIC))
            val date = cursor.getString(cursor.getColumnIndex(DATE))
            listItems.add(PastTraining(trainingTopic, date))
        }
        return listItems
    }

    @SuppressLint("Recycle")
    fun getExercises(): ArrayList<Exercise> {
        val listItemExercise = ArrayList<Exercise>()
        val cursor =
            db.query(Companion.TABLE_EXERCISE, null, null, null, null, null, ID_EXERCISE)
        while (cursor?.moveToNext()!!) {
            val exercise = cursor.getString(cursor.getColumnIndex(EXERCISE_NAME))
            var exercise2 = ""
            if (cursor.moveToNext()) {
                exercise2 = cursor.getString(cursor.getColumnIndex(EXERCISE_NAME))
            }
            listItemExercise.add(Exercise(exercise, exercise2))
        }
        return listItemExercise
    }

    @SuppressLint("Recycle")
    fun getCurrentTraining(
        trainingId: String,
    ): ArrayList<ExerciseInTable> {
        val listItemExerciseInTable = ArrayList<ExerciseInTable>()
        val approachListItems: ArrayList<ApproachInExerciseListItem> = ArrayList()
        var exerciseName = "BEZ NAZVANIYA"
        val exerciseCursor = db.query(
            Companion.TABLE_TRAINING_EXERCISE, null,
            "$ID_TRAINING='$trainingId'",
            null, null, null, ID_TRAINING_EXERCISE
        )
        exerciseCursor.moveToLast()
        val exercises: Int =
            exerciseCursor.getInt(exerciseCursor.getColumnIndex(ID_EXERCISE))
        for (i in 1..exercises) {
            approachListItems.clear()
            val cursor = db.query(
                Companion.TABLE_TRAINING_EXERCISE, null,
                "$ID_TRAINING='$trainingId' and $ID_EXERCISE='$i'",
                null, null, null, ID_TRAINING_EXERCISE
            )
            while (cursor?.moveToNext()!!) {
                val idTraining = cursor.getString(cursor.getColumnIndex(ID_TRAINING))
                val idExercise = cursor.getString(cursor.getColumnIndex(ID_EXERCISE))
                exerciseName = getExerciseName(db, idExercise)
                val numOfApproach = cursor.getString(cursor.getColumnIndex(APPROACH))
                val sumOfRepeats = cursor.getString(cursor.getColumnIndex(REPEAT))
                val workLoad = cursor.getString(cursor.getColumnIndex(WORKLOAD))
                approachListItems.add(
                    ApproachInExerciseListItem(
                        numOfApproach,
                        sumOfRepeats,
                        workLoad
                    )
                )
            }
            listItemExerciseInTable.add(
                ExerciseInTable(exerciseName, approachListItems)
            )
        }
        return listItemExerciseInTable
    }

    fun setExercisesList(exercisesNames: ArrayList<String>) {
        val lastTrainigid = getLastTrainingID()
//        val approachListItems: ArrayList<ApproachInExerciseListItem> = ArrayList()
//        val exerciseTraining: ArrayList<ExerciseInTable> = ArrayList()
//
//        val approachInExercise = ApproachInExerciseListItem("1", "0", "0")
//        approachListItems.add(approachInExercise)
        var count = 1
        for (exerciseName in exercisesNames) {
//            val exercise = ExerciseInTable(exerciseName, approachListItems)
//            exerciseTraining.add(exercise)

            db.execSQL(
                "INSERT INTO ${Companion.TABLE_TRAINING_EXERCISE}" +
                        "($ID_TRAINING, $ID_EXERCISE,$APPROACH,$REPEAT,$WORKLOAD) " +
                        "VALUES(${lastTrainigid}, $count,'1', '0', '0');"
            )
            count++
        }
    }


    @SuppressLint("Recycle")
    fun select(db: SQLiteDatabase, table: String, selection: String, column: String): String {
        val cursor = db.query(table, null, selection, null, null, null, null)
        cursor.moveToFirst()
        return cursor.getString(cursor.getColumnIndex(column))
    }

    fun closeDb() {
        db.close()
    }

    @SuppressLint("Recycle")
    fun getExerciseName(db: SQLiteDatabase, exerciseId: String): String {
        val cursor = db.query(
            Companion.TABLE_EXERCISE, null, "$ID_EXERCISE='$exerciseId'",
            null, null, null, null
        )
        cursor.moveToFirst()
        return cursor.getString(cursor.getColumnIndex(EXERCISE_NAME))
    }
}
