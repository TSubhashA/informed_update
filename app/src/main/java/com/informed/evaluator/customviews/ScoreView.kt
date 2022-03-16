package com.informed.evaluator.customviews

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color

import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar


import android.widget.RelativeLayout
import android.widget.TextView

import com.informed.evaluator.R



class ScoreView(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    var attribute: TypedArray
var progressbar: ProgressBar
    var text_score: TextView
    var text_max: TextView
    var view_line: View


    init {

      val v=  inflate(context, R.layout.score_view, this)
progressbar=v.findViewById(R.id.progressBar)
        text_score = v.findViewById(R.id.text_score)
        text_max = v.findViewById(R.id.text_max)
        view_line=v.findViewById(R.id.view_score)

        attribute = context!!.obtainStyledAttributes(attrs, R.styleable.ScoreView)
        setMax(attribute.getInteger(R.styleable.ScoreView_maxValue, 100))
        setProgress(attribute.getFloat(R.styleable.ScoreView_scoredValue, 50.0f))
        setTextSize(attribute.getDimension(R.styleable.ScoreView_textSize, 16f))
        setColor(attribute.getColor(R.styleable.ScoreView_color, Color.GREEN))
        progressbar.isIndeterminate = false
    }

    private fun setColor(color: Int) {
        progressbar.progressTintList= ColorStateList.valueOf(color)
        text_max.setTextColor(color)
        text_score.setTextColor(color)
        view_line.setBackgroundColor(color)

    }

    fun setTextSize(size: Float) {
        text_score.textSize = size
        text_max.textSize = size
    }


    fun getscoredValue(): String {
        return text_score.text.toString()
    }

    fun getmaxValue(): String {
        return text_max.text.toString()
    }

    fun setscoredValue(score: Float) {
        text_score.text = score.toString()

    }

    fun setmaxValue(score: String) {
        text_max.text = score

    }

    fun setProgress(score: Float) {
        setscoredValue(score)
        val prog=(score *100).toInt()
        progressbar.progress = prog
    }

    fun getProgress(): String {
        return text_score.text.toString()

    }

    fun setMax(score: Int) {
        setmaxValue(score.toString())
        progressbar.max = score *100
    }

    fun getMax():String{
        return  text_max.text.toString()
    }


}