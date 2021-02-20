package com.example.treniroval.itemAdapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.treniroval.ListItem.ExerciseInTable
import com.example.treniroval.R


class ItemAdapterExerciseInTable(
    listItemExerciseInTable: ArrayList<ExerciseInTable>,
    context: Context
) : RecyclerView.Adapter<ItemAdapterExerciseInTable.ViewHolder>() {
    var listItemR = listItemExerciseInTable
    var contextR = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val exerciseName = view.findViewById<TextView>(R.id.exerciseNameInPastTrainings)
        private val approach = view.findViewById<ListView>(R.id.approachesListView)
        private val linearLayout = view.findViewById<LinearLayout>(R.id.approachLinearLayout)

        fun bind(listItemExerciseInTable: ExerciseInTable, context: Context) {
            exerciseName.text = listItemExerciseInTable.exerciseName
            val adapter = ItemAdapterApproachInExercise(
                listItemExerciseInTable.listApproachesInExercise,
                context
            )
            approach.adapter = adapter

            val i = listItemExerciseInTable.listApproachesInExercise.size
            linearLayout.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,100 * i)

            itemView.setOnClickListener {
                Toast.makeText(context, "Pressed: ${exerciseName.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextR)
        return ViewHolder(
            inflater.inflate(
                R.layout.item_exercise_in_table,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listItemR.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val listItem = listItemR[p1]
        p0.bind(listItem, contextR)
    }
}

