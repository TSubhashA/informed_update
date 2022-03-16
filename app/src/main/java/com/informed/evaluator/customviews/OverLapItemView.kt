package com.informed.evaluator.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.RelativeCornerSize
import com.google.android.material.shape.ShapeAppearanceModel
import com.informed.evaluator.R


class OverLapItemView(context: Context): FrameLayout(context) {

    private var textView:TextView
    private var imageView:ShapeableImageView

    init {

        inflate(context, R.layout.view_overlap_user_image,this)
        textView = findViewById(R.id.text_view)
        imageView=findViewById(R.id.profile_user_image)

        imageView.post{

            imageView.shapeAppearanceModel= ShapeAppearanceModel().toBuilder()
                .setAllCornerSizes(RelativeCornerSize(0.5f))
                .build()
        }

    }

    private fun reset(){
        textView.visibility= View.GONE
        imageView.visibility= View.GONE
    }

    fun setImageResource(resource: Drawable){
        reset()
        imageView.setImageDrawable(resource)
        imageView.visibility= VISIBLE
    }

    fun setText(text: String){
        reset()
        textView.text=text
        textView.visibility= VISIBLE
    }

    fun overLapatEnd(dpValue:Float){

        (layoutParams as MarginLayoutParams).setMargins(0,0,dipToPx(dpValue),0)
    }

    private fun dipToPx(dipValue: Float):Int{
        return (dipValue*resources.displayMetrics.density).toInt()
    }


}