package com.informed.evaluator.presentation.evaluatescreens.evaluatesite.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference
import com.informed.evaluator.presentation.evaluatescreens.evaluatecase.view.EvaluateCaseActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatedate.view.EvaluateDateActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatesite.view.EvaluateSelectSiteActivity

class EvaluateSiteAdapter(val context: Context) : RecyclerView.Adapter<EvaluateSiteAdapter.ViewHolder>() {

    val compl= arrayListOf("Easy","Medium","Difficult","Extreamly Difficult")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.evaluation_list_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.setText(compl.get(position))
        holder.itemView.setOnClickListener {
            if  (SharedPreference().getValueBoolien(ConstantKeys.IS_ATTENDEE,false))
            context.startActivity(
            Intent(context,
                EvaluateCaseActivity::class.java)
        )
            else
                context.startActivity(
                    Intent(context,
                        EvaluateDateActivity::class.java)
                )



            (context as EvaluateSelectSiteActivity).finish()

        }
    }

    override fun getItemCount(): Int {
        return compl.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text  = itemView.findViewById(R.id.name) as TextView

    }
}