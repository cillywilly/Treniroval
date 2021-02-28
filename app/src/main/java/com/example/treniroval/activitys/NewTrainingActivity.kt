package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.ListItem.ExerciseInTable
import com.example.treniroval.R
import com.example.treniroval.itemAdapter.ItemAdapterNewTraining
import kotlinx.android.synthetic.main.activity_training_constructor.*


class NewTrainingActivity : Activity() {

    private var managerDB = ManagerDB(this)
    private var newTrainingID = 1
    private lateinit var listItems: ArrayList<ExerciseInTable>
    private lateinit var newTrainingList :RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_training)
        newTrainingList = findViewById<RecyclerView>(R.id.newTrainingList)
        val trainingName = intent.getStringExtra("trainingName")
        val trainingDate = intent.getStringExtra("trainingDate")
        trainingNameInNewTraining.text = trainingName
        trainingDateInNewTraining.text = trainingDate

        managerDB.openDb()
        newTrainingID = managerDB.getNewTrainingID()
        listItems = managerDB.getCurrentTraining(newTrainingID)

        newTrainingList.hasFixedSize()
        newTrainingList.layoutManager = LinearLayoutManager(this)

        newTrainingList.adapter = ItemAdapterNewTraining(listItems, this, managerDB)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onClickSaveApp(view: View) {
        val adapter= newTrainingList.adapter
        listItems = managerDB.getCurrentTraining(newTrainingID)
        for (i in 1..adapter?.itemCount!!) {
            val repeats: String = findViewById<EditText>(R.id.repeatInExercise).text.toString()
            val load: String = findViewById<EditText>(R.id.worckloadInExercise).text.toString()

            managerDB.saveApproach(listItems, newTrainingID, repeats, load)

        }

    }
}
