package com.informed.evaluator.presentation.evaluatescreens.evaluatereview.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationInitiateActivity
import kotlinx.android.synthetic.main.activity_evaluate_review.*

class EvaluateReviewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate_review)
        setTopBar()

        if (SharedPreference().getValueBoolien(ConstantKeys.IS_ATTENDEE, false)) {
            complexity_layout.visibility = VISIBLE
            case_no_layout.visibility = VISIBLE
        } else {
            complexity_layout.visibility = GONE
            case_no_layout.visibility = GONE
        }

        btn_confirm.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    EvaluationInitiateActivity::class.java
                ).putExtra("rowItem",intent.getParcelableExtra<RowsItem>("rowItem"))
            )

        }

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