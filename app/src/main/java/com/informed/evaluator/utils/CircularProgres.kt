package com.informed.evaluator.utils

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

object CircularProgres {

    fun imageProgress(context: Context):CircularProgressDrawable{
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return circularProgressDrawable
    }

}