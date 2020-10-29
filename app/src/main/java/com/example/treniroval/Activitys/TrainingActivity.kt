package com.example.treniroval.Activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.treniroval.R

class TrainingActivity : Activity() {

    val KEY_ID_TRAINING: String = ""
    val KEY_ID_EXERCISE: String = ""
    val KEY_APPROACH: String = ""
    val KEY_REPEAT: String = ""
    val KEY_WORKLOAD: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_complited)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, PastTrainingsActivity::class.java)
        startActivity(intent)
    }
}


