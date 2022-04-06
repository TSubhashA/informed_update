package com.informed.evaluator.presentation.evaluatescreens.evaluatedate.view

import android.content.Intent

import android.os.Bundle
import android.widget.TextView
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.common.Constants
import com.informed.evaluator.presentation.evaluatescreens.evaluatereview.view.EvaluateReviewActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest
import kotlinx.android.synthetic.main.activity_evaluate_date.*

class EvaluateDateActivity : BaseActivity() {

    val data by lazy { intent.getParcelableExtra<RowsItem>(Constants.ContextSendActivity.RowItems) }

    val pos by lazy {
        intent.getIntExtra(Constants.ContextInfo.info, -1)
    }

    val conTextInfo by lazy {
        intent.getParcelableExtra<BeginSubmitEvaluateRequest>(Constants.ContextInfo.context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate_date)

        val datePicker=findViewById<SingleDateAndTimePicker>(R.id.single_date_wheeler)

        setTopBar()

        btn_next.setOnClickListener {
            val intent=Intent(this,EvaluateReviewActivity::class.java)

            val date = datePicker.date

            conTextInfo?.contextualInfo= mapOf(Constants.ContextInfo.date to date.toString())

            intent.putExtra(Constants.ContextInfo.context,conTextInfo)

            intent.putExtra(Constants.ContextSendActivity.RowItems, data)

            startActivity(intent)

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