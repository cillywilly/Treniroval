package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.R

class MainActivity : Activity() {

    private var managerDB= ManagerDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickPastPractice(view: View) {
        val intent = Intent(this, PastTrainingsActivity::class.java)
        startActivity(intent)
    }

    fun onClickNewPractice(view: View) {
        val intent = Intent(this, SelectTrainingTopicActivity::class.java)
        startActivity(intent)
    }

    fun onClickDrop(view: View) {
        managerDB.openDb()
        managerDB.dropDB()
    }
}