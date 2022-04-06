package com.informed.evaluator.presentation.evaluatescreens.evaluation.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem
import com.informed.evaluator.utils.CircularProgres
import com.informed.evaluator.utils.CustomDialogue
import kotlinx.android.synthetic.main.activity_evaluation_initiate.*


class EvaluationInitiateActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluation_initiate)

        val data = intent.getParcelableExtra<RowsItem>("rowItems")
        setTopBar(data)

        main_title.setText(data?.questionnaire?.name)

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(CircularProgres.imageProgress(this))
            .error(R.mipmap.ic_launcher_round)

        Glide.with(this)
            .load(data?.questionnaire?.imageUrl)
            .apply(options)
            .into(image_view)

        description.setText(data?.questionnaire?.description)


        btn_back.setOnClickListener(View.OnClickListener {
            finish()
        })

        btn_start.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, EvaluationActivity::class.java).putExtra("rowItems", data))
        })


    }


    private fun setTopBar(data: RowsItem?) {
        val toolbar = findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.setTitle("")
        val text = toolbar.findViewById(R.id.title) as TextView

        text.text = data?.name
        val btn = toolbar.findViewById(R.id.action_cancel) as ImageButton

        btn.setOnClickListener {
            CustomDialogue(this)
        }

    }
}