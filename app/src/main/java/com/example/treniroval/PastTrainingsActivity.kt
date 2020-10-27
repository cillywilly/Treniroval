package com.example.treniroval

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_past_trainings.*

class PastTrainingsActivity : Activity() {

    private var managerDB= ManagerDB(this)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_trainings)



        managerDB.openDb()
        val listItems = managerDB.getPastTrainings()
        managerDB.closeDb()

        pastTrainingList.hasFixedSize()
        pastTrainingList.layoutManager = LinearLayoutManager(this)

        pastTrainingList.adapter = ItemAdapterPastTraining(listItems, this)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}