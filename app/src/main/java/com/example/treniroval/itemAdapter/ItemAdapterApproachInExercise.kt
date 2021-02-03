package com.example.treniroval.itemAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.treniroval.ListItem.ListItemApproachInExercise
import com.example.treniroval.R

class ItemAdapterApproachInExercise(
    listItemApproachInExercise: ArrayList<ListItemApproachInExercise>,
    context: Context
): ArrayAdapter<ListItemApproachInExercise>(context, R.layout.item_aproach_in_exercise, listItemApproachInExercise) {

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup): View {
        var view = convertView

        val viewHolder: ListItemApproachInExercise
        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.item_aproach_in_exercise, viewGroup, false)

            viewHolder = ListItemApproachInExercise()

        }

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.attraction_item, viewGroup, false)

            viewHolder = AttractionItemViewHolder()
            viewHolder.title = view!!.findViewById<View>(R.id.title) as TextView
            viewHolder.description = view.findViewById<View>(R.id.description) as TextView
            viewHolder.hours = view.findViewById<View>(R.id.hours) as TextView
            //shows how to apply styles to views of item for specific items
            if (i == 3)
                viewHolder.hours!!.setTextColor(Color.DKGRAY)
            viewHolder.image = view.findViewById<View>(R.id.image) as ImageView
        } else {
            //no need to call findViewById, can use existing ones from saved view holder
            viewHolder = view.tag as AttractionItemViewHolder
        }

        val attraction = getItem(i)
        viewHolder.title!!.text = attraction!!.title
        viewHolder.description!!.text = attraction.description
        viewHolder.hours!!.text = attraction.hours
        viewHolder.image!!.setImageResource(attraction.image)

        //shows how to handle events of views of items
        viewHolder.image!!.setOnClickListener {
            Toast.makeText(context, "Clicked image of " + attraction!!.title,
                Toast.LENGTH_SHORT).show()
        }

        view.tag = viewHolder

        return view

    }
}

//do luchshiX vremen
//    : RecyclerView.Adapter<ItemAdapterApproachInExercise.ViewHolder>() {
//    var listItemR = listItemApproachInExercise
//    var contextR = context
//
//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        private val approachNumber = view.findViewById<TextView>(R.id.approachInExercise)
//        private val repeatSum = view.findViewById<TextView>(R.id.repeatInExercise)
//        private val load = view.findViewById<TextView>(R.id.worckloadInExercise)
//
//        fun bind(listItemApproachInExercise: ListItemApproachInExercise, context: Context) {
//            approachNumber.text = listItemApproachInExercise.approachNumber.toString()
//            repeatSum.text =listItemApproachInExercise.repeatSum.toString()
//            load.text =listItemApproachInExercise.load.toString()
//            itemView.setOnClickListener {
//                Toast.makeText(context, "Pressed: ${approachNumber.text}", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        val inflater = LayoutInflater.from(contextR)
//        return ItemAdapterApproachInExercise.ViewHolder(inflater.inflate(R.layout.item_aproach_in_exercise,p0,false))
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        val listItem = listItemR[p1]
//        p0.bind(listItem,contextR)
//    }
//
//    override fun getItemCount(): Int {
//        return listItemR.size
//    }
}