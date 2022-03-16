package com.informed.evaluator.presentation.landingscreen.traineeview.fragment.myfeedback.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R
import com.informed.evaluator.presentation.lectureform.view.LectureFormActivity


class LectureFormListAdapter (val context: Context): RecyclerView.Adapter<LectureFormListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.feedback_summary_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(
               context, LectureFormActivity::class.java
            ))
        }

    }

    override fun getItemCount(): Int {
        return 100
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }
}
