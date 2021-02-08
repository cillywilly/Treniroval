package com.example.treniroval.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View


class TrainingComplitedActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    fun onClickBack(view: View) {
        val intent = Intent(this, PastTrainingsActivity::class.java)
        startActivity(intent)
    }
}


