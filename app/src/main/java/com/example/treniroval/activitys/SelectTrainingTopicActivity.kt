package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class SelectTrainingTopicActivity : Activity() {

    @androidx.annotation.RequiresApi(Build.VERSION_CODES.O)
    val dateTime: LocalDateTime = LocalDateTime.now()
    @androidx.annotation.RequiresApi(Build.VERSION_CODES.O)
    val date = dateTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"))
    private var managerDB = ManagerDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_training_topic)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickButtonChest(view: View) {
        managerDB.openDb()
        managerDB.insertTraining("Тренировка груди", date)
        managerDB.closeDb()
         intent = Intent(this, TrainingConstructorActivity::class.java)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickButtonLegs(view: View) {
        managerDB.openDb()
        managerDB.insertTraining("Тренировка ног", date)
        managerDB.closeDb()
         intent = Intent(this, TrainingConstructorActivity::class.java)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickButtonBack(view: View) {
        managerDB.openDb()
        managerDB.insertTraining("Тренировка спины", date)
        managerDB.closeDb()
         intent = Intent(this, TrainingConstructorActivity::class.java)
        startActivity(intent)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}