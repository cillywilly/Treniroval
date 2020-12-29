package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.R
import kotlinx.android.synthetic.main.activity_past_trainings.*

class CurrentPastTainingActivity : Activity() {
    private var managerDB= ManagerDB(this)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_past_training)



        managerDB.openDb()
//        val listItems = managerDB.getPastTrainings()//нужно вернуть вругой лист
        managerDB.closeDb()

        pastTrainingList.hasFixedSize()
        pastTrainingList.layoutManager = LinearLayoutManager(this)

//        pastTrainingList.adapter = ItemAdapterExerciseInTable(listItems, this)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}