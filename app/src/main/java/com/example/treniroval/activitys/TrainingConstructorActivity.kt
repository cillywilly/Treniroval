package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.R
import com.example.treniroval.itemAdapter.ItemAdapterTrainingConstructor
import kotlinx.android.synthetic.main.activity_training_constructor.*


class TrainingConstructorActivity : Activity() {

    private var managerDB = ManagerDB(this)
    private lateinit var selectedItems1: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_constructor)

        managerDB.openDb()
        val listItemExercise = managerDB.getExercises()
        managerDB.closeDb()

        println(listItemExercise)
        exerciseList.hasFixedSize()
        exerciseList.layoutManager = LinearLayoutManager(this)
        val r = ItemAdapterTrainingConstructor(listItemExercise, this)
        exerciseList.adapter = r
        selectedItems1 = r.selectedItems
    }

    fun onClickSave(view: View) {
        val buttonSave: Button = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            managerDB.openDb()
            managerDB.setExercisesList(selectedItems1)
        }
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, SelectTrainingTopicActivity::class.java)
        startActivity(intent)
    }
}