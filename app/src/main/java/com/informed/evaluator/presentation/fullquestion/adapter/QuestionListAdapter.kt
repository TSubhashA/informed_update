package com.informed.evaluator.presentation.fullquestion

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.informed.evaluator.R

class FullQuestionListAdapter(val context: Context):
    RecyclerView.Adapter<FullQuestionListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.full_quest_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return 10
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

}