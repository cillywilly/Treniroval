package com.example.treniroval.DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.treniroval.DB.DBHelper.Companion
import com.example.treniroval.DB.DBHelper.Companion.APPROACH
import com.example.treniroval.DB.DBHelper.Companion.DATE
import com.example.treniroval.DB.DBHelper.Companion.EXERCISE_NAME
import com.example.treniroval.DB.DBHelper.Companion.EXERCISE_NAME_IN_TRAINING
import com.example.treniroval.DB.DBHelper.Companion.ID_EXERCISE
import com.example.treniroval.DB.DBHelper.Companion.ID_TRAINING
import com.example.treniroval.DB.DBHelper.Companion.ID_TRAINING_TOPIC
import com.example.treniroval.DB.DBHelper.Companion.NUMBER_EXERCISES
import com.example.treniroval.DB.DBHelper.Companion.REPEAT
import com.example.treniroval.DB.DBHelper.Companion.TABLE_TRAINING
import com.example.treniroval.DB.DBHelper.Companion.TABLE_TRAINING_EXERCISE
import com.example.treniroval.DB.DBHelper.Companion.TABLE_TRAINING_TOPIC
import com.example.treniroval.DB.DBHelper.Companion.TRAINING_TOPIC
import com.example.treniroval.DB.DBHelper.Companion.WORKLOAD
import com.example.treniroval.ListItem.ApproachInExercise
import com.example.treniroval.ListItem.Exercise
import com.example.treniroval.ListItem.ExerciseInTable
import com.example.treniroval.ListItem.PastTraining

class ManagerDB(context: Context) {
    private val DBHelper = DBHelper(context)
    lateinit var db: SQLiteDatabase


    @SuppressLint("Recycle")
    fun getNewTrainingID(): Int {
        val cursor = db.query(
            TABLE_TRAINING,
            null, null, null, null, null, ID_TRAINING
        )
        cursor.moveToLast()
        return cursor.getInt(cursor.getColumnIndex(ID_TRAINING))
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
    fun insertTraining(trainingTopic: String, date: String) {
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
            put(NUMBER_EXERCISES, 1)
        }
        println(value)
        db.insert(TABLE_TRAINING, null, value)
    }

    @SuppressLint("Recycle")
    fun getPastTrainings(): ArrayList<PastTraining> {
        val trainingList = ArrayList<PastTraining>()
        val cursor =
            db.query(Companion.TABLE_TRAINING, null, null, null, null, null, null)
        while (cursor?.moveToNext()!!) {
            val trainingTopicID = cursor.getInt(cursor.getColumnIndex(ID_TRAINING_TOPIC))
            val cursor1 = db.query(
                TABLE_TRAINING_TOPIC,
                null, "$ID_TRAINING_TOPIC = $trainingTopicID", null, null, null, null
            )
            cursor1?.moveToFirst()!!
            val trainingTopic = cursor1.getString(cursor1.getColumnIndex(TRAINING_TOPIC))
            val date = cursor.getString(cursor.getColumnIndex(DATE))
            val numberExercises = cursor.getInt(cursor.getColumnIndex(NUMBER_EXERCISES))
            trainingList.add(PastTraining(trainingTopic, date, numberExercises))
        }
        return trainingList
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
        trainingId: Int,
    ): ArrayList<ExerciseInTable> {
        val listItemExerciseInTable = ArrayList<ExerciseInTable>()
        var exerciseName = "BEZ NAZVANIYA"
        val exerciseCursor = db.query(
            TABLE_TRAINING, null,
            "$ID_TRAINING=$trainingId",
            null, null, null, null
        )
        exerciseCursor.moveToFirst()
        val exercisesCount: Int =
            exerciseCursor.getInt(exerciseCursor.getColumnIndex(NUMBER_EXERCISES))

        for (exerciseId in 1..exercisesCount) {
            val approaches = ArrayList<ApproachInExercise>()
            val query =
                "SELECT * FROM $TABLE_TRAINING_EXERCISE WHERE $ID_TRAINING=$trainingId and $ID_EXERCISE=$exerciseId"
            val cursor = db.rawQuery(query, null)
            while (cursor.moveToNext()) {
                val idTraining = cursor.getInt(cursor.getColumnIndex(ID_TRAINING))
                val idExercise = cursor.getInt(cursor.getColumnIndex(ID_EXERCISE))
                val numOfApproach = cursor.getString(cursor.getColumnIndex(APPROACH))
                exerciseName = getExerciseName(idTraining, idExercise)
                val sumOfRepeats = cursor.getString(cursor.getColumnIndex(REPEAT))
                val workLoad = cursor.getString(cursor.getColumnIndex(WORKLOAD))

                approaches.add(
                    ApproachInExercise(numOfApproach, sumOfRepeats, workLoad)
                )
            }
            listItemExerciseInTable.add(
                ExerciseInTable(exerciseName, approaches)
            )
        }
        return listItemExerciseInTable
    }

