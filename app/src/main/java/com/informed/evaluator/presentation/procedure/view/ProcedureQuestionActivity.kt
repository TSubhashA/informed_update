package com.informed.evaluator.presentation.procedure.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference
import kotlinx.android.synthetic.main.activity_procedure_question.*

class ProcedureQuestionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_procedure_question)
        setTopBar()
        val trineeLayout=findViewById<LinearLayout>(R.id.trainee_ll)

        if (SharedPreference().getValueBoolien(ConstantKeys.IS_ATTENDEE,false))
            trineeLayout.visibility=GONE
        else
            trineeLayout.visibility= VISIBLE
        back_btn.setOnClickListener { finish() }

    }

    private fun setTopBar() {
        val toolbar=findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.setTitle("")

        setSupportActionBar(toolbar)
    }
}