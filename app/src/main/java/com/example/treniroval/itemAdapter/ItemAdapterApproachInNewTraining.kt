package com.example.treniroval.itemAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.treniroval.ListItem.ApproachInExerciseListItem
import com.example.treniroval.R

class ItemAdapterApproachInNewTraining(
    private val approachInExerciseListItem: ArrayList<ApproachInExerciseListItem>,
    context: Context
) : ArrayAdapter<ApproachInExerciseListItem>(
    context,
    R.layout.item_approach_in_new_exercise,
    approachInExerciseListItem
) {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount(): Int {
        return approachInExerciseListItem.size
    }

    override fun getItem(position: Int): ApproachInExerciseListItem {
        return approachInExerciseListItem[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder", "WrongConstant")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.item_approach_in_new_exercise, parent, false)
        val podhod: TextView = rowView.findViewById(R.id.approachInExercise)
        val povtoreniya = rowView.findViewById<TextView>(R.id.repeatInExercise)
        val kg = rowView.findViewById<TextView>(R.id.worckloadInExercise)

        podhod.text = approachInExerciseListItem[position].approachNumber
        povtoreniya.text = approachInExerciseListItem[position].repeatSum
        kg.text = approachInExerciseListItem[position].load
        return rowView
    }
}