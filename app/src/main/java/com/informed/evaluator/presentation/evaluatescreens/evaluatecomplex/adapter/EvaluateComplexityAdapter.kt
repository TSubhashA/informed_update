package com.informed.evaluator.presentation.evaluatescreens.evaluatestart.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatecomplex.view.EvaluateComplexityActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatesite.view.EvaluateSelectSiteActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem

class EvaluateComplexityAdapter(val context: Context,val data: RowsItem?): RecyclerView.Adapter<EvaluateComplexityAdapter.ViewHolder>() {

    val compl= arrayListOf("Easy","Medium","Difficult","Extreamly Difficult")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.evaluation_list_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
holder.text.setText(compl.get(position))
        holder.itemView.setOnClickListener {

            val intent=Intent(context,EvaluateSelectSiteActivity::class.java)
            intent.putExtra("rowItems",data)

            context.startActivity(intent)
//          (context as EvaluateComplexityActivity).finish()
        }
    }

    override fun getItemCount(): Int {
        return compl.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text  = itemView.findViewById(R.id.name) as TextView

    }
}