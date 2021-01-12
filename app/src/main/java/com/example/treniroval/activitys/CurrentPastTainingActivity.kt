package com.example.treniroval.activitys

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.treniroval.DB.DBHelper
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.R
import com.example.treniroval.itemAdapter.ItemAdapterExerciseInTable
import kotlinx.android.synthetic.main.activity_current_past_training.*

class CurrentPastTainingActivity : Activity() {
    private var managerDB= ManagerDB(this)

    @SuppressLint("Recycle")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_past_training)



        managerDB.openDb()

        val listItems = managerDB.getCurrentTraining()
        val s: Cursor? =
            managerDB.db?.rawQuery("select * from ${DBHelper.TABLE_TRAINING_EXERCISE}", null)
        if (s != null) {
            s.moveToFirst()
            println(s.getString(0))
            println(s.getString(1))
        }


        for (exercise in listItems) {
            println("YPRAGNENIE : "+exercise)
        }
        managerDB.closeDb()

        currentPastTrainingList.hasFixedSize()
        currentPastTrainingList.layoutManager = LinearLayoutManager(this)

        currentPastTrainingList.adapter = ItemAdapterExerciseInTable(listItems, this)
    }


    fun onClickBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}