package com.example.treniroval

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_past_trainings.*
import java.time.LocalDateTime

class PastTrainingSessionsActivity : Activity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_trainings)

//        val trainingList = ArrayList<String>()
//        trainingList.add("Ноги")
//        trainingList.add("Грудь")
//        trainingList.add("Спина")

        val listItems = ArrayList<ListItem>()
        listItems.add(ListItem("Тренировка ног", LocalDateTime.now()))
        listItems.add(ListItem("Тренировка груди", LocalDateTime.MIN))
        listItems.add(ListItem("Тренировка спины", LocalDateTime.MAX))

        pastTrainingList.hasFixedSize()
        pastTrainingList.layoutManager = LinearLayoutManager(this)

        pastTrainingList.adapter = ItemAdapter(listItems, this)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}