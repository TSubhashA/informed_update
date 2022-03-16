package com.informed.evaluator.presentation.landingscreen.fragment.message.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.informed.evaluator.R
import com.informed.evaluator.presentation.chat.view.ChatActivity
import de.hdodenhof.circleimageview.CircleImageView


class MessageUserListAdapter(val context: Context): RecyclerView.Adapter<MessageUserListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { context.startActivity(Intent(context,ChatActivity::class.java)) }

        holder.image.getViewTreeObserver().addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val badgeDrawable= BadgeDrawable.create(context)
                badgeDrawable.setBackgroundColor(Color.GREEN)
                badgeDrawable.badgeGravity= BadgeDrawable.TOP_START
//                badgeDrawable.setVerticalOffset(64)
//                badgeDrawable.setHorizontalOffset(-34)
                BadgeUtils.attachBadgeDrawable(badgeDrawable, holder.image);
            }
        })

    }

    override fun getItemCount(): Int {
        return 100
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image =itemView.findViewById(R.id.profile_image) as CircleImageView

    }


}