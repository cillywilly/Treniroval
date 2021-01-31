package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.R
import com.example.treniroval.itemAdapter.ItemAdapterTrainingConstructor
import kotlinx.android.synthetic.main.activity_training_constructor.*


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

    fun onClickBack(view: View) {
        val intent = Intent(this, SelectTrainingTopicActivity::class.java)
        startActivity(intent)
    }
}