package com.informed.trainee.presentation.chat.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R

import com.informed.trainee.presentation.chat.domain.model.MyChat

class ChatReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var content: TextView? = null
    private var timestamp: TextView? = null
    private var senderName: TextView? = null

    init {
        content = itemView.findViewById(R.id.content_message)
        timestamp = itemView.findViewById(R.id.time_stamp)
        senderName = itemView.findViewById(R.id.sender_name)
    }

    fun bind(item: MyChat) {
        content?.text = item.content
        timestamp?.text = item.timestamp.toString()
        senderName?.text=item.from.toString()
    }

}