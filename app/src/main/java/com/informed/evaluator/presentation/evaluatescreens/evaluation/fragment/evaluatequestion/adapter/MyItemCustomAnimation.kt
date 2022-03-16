package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.adapter

import android.animation.*
import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.log


class MyItemCustomAnimation() : DefaultItemAnimator() {

    @SuppressLint("Recycle")
    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder?,
        newHolder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int
    ): Boolean {
        oldHolder as EvaluationOptionMCQAdapter.ViewHolder

        val expand= ObjectAnimator.ofFloat(oldHolder.llLayout_comment, View.TRANSLATION_Y,1f)

        val collapse=
            ObjectAnimator.ofFloat(oldHolder.optionNumber, View.TRANSLATION_Y,0f)

        val expandX=
            ObjectAnimator.ofFloat(oldHolder.llLayout_comment, View.TRANSLATION_X,1f)

        val collapseX=
            ObjectAnimator.ofFloat(oldHolder.optionNumber, View.TRANSLATION_X,0f)

        val anim=AnimatorSet()

        anim.playTogether(collapseX,expandX,expand,collapse)

        return super.animateChange(oldHolder, newHolder, fromX, fromY, toX, toY)
    }

    override fun getChangeDuration(): Long {
        return 500
    }

    override fun onChangeStarting(item: RecyclerView.ViewHolder?, oldItem: Boolean) {


        super.onChangeStarting(item, oldItem)
    }

    override fun onChangeFinished(item: RecyclerView.ViewHolder?, oldItem: Boolean) {
        super.onChangeFinished(item, oldItem)
    }


}