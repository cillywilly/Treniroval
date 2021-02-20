package com.example.treniroval.activitys

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.R
import com.example.treniroval.itemAdapter.ItemAdapterExerciseInTable
import kotlinx.android.synthetic.main.activity_current_past_training.*

class CurrentPastTainingActivity : Activity() {
    private var managerDB = ManagerDB(this)

    @SuppressLint("Recycle")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_past_training)
        trainingNameInTraining.text = intent.getStringExtra("trainingName")
        trainingDateInTraining.text = intent.getStringExtra("trainingDate")

        managerDB.openDb()

        val listItems = managerDB.getCurrentTraining(managerDB.getNewTrainingID())

        currentPastTrainingList.hasFixedSize()
        currentPastTrainingList.layoutManager = LinearLayoutManager(this)

        currentPastTrainingList.adapter = ItemAdapterExerciseInTable(listItems, this)

        managerDB.closeDb()
    }


    fun onClickBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}