package com.informed.evaluator.presentation.recentevaluation.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import com.informed.evaluator.R
import com.informed.evaluator.customviews.ScoreView
import com.informed.evaluator.presentation.procedure.view.ProcedureQuestionActivity



class RecentEvaluationAdapter(val context: Context): RecyclerView.Adapter<RecentEvaluationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recent_evaluation_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
holder.itemView.setOnClickListener {context.startActivity(Intent(context,ProcedureQuestionActivity::class.java)) }
    }

    override fun getItemCount(): Int {
        return 10
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var score: ScoreView = itemView.findViewById(R.id.score_view)

    }
}