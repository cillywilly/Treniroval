package com.example.treniroval

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class ItemAdapterPastTraining(listArrayPastTraining: ArrayList<ListItemPastTraining>, context: Context) :
    RecyclerView.Adapter<ItemAdapterPastTraining.ViewHolder>() {
    var listItemR = listArrayPastTraining
    var contextR = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val trainingName: TextView = view.findViewById(R.id.trainingName)
        private val trainingDate: TextView = view.findViewById(R.id.trainingDate)

        fun bind(listItemPastTraining: ListItemPastTraining, context: Context) {
            trainingName.text = listItemPastTraining.trainingName
            trainingDate.text = listItemPastTraining.trainingDate
            itemView.setOnClickListener {
                Toast.makeText(context, "Pressed: ${trainingName.text}", Toast.LENGTH_SHORT)
                    .show() //todo переход в тренировку
            }
        }
    }

    class ViewTrainingConstructor(view: View) : RecyclerView.ViewHolder(view) {
        private val exercise: TextView = view.findViewById(R.id.exercise)

        fun bind(listItemExercise: ListItemExercise, context: Context) {
            exercise.text = listItemExercise.exercise
            itemView.setOnClickListener {
                Toast.makeText(context, "Pressed: $listItemExercise", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val inflater = LayoutInflater.from(contextR)
        return ViewHolder(inflater.inflate(R.layout.item_past_training, p0, false))

    }

    override fun getItemCount(): Int {
        return listItemR.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        var listItem = listItemR[p1]
        p0.bind(listItem, contextR)
    }

}