package com.informed.evaluator.presentation.evaluatescreens.evaluatesite.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R
import com.informed.evaluator.common.Constants
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference
import com.informed.evaluator.presentation.evaluatescreens.evaluatecase.view.EvaluateCaseActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatedate.view.EvaluateDateActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatesite.view.EvaluateSelectSiteActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.adapter.ContextInfoType
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest

class EvaluateSiteAdapter(
    val context: Context,
    val data: RowsItem?,
    val pos: Int,
    val conTextInfo: BeginSubmitEvaluateRequest?
) :
    RecyclerView.Adapter<EvaluateSiteAdapter.ViewHolder>() {

    val compl = data?.contextualInfo!![pos]?.dropdownOptions

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.evaluation_list_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.setText(compl?.get(position).toString())
        holder.itemView.setOnClickListener {

            val intent = Intent(
                context,

                if (data?.contextualInfo?.size!! > pos + 1) {
                    when (data.contextualInfo[pos + 1]?.type) {
                        ContextInfoType.DROPDOWN.type -> EvaluateSelectSiteActivity::class.java
                        ContextInfoType.SHORT_TEXT.type -> EvaluateCaseActivity::class.java
                        ContextInfoType.LONG_TEXT.type -> EvaluateCaseActivity::class.java

                        else -> EvaluateDateActivity::class.java
                    }

                } else
                    EvaluateDateActivity::class.java

            )
            intent.putExtra(Constants.ContextSendActivity.RowItems, data)

            val addMap= mapOf<String,String>(data.contextualInfo[pos]?.name.toString() to compl!![position].toString())

            conTextInfo?.contextualInfo?.putAll(addMap)

            intent.putExtra(Constants.ContextInfo.context, conTextInfo)
            Log.e(TAG, "onBindViewHolder: $conTextInfo" )
            if(data.contextualInfo.size > pos+1)
            {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                intent.putExtra(Constants.ContextInfo.info, pos+1)
            }

            context.startActivity(intent)
//            (context as EvaluateSelectSiteActivity).finish()

        }
    }

    override fun getItemCount(): Int {
        return compl?.size?:0
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text = itemView.findViewById(R.id.name) as TextView

    }
}