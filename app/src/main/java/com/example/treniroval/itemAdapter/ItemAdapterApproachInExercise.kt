package com.example.treniroval.itemAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.treniroval.ListItem.ListItemApproachInExercise
import com.example.treniroval.R

class ItemAdapterApproachInExercise(
    private val listItemApproachInExercise: ArrayList<ListItemApproachInExercise>,
    context: Context
//):  BaseAdapter() {
): ArrayAdapter<ListItemApproachInExercise>(context, R.layout.item_aproach_in_exercise, listItemApproachInExercise) {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount(): Int {
        return listItemApproachInExercise.size
    }

    override fun getItem(position: Int): ListItemApproachInExercise {
        return listItemApproachInExercise[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.item_aproach_in_exercise, parent, false)
        val podhod : TextView = rowView.findViewById(R.id.approachInExercise)
        val povtoreniya = rowView.findViewById<TextView>(R.id.repeatInExercise)
        val kg = rowView.findViewById<TextView>(R.id.worckloadInExercise)

        podhod.text = listItemApproachInExercise[position].approachNumber
        povtoreniya.text = listItemApproachInExercise[position].repeatSum
        kg.text = listItemApproachInExercise[position].load
        return rowView
    }

}