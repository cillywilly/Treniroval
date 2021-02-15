package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.treniroval.R
import kotlinx.android.synthetic.main.activity_training_constructor.*


class TrainingComplitedActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_complited)

        val trainingName = intent.getStringExtra("trainingName")
        val trainingDate  = intent.getStringExtra("trainingDate")
        trainingNameInNewTraining.text= trainingName
        trainingDateInNewTraining.text= trainingDate
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, PastTrainingsActivity::class.java)
        startActivity(intent)
    }
}


