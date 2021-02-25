package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.R
import com.example.treniroval.itemAdapter.ItemAdapterPastTraining

class PastTrainingsActivity : Activity() {

    private var managerDB= ManagerDB(this)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_trainings)

        managerDB.openDb()
        val listItems = managerDB.getPastTrainings()
        managerDB.closeDb()
        val pastTrainingList = findViewById<RecyclerView>(R.id.pastTrainingList)
        pastTrainingList.hasFixedSize()
        pastTrainingList.layoutManager = LinearLayoutManager(this)

        pastTrainingList.adapter = ItemAdapterPastTraining(listItems, this)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}