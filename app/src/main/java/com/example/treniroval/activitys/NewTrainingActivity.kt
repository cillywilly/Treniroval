package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.treniroval.R
import kotlinx.android.synthetic.main.activity_training_complited.*

class NewTrainingActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_complited)
        trainingNameInNewTraining.text= intent.getStringExtra("trainingName")
        trainingDateInNewTraining.text= intent.getStringExtra("trainingDate")


    }

    fun onClickBack(view: View) {
        val intent = Intent(this, TrainingConstructorActivity::class.java)

        startActivity(intent)
    }
}