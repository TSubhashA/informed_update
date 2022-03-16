package com.informed.evaluator.presentation.lectureform.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.majorik.sparklinelibrary.SparkLineLayout
import com.informed.evaluator.R

class QuestionsListAdapter(context: Context) :
    RecyclerView.Adapter<QuestionsListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.sparkLine.setData(
            arrayListOf(
                298, 46, 87, 178, 446, 1167, 1855, 1543, 662, 1583
            )
        )

    }

    override fun getItemCount(): Int {
        return 100
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val sparkLine = itemView.findViewById(R.id.chart_list) as SparkLineLayout


    }
}