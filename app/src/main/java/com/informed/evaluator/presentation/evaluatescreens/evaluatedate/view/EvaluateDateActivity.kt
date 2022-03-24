package com.informed.evaluator.presentation.evaluatescreens.evaluatedate.view

import android.content.Intent

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatereview.view.EvaluateReviewActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem
import kotlinx.android.synthetic.main.activity_evaluate_date.*

class EvaluateDateActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate_date)

        setTopBar()

        btn_next.setOnClickListener {
            startActivity(Intent(this,EvaluateReviewActivity::class.java).putExtra("rowItem",intent.getParcelableExtra<RowsItem>("rowItem")))
//        finish()
        }

        single_date_wheeler.setIsAmPm(false)

    }

    private fun setTopBar() {
        val toolbar = findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.setTitle("")
        val text = toolbar.findViewById(R.id.action_bar_title) as TextView
        text.text = "Evaluate Annet"
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_black)
    }
}