    fun setExercisesList(exercisesNames: ArrayList<String>) {
        val lastTrainingId = getNewTrainingID()
        db.execSQL("update $TABLE_TRAINING set $NUMBER_EXERCISES=${exercisesNames.size} where $ID_TRAINING =$lastTrainingId")
        var count = 1
        for (exerciseName in exercisesNames) {
            db.execSQL(
                "INSERT INTO $TABLE_TRAINING_EXERCISE" +
                        "($ID_TRAINING, $ID_EXERCISE,$APPROACH,$REPEAT,$WORKLOAD, $EXERCISE_NAME_IN_TRAINING) " +
                        "VALUES($lastTrainingId, $count,'1', '0', '0', '$exerciseName');"
            )
            count++
        }
    }

    fun closeDb() {
        db.close()
    }

    fun addApproach(exerciseId: Int, approachNum: Int, exerciseName: String) {
        addApproach(getNewTrainingID(), exerciseId, approachNum, exerciseName)
    }

    fun addApproach(trainingId: Int, exerciseId: Int, approachNum: Int, exerciseName: String) {
        db.execSQL(
            "INSERT INTO $TABLE_TRAINING_EXERCISE " +
                    "($ID_TRAINING, $ID_EXERCISE,$APPROACH, $EXERCISE_NAME_IN_TRAINING, $REPEAT, $WORKLOAD) " +
                    "VALUES('$trainingId', '$exerciseId', '$approachNum', '$exerciseName', " +
                    "'0', " +
                    "'0');"
        )
    }

    @SuppressLint("Recycle")
    fun getApproachNum(trainingId: Int, exerciseNum: Int): Int {
        val query =
            "SELECT APPROACH FROM $TABLE_TRAINING_EXERCISE WHERE $ID_TRAINING='$trainingId' and $ID_EXERCISE='$exerciseNum' order by id_training_exercise"
        val cursor = db.rawQuery(query, null)
        cursor.moveToLast()
        return cursor.getInt(cursor.getColumnIndex(APPROACH)) + 1
    }

    @SuppressLint("Recycle")
    fun getExerciseName(trainingId: Int, exerciseNum: Int): String {
        val query =
            "SELECT EXERCISE_NAME_IN_TRAINING FROM $TABLE_TRAINING_EXERCISE WHERE $ID_TRAINING='$trainingId' and $ID_EXERCISE='$exerciseNum' order by id_training_exercise"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()
        return cursor.getString(cursor.getColumnIndex(EXERCISE_NAME_IN_TRAINING))
    }

    @SuppressLint("Recycle")
    fun saveApproach(
        exercises: ArrayList<ExerciseInTable>,
        trainingId: Int,
        repeats: String,
        load: String
    ) {
        var i = 1
        for (exercise in exercises) {
            for (approach in exercise.listApproachesInExercise) {
                val approachNum = approach.approachNumber
                val values = ContentValues().apply {
                    put(REPEAT, repeats)
                    put(WORKLOAD, load)
                }
                db.update(
                    TABLE_TRAINING_EXERCISE,
                    values,
                    "$ID_TRAINING='$trainingId' and $ID_EXERCISE='$i' and $APPROACH='$approachNum'",
                    null
                )
            }
            i++
        }
    }

    fun saveRepeat(trainingId: Int, exerciseId: Int, approachNum: Int, repeats: String) {
        val values = ContentValues().apply {
            put(REPEAT, repeats)
        }
        db.update(
            TABLE_TRAINING_EXERCISE,
            values,
            "$ID_TRAINING='$trainingId' and $ID_EXERCISE='${exerciseId+1}' and $APPROACH='${approachNum+1}'",
            null
        )
    }

    fun saveLoad(trainingId: Int, exerciseId: Int, approachNum: Int, load: String) {
        val values = ContentValues().apply {
            put(WORKLOAD, load)
        }
        db.update(
            TABLE_TRAINING_EXERCISE,
            values,
            "$ID_TRAINING='$trainingId' and $ID_EXERCISE='${exerciseId+1}' and $APPROACH='${approachNum+1}'",
            null
        )
    }
}
