package com.example.treniroval.itemAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.treniroval.ListItem.ApproachInExercise
import com.example.treniroval.R


class ItemAdapterApproachInNewTraining(
    private val approachInExercise: ArrayList<ApproachInExercise>,
    context: Context
) : BaseAdapter(

) {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return approachInExercise.size
    }

    override fun getItem(position: Int): ApproachInExercise {
        return approachInExercise[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rowView = inflater.inflate(R.layout.item_approach_in_new_exercise, parent, false)
        val podhod: TextView = rowView.findViewById(R.id.approachInExercise)
        val povtoreniya: TextView = rowView.findViewById(R.id.repeatInExercise)
        val kg: TextView = rowView.findViewById(R.id.worckloadInExercise)
//            val repeats: String = p0.view.repeatInExercise.text.toString()
//            val load: String = p0.view.worckloadInExercise.text.toString()
//            val repeats: String = view.findViewById<EditText>(R.id.repeatInExercise).text.toString()
//            val load: String = view.findViewById<EditText>(R.id.worckloadInExercise).text.toString()
//            managerDB.saveApproach(listExercisesInTable, p0.adapterPosition+1, repeats, load)


        povtoreniya.hint = approachInExercise[position].repeatSum
        kg.hint = approachInExercise[position].load
        podhod.text = approachInExercise[position].approachNumber
        povtoreniya.text = approachInExercise[position].repeatSum
        kg.text = approachInExercise[position].load
        return rowView
    }

}