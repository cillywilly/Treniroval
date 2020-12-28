package com.example.treniroval.itemAdapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.treniroval.ListItem.ListItemPastTraining
import com.example.treniroval.R
import com.example.treniroval.activitys.TrainingActivity

class ItemAdapterExerciseInTable(
    listArrayPastTraining: ArrayList<ListItemPastTraining>,
    context: Context
) : RecyclerView.Adapter<ItemAdapterPastTraining.ViewHolder>() {
    var listItemR = listArrayPastTraining
    var contextR = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val trainingName = view.findViewById<TextView>(R.id.trainingName)
        private val trainingDate = view.findViewById<TextView>(R.id.trainingDate)

        fun bind(listItemPastTraining: ListItemPastTraining, context: Context) {
            trainingName.text = listItemPastTraining.trainingName
            trainingDate.text = listItemPastTraining.trainingDate
            itemView.setOnClickListener {
                val intent = Intent(context, TrainingActivity::class.java)
                ContextCompat.startActivity(context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemAdapterPastTraining.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(p0: ItemAdapterPastTraining.ViewHolder, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

