package com.informed.evaluator.presentation.evaluatescreens.evaluatecase.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView

import com.informed.evaluator.base.BaseActivity
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatedate.view.EvaluateDateActivity
import kotlinx.android.synthetic.main.activity_evaluate_case.*

class EvaluateCaseActivity : BaseActivity() {

    var fa: Activity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate_case)
fa=this
        setTopBar()

        btn_next.setOnClickListener {
            startActivity(Intent(this,EvaluateDateActivity::class.java))
//        finish()
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