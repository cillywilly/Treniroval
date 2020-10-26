package com.example.treniroval

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View


class SelectTrainingTopicActivity : Activity() {

    private var dbHelper = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_training_topic)

    }


    fun onClickButtonChest(view: View) {
        val intent = Intent(this, TrainingConstructorActivity::class.java)
        startActivity(intent)
    }

    fun onClickBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}