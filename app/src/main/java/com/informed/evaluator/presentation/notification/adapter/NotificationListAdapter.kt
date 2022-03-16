package com.informed.evaluator.presentation.notification.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.informed.evaluator.R
import com.informed.evaluator.presentation.notification.NotificationCallBack
import com.informed.evaluator.presentation.notification.model.NotificationData
import com.informed.evaluator.utils.showToast


@SuppressLint("NotifyDataSetChanged")
class NotificationListAdapter(val callback:NotificationCallBack): RecyclerView.Adapter<NotificationListAdapter.ViewHolder>() {

private var selectedItem= mutableListOf<NotificationData>()
 private var isSelectedMode=false

private var data= mutableListOf<NotificationData>()


fun setData(data:MutableList<NotificationData>)
{
    this.data=data
    notifyDataSetChanged()

}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.onBind(data.get(position))

    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title=itemView.findViewById(R.id.title) as TextView
        val sub_title=itemView.findViewById(R.id.sub_title) as TextView
        val time_tv=itemView.findViewById(R.id.time_tv) as TextView
        val card_view=itemView.findViewById(R.id.card_view) as MaterialCardView



        fun onBind(itemData:NotificationData){

            title.text=itemData.title
            sub_title.text=itemData.message
            time_tv.text=itemData.date

            checkHighlight(itemData)

            itemView.setOnLongClickListener {
                isSelectedMode=true
                callback.onSelectedMode(isSelectedMode)
                highlighSelected(itemData)
                true
            }

            itemView.setOnClickListener {
                if (isSelectedMode)
                {
                    highlighSelected(itemData)
                }
                else
                {
                    itemView.context.showToast("Clicked ${itemData.id}")
                }
            }

        }

        fun highlighSelected(itemData:NotificationData){
            if (selectedItem.contains(itemData))
            {
                itemView.setBackgroundColor(Color.TRANSPARENT)
                card_view.setCardBackgroundColor(Color.WHITE)
                selectedItem.remove(itemData)
            }
            else
            {
                itemView.setBackgroundColor(itemView.resources.getColor(R.color.purple_4))
                card_view.setCardBackgroundColor(itemView.resources.getColor(R.color.purple_4))
                selectedItem.add(itemData)
            }
            if (selectedItem.size==0)
            {
                isSelectedMode=false
                callback.onSelectedMode(isSelectedMode)
            }
        }

        fun checkHighlight(itemData:NotificationData){
            if (!selectedItem.contains(itemData)) {
                itemView.setBackgroundColor(Color.TRANSPARENT)
                card_view.setCardBackgroundColor(Color.WHITE)
            }
        }

    }


    fun deleteData(){
        if (isSelectedMode) {
            data.removeAll(selectedItem)
            selectedItem.clear()
            isSelectedMode=false
            callback.onSelectedMode(isSelectedMode)
            notifyDataSetChanged()
        }

    }

    fun cancelSelection(){
        if (isSelectedMode) {
            selectedItem.clear()
            isSelectedMode=false
            callback.onSelectedMode(isSelectedMode)
            notifyDataSetChanged()
        }

    }

}