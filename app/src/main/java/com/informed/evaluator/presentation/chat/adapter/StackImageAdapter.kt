package com.informed.evaluator.presentation.chat.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.informed.evaluator.R
import de.hdodenhof.circleimageview.CircleImageView


class StackImageAdapter(val context: Context, val arrayList: ArrayList<Drawable>) : BaseAdapter() {

    var holder: ViewHolder? = null

    override fun getCount(): Int {
        return arrayList.size;
    }

    override fun getItem(p0: Int): Any {
        return arrayList.get(p0);
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong();
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = p1
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.stackview_image, p2, false)

            holder = ViewHolder()
            holder!!.image = view.findViewById(R.id.user_image) as CircleImageView
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }
        holder!!.image?.setImageDrawable(arrayList.get(p0))

        return view!!

    }

    class ViewHolder {
        var image: CircleImageView? = null
    }


}