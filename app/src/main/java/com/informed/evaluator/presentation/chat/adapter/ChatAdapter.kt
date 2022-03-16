package com.informed.evaluator.presentation.chat.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R

import com.informed.trainee.presentation.chat.adapter.viewholder.ChatReceiveViewHolder
import com.informed.trainee.presentation.chat.adapter.viewholder.ChatSendViewHolder
import com.informed.trainee.presentation.chat.domain.model.MyChat

class ChatAdapter(private val itsMe: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     var list = mutableListOf<MyChat>()
    // Different types of rows
    private val TYPE_ITEM_RECEIVE = 0
    private val TYPE_ITEM_SEND = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType)
        {
            TYPE_ITEM_RECEIVE->{
                val view= LayoutInflater.from(parent.context).inflate(R.layout.chat_incoming_layout,parent,false)
                return ChatReceiveViewHolder(view)

            }
            else->{
                val view= LayoutInflater.from(parent.context).inflate(R.layout.chat_outgoing_layout,parent,false)
                return ChatSendViewHolder(view)
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when(holder.getItemViewType())
        {
            TYPE_ITEM_RECEIVE->{
                (holder as ChatReceiveViewHolder).bind(list[position])
            }
            TYPE_ITEM_SEND->{
                (holder as ChatSendViewHolder).bind(list[position])
            }

        }

    }

    override fun getItemViewType(position: Int): Int {
        val obj = list[position]
        return if (obj.from.equals(itsMe)) {
            TYPE_ITEM_SEND
        } else {
            TYPE_ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {

        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onLoad(list:MutableList<MyChat>){
        this.list=list
        notifyDataSetChanged()
    }





}