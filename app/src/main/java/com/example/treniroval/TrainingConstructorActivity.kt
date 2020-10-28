package com.example.treniroval

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_training_constructor.*


class TrainingConstructorActivity : Activity() {

    private var managerDB= ManagerDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_constructor)

//        managerDB.openDb()
//        val listItemExercise = managerDB.getExercises()
//        managerDB.closeDb()

        val listItemExercise = ArrayList<ListItemExercise>()
        listItemExercise.add(ListItemExercise("dsads"))
        listItemExercise.add(ListItemExercise("aa"))
        listItemExercise.add(ListItemExercise("ass"))


        exerciseList.hasFixedSize()
        exerciseList.layoutManager = LinearLayoutManager(this)


        exerciseList.adapter = ItemAdapterTrainingConstructor(listItemExercise,this)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, SelectTrainingTopicActivity::class.java)
        startActivity(intent)
    }
}