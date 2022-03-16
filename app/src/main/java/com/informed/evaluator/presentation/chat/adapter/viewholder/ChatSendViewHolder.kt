package com.informed.trainee.presentation.chat.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R

import com.informed.trainee.presentation.chat.domain.model.MyChat

class ChatSendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var content: TextView = itemView.findViewById(R.id.content_message)
    private var timestamp: TextView = itemView.findViewById(R.id.time_stamp)

    fun bind(item: MyChat) {
        content.text = item.content
        timestamp.text = item.timestamp.toString()
    }


}