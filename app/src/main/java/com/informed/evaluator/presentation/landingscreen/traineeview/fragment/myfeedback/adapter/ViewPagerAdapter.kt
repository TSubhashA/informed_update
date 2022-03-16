package com.informed.evaluator.presentation.landingscreen.traineeview.fragment.myfeedback.adapter


import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

import android.view.LayoutInflater

import android.widget.TextView

import androidx.viewpager.widget.ViewPager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R


class ViewPagerAdapter(val context: Context): PagerAdapter() {
    private val text = arrayOf<String>("Target Pending", "Target Acheived")

    override fun getCount(): Int {
        return text.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.target_custom_layout,null)
        val textview = view.findViewById(R.id.text_view) as TextView
        val list=view.findViewById(R.id.list_view) as RecyclerView
        val itemsAdapter= TargetCustomListAdapter(context)
        val layoutManager = LinearLayoutManager(context.applicationContext)
        list.layoutManager = layoutManager
        list.adapter = itemsAdapter

        textview.setText(text .get(position))

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view

    }

    override fun destroyItem(container: View, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}