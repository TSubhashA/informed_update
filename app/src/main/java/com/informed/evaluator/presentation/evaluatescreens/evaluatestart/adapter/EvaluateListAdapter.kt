package com.informed.evaluator.presentation.evaluatescreens.evaluatestart.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference
import com.informed.evaluator.presentation.evaluatescreens.evaluatecomplex.view.EvaluateComplexityActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatesite.view.EvaluateSelectSiteActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.view.EvaluateStartActivity

class EvaluateListAdapter(val context:Context): RecyclerView.Adapter<EvaluateListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.evaluation_list_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

holder.itemView.setOnClickListener {

    if  (SharedPreference().getValueBoolien(ConstantKeys.IS_ATTENDEE,false))
    context.startActivity(Intent(context,EvaluateComplexityActivity::class.java))
else
        context.startActivity(Intent(context,EvaluateSelectSiteActivity::class.java))

    (context as EvaluateStartActivity).finish()
}
    }

    override fun getItemCount(): Int {
        return 10
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }
}