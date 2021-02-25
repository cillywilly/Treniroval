package com.example.treniroval.activitys

import android.annotation.SuppressLint
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
import com.example.treniroval.itemAdapter.ItemAdapterExerciseInTable
import kotlinx.android.synthetic.main.activity_current_past_training.*

class CurrentPastTainingActivity : Activity() {
    private var managerDB = ManagerDB(this)

    @SuppressLint("Recycle")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_past_training)
        trainingNameInTraining.text = intent.getStringExtra("trainingName")
        trainingDateInTraining.text = intent.getStringExtra("trainingDate")

        managerDB.openDb()

        val listItems = managerDB.getCurrentTraining(managerDB.getNewTrainingID())
        val currentPastTrainingList = findViewById<RecyclerView>(R.id.currentPastTrainingList)

        currentPastTrainingList.hasFixedSize()
        currentPastTrainingList.layoutManager = LinearLayoutManager(this)

        currentPastTrainingList.adapter = ItemAdapterExerciseInTable(listItems, this)

        managerDB.closeDb()
    }


    fun onClickBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
//var exerciseInTableList = ArrayList<ExerciseInTable>()
//
//        var approachInExerciseList1 = ArrayList<ApproachInExercise>()
//        approachInExerciseList1.add(ApproachInExercise("1", "0", "0", 1))
//        var exerciseInTable1= ExerciseInTable("odin",approachInExerciseList1)
//        exerciseInTableList.add(exerciseInTable1)
//
//        var approachInExerciseList2 = ArrayList<ApproachInExercise>()
//        approachInExerciseList2.add(ApproachInExercise("1", "0", "0", 1))
//        approachInExerciseList2.add(ApproachInExercise("2", "0", "0", 1))
//        var  exerciseInTable2= ExerciseInTable("dva",approachInExerciseList2)
//        exerciseInTableList.add(exerciseInTable2)
//
//        var approachInExerciseList3 = ArrayList<ApproachInExercise>()
//        approachInExerciseList3.add(ApproachInExercise("1", "0", "0", 1))
//        approachInExerciseList3.add(ApproachInExercise("2", "0", "0", 1))
//        approachInExerciseList3.add(ApproachInExercise("3", "0", "0", 1))
//        var exerciseInTable3= ExerciseInTable("tri",approachInExerciseList3)
//        exerciseInTableList.add(exerciseInTable3)
//        listItems=exerciseInTableList
//        for (item in exerciseInTableList) {
//            println("--------------------------------------------" + listItems.size)
//            println("название " + item.exerciseName)
//            for (it in item.listApproachesInExercise) {
//                println("номер подхода " + it.approachNumber)
//                println("повторений " + it.repeatSum)
//                println("назрузка " + it.load)
//            }
//        }