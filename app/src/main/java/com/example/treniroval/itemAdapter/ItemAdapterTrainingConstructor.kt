        package com.example.treniroval.itemAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.example.treniroval.ListItem.Exercise
import com.example.treniroval.R

        class ItemAdapterTrainingConstructor(listArrayExercise: ArrayList<Exercise>, context: Context) :
    RecyclerView.Adapter<ItemAdapterTrainingConstructor.ViewHolder>() {
    var listItemR = listArrayExercise
    var contextR = context
    var selectedItems:ArrayList<String> = ArrayList()

     inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val exercise = view.findViewById<CheckBox>(R.id.exercise)
        private val exercise2 = view.findViewById<CheckBox>(R.id.exercise2)
        init {
            exercise.setOnCheckedChangeListener{
                _,isChecked ->
                if (isChecked) selectedItems.add(exercise.text as String)
                if (!isChecked) selectedItems.remove(exercise.text as String)
            }
            exercise2.setOnCheckedChangeListener{
                _,isChecked ->
                if (isChecked) selectedItems.add(exercise2.text as String)
                if (!isChecked) selectedItems.remove(exercise2.text as String)
            }

        }

        fun bind(listItemExercise: Exercise, context: Context) {

            exercise.text = listItemExercise.exercise
            exercise2.text = listItemExercise.exercise2
//            val intent = Intent(context, CurrentPastTainingActivity::class.java)
//
//            ContextCompat.startActivity(context, intent, null)

            println(exercise.text)
            println(exercise2.text)

//            itemView.setOnClickListener {
//                Toast.makeText(context, "Pressed: ${exercise.text}", Toast.LENGTH_SHORT).show()
//            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val inflater = LayoutInflater.from(contextR)
        return ViewHolder(inflater.inflate(R.layout.item_training_exercises, p0, false))

    }

    override fun getItemCount(): Int {
        return listItemR.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val listExercise = listItemR[p1]
        p0.bind(listExercise, contextR)
    }

}