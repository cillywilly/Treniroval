package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.R
import com.example.treniroval.itemAdapter.ItemAdapterTrainingConstructor
import kotlinx.android.synthetic.main.activity_training_constructor.*
import kotlinx.android.synthetic.main.item_training_exercises.*


class TrainingConstructorActivity : Activity() {

    private var managerDB= ManagerDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_constructor)

        managerDB.openDb()
        val listItemExercise = managerDB.getExercises()
        managerDB.closeDb()

        println( listItemExercise)
        exerciseList.hasFixedSize()
        exerciseList.layoutManager = LinearLayoutManager(this)

        exerciseList.adapter = ItemAdapterTrainingConstructor(listItemExercise,this)

    }

    fun onClickSave(view: View) {
        val buttonSave: Button = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            val exercises = ArrayList<String>()
            if (findViewById<CheckBox>(R.id.exercise).isChecked) {
                exercises.add(exercise.text as String)
            }
            if (findViewById<CheckBox>(R.id.exercise2).isChecked) {
                exercises.add(exercise2.text as String)
            }
            managerDB.openDb()
            managerDB.setExercisesList(exercises)
        }
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, SelectTrainingTopicActivity::class.java)
        startActivity(intent)
    }
}