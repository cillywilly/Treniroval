package com.example.treniroval

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View


class SelectTrainingTopicActivity : Activity() {

    private var managerDB= ManagerDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_training_topic)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickButtonChest(view: View) {
        managerDB.openDb()
        managerDB.insertTraining("Тренировка груди")
        managerDB.closeDb()
        val intent = Intent(this, TrainingConstructorActivity::class.java)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickButtonLegs(view: View) {
        managerDB.openDb()
        managerDB.insertTraining("Тренировка ног")
        managerDB.closeDb()
        val intent = Intent(this, TrainingConstructorActivity::class.java)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickButtonBack(view: View) {
        managerDB.openDb()
        managerDB.insertTraining("Тренировка спины")
        managerDB.closeDb()
        val intent = Intent(this, TrainingConstructorActivity::class.java)
        startActivity(intent)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}