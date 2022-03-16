package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.saveddraft.adapter.itemsectionview

import android.content.Context
import android.widget.FrameLayout
import android.widget.TextView
import com.informed.evaluator.R


class SectionHeaderViewHolder(context : Context): FrameLayout(context) {

    private var headerTV: TextView

    init {
        inflate(context, R.layout.header_data,this)
        headerTV=findViewById(R.id.header_text)
    }

    fun setHeader(item: String) {
        headerTV.text = item
    }

}


