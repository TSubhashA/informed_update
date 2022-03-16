package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.saveddraft.adapter.recycleradapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.model.Row
import de.hdodenhof.circleimageview.CircleImageView

class SavedDraftListAdapter(val context: Context) : RecyclerView.Adapter<SavedDraftListAdapter.ViewHolder>() {

    var listData = mutableListOf<Row>()

    fun setData(list: List<Row>) {
        addData(listData)
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun addData(list: List<Row>){
        listData.addAll(list as MutableList<Row>)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        val circleImageV = itemView.findViewById(R.id.user_image) as CircleImageView
        val nameTV = itemView.findViewById(R.id.user_name) as TextView
        val yearTV = itemView.findViewById(R.id.user_year) as TextView
        val evaluation_name = itemView.findViewById(R.id.evaluation_name) as TextView
        val date = itemView.findViewById(R.id.date) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.saved_draft_layout_item, parent, false)

        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
return 2
    }
}