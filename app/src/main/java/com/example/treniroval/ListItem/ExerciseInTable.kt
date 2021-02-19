package com.example.treniroval.ListItem

import android.widget.ImageButton


data class ExerciseInTable(
    var exerciseName: String,
    var approachInExerciseListItem: ArrayList<ApproachInExerciseListItem>,
    var addApproach: ImageButton?
)