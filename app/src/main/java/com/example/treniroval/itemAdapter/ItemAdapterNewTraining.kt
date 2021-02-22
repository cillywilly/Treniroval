package com.example.treniroval.itemAdapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.treniroval.DB.ManagerDB
import com.example.treniroval.ListItem.ExerciseInTable
import com.example.treniroval.R
import kotlinx.android.synthetic.main.item_exercise_in_new_training.view.*

class ItemAdapterNewTraining(
    var listExercisesInTable: ArrayList<ExerciseInTable>,
    var context: Context, private val managerDB: ManagerDB
) : RecyclerView.Adapter<ItemAdapterNewTraining.ViewHolder>() {
    lateinit var newListItem: ArrayList<ExerciseInTable>

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private val linearLayout = view.findViewById<LinearLayout>(R.id.linearLayoutNewTraining)
        private val exerciseName = view.findViewById<TextView>(R.id.exerciseNameInNewTraining)
        private val approach = view.findViewById<ListView>(R.id.approachesInNewTraining)
        private val addApproach = view.findViewById<ImageButton>(R.id.addApproachButton)

        fun bind(listItemExerciseInTable: ExerciseInTable, context: Context) {

            exerciseName.text = listItemExerciseInTable.exerciseName
            val adapter = ItemAdapterApproachInNewTraining(
                listItemExerciseInTable.listApproachesInExercise,
                context
            )
            approach.adapter = adapter

            val i = listItemExerciseInTable.listApproachesInExercise.size
            linearLayout.layoutParams =
                ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 100 * i)

            itemView.setOnClickListener {
                Toast.makeText(context, "Pressed: ${exerciseName.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(
            inflater.inflate(
                R.layout.item_exercise_in_new_training,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listExercisesInTable.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val listItem = listExercisesInTable[p1]
        p0.view.addApproachButton.setOnClickListener {
            managerDB.closeDb()
            managerDB.openDb()
            val positoin= p0.adapterPosition+1
            val trainingId = managerDB.getNewTrainingID()
            managerDB.addApproach(positoin, managerDB.getApproachNum(trainingId,positoin))
//            managerDB.addApproach(trainingId, positoin, positoin)
            managerDB.closeDb()
            managerDB.openDb()
            updateAdapter(
                managerDB.getCurrentTraining(managerDB.getNewTrainingID()),
                p0.adapterPosition
            )
        }
        p0.bind(listItem, context)
    }

    private fun getApproachNum(positoin: Int): Int {
        val size = listExercisesInTable[positoin].listApproachesInExercise.size
        return size + 1
    }

    private fun updateAdapter(list: ArrayList<ExerciseInTable>, adapterPosition: Int) {
        listExercisesInTable.clear()
        listExercisesInTable.addAll(list)
//        notifyItemInserted(adapterPosition)
//        notifyItemChanged(adapterPosition)
        notifyDataSetChanged()
    }
}