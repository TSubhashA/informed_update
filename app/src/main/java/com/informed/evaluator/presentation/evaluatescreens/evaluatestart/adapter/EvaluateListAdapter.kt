package com.informed.evaluator.presentation.evaluatescreens.evaluatestart.adapter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R
import com.informed.evaluator.common.Constants
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference
import com.informed.evaluator.presentation.evaluatescreens.evaluatecase.view.EvaluateCaseActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatecomplex.view.EvaluateComplexityActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatedate.view.EvaluateDateActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatesite.view.EvaluateSelectSiteActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.view.EvaluateStartActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluation.adapter.ContextInfoType
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest

class EvaluateListAdapter(val context: Context) :
    RecyclerView.Adapter<EvaluateListAdapter.ViewHolder>(), Filterable {

    var data: MutableList<RowsItem?>? = mutableListOf()
    var update: MutableList<RowsItem?>? = mutableListOf()

    @JvmName("setData1")
    fun setData(rows: MutableList<RowsItem?>) {
        data = rows
        addData(data!!)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addData(list: MutableList<RowsItem?>) {
        update = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.evaluation_list_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            val intent = Intent(
                context, if(update!![position]?.contextualInfo?.size!! > 0)
                {
                    when(update!![position]?.contextualInfo?.get(0)?.type){
                        ContextInfoType.DROPDOWN.type -> EvaluateComplexityActivity::class.java
                        ContextInfoType.SHORT_TEXT.type->EvaluateCaseActivity::class.java
                        ContextInfoType.LONG_TEXT.type->EvaluateCaseActivity::class.java
                        else ->
                            EvaluateDateActivity::class.java
                    }
                }
            else
                EvaluateDateActivity::class.java
            )

            Log.e(TAG, "onBindViewHolder: $intent  " )
            Log.e(TAG, "onBindViewHolder: ${ContextInfoType.DROPDOWN.name}  " )

            intent.putExtra(Constants.ContextSendActivity.RowItems, update?.get(position))

            val conTextInfo = BeginSubmitEvaluateRequest()

            conTextInfo.evaluateeRoleId=SharedPreference().getValueString(ConstantKeys.ROLE_ID)?.toInt()

            conTextInfo.contextualInfo= mutableMapOf()

            intent.putExtra(Constants.ContextInfo.context, conTextInfo)

            if(update!![position]?.contextualInfo?.size!! > 0)
            {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                intent.putExtra(Constants.ContextInfo.info, 0)
            }

            context.startActivity(intent)
//            (context as EvaluateStartActivity).finish()
        }

        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return update?.size ?: 0
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.name)

        fun bind(position: Int) {
            name.setText(update?.get(position)?.name)
        }

    }

    override fun getFilter(): Filter {
        return searched_Filter
    }

    private val searched_Filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList = mutableListOf<RowsItem?>()
            if (constraint.length == 0) {
                filteredList.addAll(data!!)
            } else {
                val filterPattern = constraint.toString().trim { it <= ' ' }
                for (item in data!!) {
                    if (item?.name?.contains(filterPattern, true) == true) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            results.count = filteredList.size
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            if (results.count > 0) {
                addData(results.values as MutableList<RowsItem?>)
            } else {
                Log.println(Log.INFO, "Results", "${results.count} ");
                notifyDataSetChanged()
            }
        }
    }

}

