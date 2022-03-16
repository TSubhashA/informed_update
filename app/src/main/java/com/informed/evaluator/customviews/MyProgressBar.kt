package com.informed.evaluator.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener

import android.view.animation.DecelerateInterpolator
import android.os.Bundle
import android.os.Parcelable
import android.graphics.Color
import com.informed.evaluator.R

import kotlin.math.roundToInt


class MyProgressBar(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var progressPaint: Paint? = null
    private var goal = 0
    private var max = 100f
    private var progress = 0
    private var indicatorColor =  Color.GREEN
    private var goalIndicatorHeight = 0f
    private var goalIndicatorThickness = 0f
    private var goalReachedColor = 0
    private var goalNotReachedColor = 0
    private var unfilledSectionColor = 0
    private var barThickness = 0
    private var indicatorType: IndicatorType? = null
    private var barAnimator = ValueAnimator.ofFloat(0f, 1f)

    enum class IndicatorType {
        Line, Circle, Square
    }

init {

    attrs?.let { init(it) }
}



    private fun init(attrs: AttributeSet) {
        progressPaint = Paint()
        progressPaint!!.style = Paint.Style.FILL_AND_STROKE
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.MyProgressBar, 0, 0)
        try {
            setGoalIndicatorHeight(
                typedArray.getDimensionPixelSize(
                    R.styleable.MyProgressBar_goalIndicatorHeight,
                    10
                ).toFloat()
            )
            setGoalIndicatorThickness(
                typedArray.getDimensionPixelSize(
                    R.styleable.MyProgressBar_goalIndicatorThickness,
                    5
                ).toFloat()
            )
            setGoalReachedColor(
                typedArray.getColor(
                    R.styleable.MyProgressBar_goalReachedColor,
                    Color.BLUE
                )
            )
            setGoalNotReachedColor(
                typedArray.getColor(
                    R.styleable.MyProgressBar_goalNotReachedColor,
                    Color.BLACK
                )
            )
            setUnfilledSectionColor(
                typedArray.getColor(
                    R.styleable.MyProgressBar_unfilledSectionColor,
                    Color.GRAY
                )
            )
            setBarThickness(
                typedArray.getDimensionPixelOffset(
                    R.styleable.MyProgressBar_barThickness,
                    4
                )

            )
            setMax(
                typedArray.getInteger(
                    R.styleable.MyProgressBar_progressMax,
                    100
                )

            )
            val index = typedArray.getInt(R.styleable.MyProgressBar_indicatorType, 0)
            setIndicatorType(IndicatorType.values()[index])
        } finally {
            typedArray.recycle()
        }
    }

     override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()

        // save our added state - progress and goal
        bundle.putInt("progress", progress)
        bundle.putInt("goal", goal)
        bundle.putFloat("max", max)

        // save super state
        bundle.putParcelable("superState", super.onSaveInstanceState())
        return bundle
    }

     override fun onRestoreInstanceState(state: Parcelable?) {
        var state = state
        if (state is Bundle) {
            val bundle = state

            // restore our added state - progress and goal
            setProgress(bundle.getInt("progress"))
            setGoal(bundle.getInt("goal"))
            setMax(bundle.getFloat("max").roundToInt())

            // restore super state
            state = bundle.getParcelable("superState")
        }
        super.onRestoreInstanceState(state)
    }

     fun setMax(max: Int) {
         this.max= max.toFloat()
         postInvalidate()

    }

    override fun onDraw(canvas: Canvas) {
        val halfHeight = height / 2
        val progressEndX = (width * progress / max).toInt()

        // draw the filled portion of the bar
        progressPaint!!.strokeWidth = barThickness.toFloat()
        val color = goalReachedColor
        progressPaint!!.color = color
        canvas.drawLine(
            0f, halfHeight.toFloat(), progressEndX.toFloat(), halfHeight.toFloat(),
            progressPaint!!
        )

        // draw the unfilled portion of the bar
        progressPaint!!.color = unfilledSectionColor
        canvas.drawLine(
            progressEndX.toFloat(), halfHeight.toFloat(), width.toFloat(), halfHeight.toFloat(),
            progressPaint!!
        )

        // draw goal indicator
        val indicatorPosition = (width * goal / max).toInt()
        progressPaint!!.color = indicatorColor
        progressPaint!!.strokeWidth = goalIndicatorThickness
        when (indicatorType) {
            IndicatorType.Line -> canvas.drawLine(
                indicatorPosition.toFloat(),
                halfHeight - goalIndicatorHeight / 2,
                indicatorPosition.toFloat(),
                halfHeight + goalIndicatorHeight / 2,
                progressPaint!!
            )
            IndicatorType.Square -> canvas.drawRect(
                indicatorPosition - goalIndicatorHeight / 2, 0f,
                indicatorPosition + goalIndicatorHeight / 2,
                goalIndicatorHeight,
                progressPaint!!
            )
            IndicatorType.Circle -> canvas.drawCircle(
                indicatorPosition.toFloat(), halfHeight.toFloat(), halfHeight.toFloat(),
                progressPaint!!
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val specHeight = MeasureSpec.getSize(heightMeasureSpec)
        val height: Int
        height = when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.EXACTLY -> specHeight
            MeasureSpec.AT_MOST -> Math.min(goalIndicatorHeight, specHeight.toFloat()).toInt()
            MeasureSpec.UNSPECIFIED -> specHeight
            else -> specHeight
        }

        // must call this, otherwise the app will crash
        setMeasuredDimension(width, height)
    }

    fun setProgress(progress: Int) {
        setProgress(progress, true)
    }

     fun setProgress(progress: Int, animate: Boolean) {
        if (animate) {

            barAnimator.setDuration(1000)
            // reset progress without animating
            setProgress(0, false)
            barAnimator.setInterpolator(DecelerateInterpolator())
            barAnimator.addUpdateListener(AnimatorUpdateListener { animation ->
                val interpolation = animation.animatedValue as Float
                setProgress((interpolation * progress).toInt(), false)
            })
            if (!barAnimator.isStarted()) {
                barAnimator.start()
            }
        } else {
            this.progress = progress
            postInvalidate()
        }
    }

    fun setGoal(goal: Int) {
        this.goal = goal
        postInvalidate()
    }

    fun setGoalIndicatorHeight(goalIndicatorHeight: Float) {
        this.goalIndicatorHeight = goalIndicatorHeight
        postInvalidate()
    }

    fun setGoalIndicatorThickness(goalIndicatorThickness: Float) {
        this.goalIndicatorThickness = goalIndicatorThickness
        postInvalidate()
    }

    fun setGoalReachedColor(goalReachedColor: Int) {
        this.goalReachedColor = goalReachedColor
        postInvalidate()
    }

    fun setGoalNotReachedColor(goalNotReachedColor: Int) {
        this.goalNotReachedColor = goalNotReachedColor
        postInvalidate()
    }

    fun setUnfilledSectionColor(unfilledSectionColor: Int) {
        this.unfilledSectionColor = unfilledSectionColor
        postInvalidate()
    }

    fun setBarThickness(barThickness: Int) {
        this.barThickness = barThickness
        postInvalidate()
    }

    fun setIndicatorType(indicatorType: IndicatorType?) {
        this.indicatorType = indicatorType
        postInvalidate()
    }

}