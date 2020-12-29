package com.example.treniroval.itemAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.treniroval.ListItem.ListItemApproachInExercise
import com.example.treniroval.R

class ItemAdapterApproachInExercise(
    listItemApproachInExercise: ArrayList<ListItemApproachInExercise>,
    context: Context
) : RecyclerView.Adapter<ItemAdapterApproachInExercise.ViewHolder>() {
    var listItemR = listItemApproachInExercise
    var contextR = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val approachNumber = view.findViewById<TextView>(R.id.approachInExercise)
        private val repeatSum = view.findViewById<TextView>(R.id.repeatInExercise)
        private val load = view.findViewById<TextView>(R.id.worckloadInExercise)

        fun bind(listItemApproachInExercise: ListItemApproachInExercise, context: Context) {
            approachNumber.text = listItemApproachInExercise.approachNumber.toString()
            repeatSum.text =listItemApproachInExercise.repeatSum.toString()
            load.text =listItemApproachInExercise.load.toString()
            itemView.setOnClickListener {
                Toast.makeText(context, "Pressed: ${approachNumber.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextR)
        return ItemAdapterApproachInExercise.ViewHolder(inflater.inflate(R.layout.item_aproach_in_exercise,p0,false))
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val listItem = listItemR[p1]
        p0.bind(listItem,contextR)
    }

    override fun getItemCount(): Int {
        return listItemR.size
    }
}