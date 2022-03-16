package com.informed.evaluator.presentation.traineedetails.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R
import com.informed.evaluator.customviews.MyProgressBar
import com.informed.evaluator.presentation.lectureform2.view.LectureForm2Activity

class TargetCustomListAdapter(val context: Context): RecyclerView.Adapter<TargetCustomListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.target_layout_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.myprogressbar.setMax(120)
        holder.myprogressbar.setGoal(100)
        holder.myprogressbar.setProgress(40)

        holder.itemView.setOnClickListener { context.startActivity(Intent(context,
            LectureForm2Activity::class.java)) }


    }

    override fun getItemCount(): Int {
        return 10
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
val myprogressbar=itemView.findViewById(R.id.my_progress_bar) as MyProgressBar

    }
}