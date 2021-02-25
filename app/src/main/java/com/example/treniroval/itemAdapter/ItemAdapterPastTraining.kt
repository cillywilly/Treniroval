package com.example.treniroval.itemAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.treniroval.ListItem.PastTraining
import com.example.treniroval.R
import com.example.treniroval.activitys.CurrentPastTainingActivity

class ItemAdapterPastTraining(
    listArrayPastTraining: ArrayList<PastTraining>,
    context: Context
) : RecyclerView.Adapter<ItemAdapterPastTraining.ViewHolder>() {
    var listItemR = listArrayPastTraining
    var contextR = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val trainingName = view.findViewById<TextView>(R.id.trainingName)
        private val trainingDate = view.findViewById<TextView>(R.id.trainingDate)

        fun bind(listItemPastTraining: PastTraining, context: Context) {
            trainingName.text = listItemPastTraining.trainingName
            trainingDate.text = listItemPastTraining.trainingDate
            itemView.setOnClickListener {
//                val intent = Intent(context, TrainingActivity::class.java)
                val intent = Intent(context, CurrentPastTainingActivity::class.java)
                intent.putExtra("trainingName",trainingName.text )
                intent.putExtra("trainingDate",trainingDate.text)
                startActivity(context, intent,null)
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
        val listItem = listItemR[p1]
        p0.bind(listItem, contextR)
    }

}