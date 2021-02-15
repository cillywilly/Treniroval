package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.R
import com.example.treniroval.itemAdapter.ItemAdapterNewTraining
import kotlinx.android.synthetic.main.activity_training_complited.*

class NewTrainingActivity : Activity() {
    private var managerDB = ManagerDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_complited)
        trainingNameInNewTraining.text= intent.getStringExtra("trainingName")
        trainingDateInNewTraining.text= intent.getStringExtra("trainingDate")

        managerDB.openDb()

        val listItems = managerDB.getCurrentTraining("1")
        newTrainingList.hasFixedSize()
        newTrainingList.layoutManager = LinearLayoutManager(this)

        newTrainingList.adapter = ItemAdapterNewTraining(listItems, this)

    }

    fun onClickBack(view: View) {
        val intent = Intent(this, TrainingConstructorActivity::class.java)
        startActivity(intent)
    }
}