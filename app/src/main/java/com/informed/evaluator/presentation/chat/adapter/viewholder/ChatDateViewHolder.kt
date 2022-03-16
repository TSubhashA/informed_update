package com.informed.trainee.presentation.chat.adapter.viewholder

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R


class ChatDateViewHolder(context: Context) : FrameLayout(context) {

    private var timestamp: TextView

     init {
        inflate(context, R.layout.chat_date_layout,this)
        timestamp=findViewById(R.id.date_chat)
    }

    fun setDate(item: String) {
        timestamp.text = item
    }

}