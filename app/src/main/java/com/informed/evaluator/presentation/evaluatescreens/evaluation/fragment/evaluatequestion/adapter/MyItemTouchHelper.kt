package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.adapter


import android.app.Activity
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R
import kotlinx.android.synthetic.main.notification_list_item.view.*


class MyItemTouchHelper (val context: Context,val mcqAdapter: EvaluationOptionMCQAdapter):
    ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        when (direction) {
            ItemTouchHelper.RIGHT -> {
                mcqAdapter.onItemLeftSwipe(viewHolder as EvaluationOptionMCQAdapter.ViewHolder)

            }
        }
    }


    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        val viewHolder1 = viewHolder
        val position: Int = viewHolder1.absoluteAdapterPosition
        val item = mcqAdapter.getItemId(position) ?: return
        val itemView: View = viewHolder.itemView
        var showText = ""

        if (viewHolder.absoluteAdapterPosition==mcqAdapter.commentPos)
            showText="Edit Comment"
        else
            showText="Add Commennt"

        val height = itemView.getBottom().toFloat() - itemView.getTop().toFloat()
        val width = height / 2
        val p = Paint()
        val textPaint = Paint()
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            textPaint.setColor(Color.WHITE)
            textPaint.setTextSize(40F)
            textPaint.typeface= Typeface.DEFAULT_BOLD

            if (dX > 0) {
//                p.setColor(Color.parseColor("#E4DDFB"))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    p.setColor(context.resources.getColor(R.color.purple,null))
                }else
                    p.setColor(context.resources.getColor(R.color.purple))

                val background = RectF(
                    itemView.getLeft().toFloat(),
                    itemView.getTop().toFloat(),
                    dX,
                    itemView.getBottom().toFloat()
                )
                c.drawRect(background, p)

                c.drawText(
                    showText,
                    (itemView.getLeft() + width/2 ) as Float,
                    (itemView.bottom - width+10f ) as Float,
                    textPaint
                )
            }
        }

        var newDx = dX
        val windowsWidth=DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(windowsWidth)
//        Log.e(TAG, "onChildDraw: ${windowsWidth.widthPixels}" )
        if (newDx >= windowsWidth.widthPixels * 3 / 4) {
            newDx = 0f
//            textPaint.setColor(context.resources.getColor(R.color.purple_6))
//            c.drawText(
//                showText,
//                (itemView.getLeft() + width/3) as Float,
//                (itemView.top + width) as Float,
//                textPaint
//            )

            p.setColor(context.resources.getColor(R.color.purple_6))
            val background = RectF(
                itemView.getLeft().toFloat(),
                itemView.getTop().toFloat(),
                dX,
                itemView.getBottom().toFloat()
            )
            c.drawRect(background, p)
        }
        super.onChildDraw(c, recyclerView, viewHolder, newDx, dY, actionState, isCurrentlyActive)
    }


    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
//        if (viewHolder.absoluteAdapterPosition==mcqAdapter.commentPos)
//            return 0
//        return super.getSwipeDirs(recyclerView, viewHolder)
        return 0
    }
}


