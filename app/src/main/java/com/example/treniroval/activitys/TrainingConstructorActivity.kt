package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.R
import com.example.treniroval.itemAdapter.ItemAdapterTrainingConstructor
import kotlinx.android.synthetic.main.activity_training_constructor.*

class TrainingConstructorActivity : Activity() {

    private var managerDB = ManagerDB(this)
    private lateinit var selectedItems: ArrayList<String>
    private lateinit var trainingName:String
    private lateinit var trainingDate:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trainingName = intent.getStringExtra("trainingName").toString()
        trainingDate  = intent.getStringExtra("trainingDate").toString()
        setContentView(R.layout.activity_training_constructor)
        trainingNameInNewTraining.text= trainingName
        trainingDateInNewTraining.text= trainingDate
        managerDB.openDb()
        val listItemExercise = managerDB.getExercises()
        managerDB.closeDb()

        println(listItemExercise)
        exerciseList.hasFixedSize()
        exerciseList.layoutManager = LinearLayoutManager(this)
        val r = ItemAdapterTrainingConstructor(listItemExercise, this)

        exerciseList.adapter = r
        selectedItems = r.selectedItems
    }

    fun onClickSave(view: View) {
        val buttonSave: Button = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            managerDB.openDb()
            managerDB.setExercisesList(selectedItems)
            val intent = Intent(this, NewTrainingActivity::class.java)
            intent.putExtra("trainingName", trainingName)
            intent.putExtra("trainingDate", trainingDate)
            startActivity(intent)
        }
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, SelectTrainingTopicActivity::class.java)
        startActivity(intent)
    }
}