package com.example.treniroval

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class TrainingConstructorActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_constructor)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, SelectTrainingTopicActivity::class.java)
        startActivity(intent)
    }
}