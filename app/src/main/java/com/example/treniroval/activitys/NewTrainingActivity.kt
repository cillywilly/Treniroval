package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.ListItem.ExerciseInTable
import com.example.treniroval.R
import com.example.treniroval.itemAdapter.ItemAdapterNewTraining
import kotlinx.android.synthetic.main.activity_new_training.*
import kotlinx.android.synthetic.main.activity_training_constructor.trainingDateInNewTraining
import kotlinx.android.synthetic.main.activity_training_constructor.trainingNameInNewTraining


class NewTrainingActivity : Activity() {

    private var managerDB = ManagerDB(this)
    private lateinit var newTrainingID:String
    private lateinit var listItems :ArrayList<ExerciseInTable>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_training)

        val trainingName = intent.getStringExtra("trainingName")
        val trainingDate = intent.getStringExtra("trainingDate")
        trainingNameInNewTraining.text = trainingName
        trainingDateInNewTraining.text = trainingDate

        managerDB.openDb()
        newTrainingID = managerDB.getLastTrainingID()
        listItems = managerDB.getCurrentTraining(newTrainingID)

        newTrainingList.hasFixedSize()
        newTrainingList.layoutManager = LinearLayoutManager(this)

        newTrainingList.adapter = ItemAdapterNewTraining(listItems, this)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onClickAddApproach(view: View) {
        managerDB.addApproach("3","2")
        listItems = managerDB.getCurrentTraining(newTrainingID)
        newTrainingList.adapter = ItemAdapterNewTraining(listItems, this)
    }
}
