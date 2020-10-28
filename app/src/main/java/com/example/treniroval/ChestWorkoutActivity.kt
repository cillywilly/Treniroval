package com.example.treniroval

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.treniroval.Activitys.TrainingConstructorActivity

class ChestWorkoutActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, TrainingConstructorActivity::class.java)
        startActivity(intent)
    }
}