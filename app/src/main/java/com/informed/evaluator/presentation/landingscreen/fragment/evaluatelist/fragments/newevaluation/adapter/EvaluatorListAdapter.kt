package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.adapter

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
import com.bumptech.glide.Glide
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.view.EvaluateStartActivity
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.model.Row
import de.hdodenhof.circleimageview.CircleImageView


class EvaluatorListAdapter(val context: Context) : RecyclerView.Adapter<EvaluatorListAdapter.ViewHolder>(), Filterable {

     var listData = mutableListOf<Row>()
     var filtered_list=mutableListOf<Row>()


    fun setData(list: List<Row>) {
        listData = list as MutableList<Row>
        addData(listData)

    }
    @SuppressLint("NotifyDataSetChanged")
    private fun addData(list: List<Row>){
        filtered_list.addAll(list as MutableList<Row>)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV = itemView.findViewById(R.id.trainee_name) as TextView
        val circleImageV = itemView.findViewById(R.id.trainee_list_image) as CircleImageView
        val yearTV = itemView.findViewById(R.id.trainee_year) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.trainee_list_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = filtered_list.get(position).name
        holder.nameTV.setText(name)
        holder.yearTV.text=filtered_list.get(position).roles[0]?.year.toString()
        Log.e(TAG, "onBindViewHolder: $name" )

        if (filtered_list.get(position).imageUrl != null)
        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(filtered_list.get(position).imageUrl)
            .placeholder(R.drawable.profile_image)
            .error(holder.circleImageV)
            .into(holder.circleImageV)

        holder.itemView.setOnClickListener { context.startActivity(Intent(context,
            EvaluateStartActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) }
    }

    override fun getItemCount(): Int {
        if (!filtered_list.isEmpty()) {
            return filtered_list.size
        }
        return 0
    }

    override fun getFilter(): Filter {
        return searchFilter
    }

    private val searchFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList = mutableListOf<Row?>()
            if (constraint.length == 0) {
                filteredList.addAll(listData)
            } else {
                val filterPattern = constraint.toString().trim { it <= ' ' }
                for (item in listData) {
                    if (item.name?.contains(filterPattern,true) == true) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            results.count=filteredList.size
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            if  (results.count>0){
                filtered_list.clear()
                addData(results.values as List<Row>)
            }
            else {
                Log.println(Log.INFO, "Results", "${results.count} ");
                           }
        }
    }


}