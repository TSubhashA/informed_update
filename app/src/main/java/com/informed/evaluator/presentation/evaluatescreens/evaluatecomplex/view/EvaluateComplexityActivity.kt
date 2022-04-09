package com.informed.evaluator.presentation.evaluatescreens.evaluatecomplex.view

import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.base.BaseActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import com.informed.evaluator.R
import com.informed.evaluator.common.Constants
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.adapter.EvaluateComplexityAdapter
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest

class EvaluateComplexityActivity : BaseActivity() {

    val data by lazy {intent.getParcelableExtra<RowsItem>(Constants.ContextSendActivity.RowItems)}

    val pos by lazy {

        intent.getIntExtra(Constants.ContextInfo.info,-1)
    }

    val conTextInfo by lazy {
        intent.getParcelableExtra<BeginSubmitEvaluateRequest>(Constants.ContextInfo.context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate_complexity)

        val title = findViewById<TextView>(R.id.title)
        title.setText(data?.contextualInfo!![pos]?.name.toString())
        val search = findViewById<TextInputLayout>(R.id.search)
        val hint="Enter the ${data?.contextualInfo!![pos]?.name}"
search.editText?.setHint(hint)
        setTopBar()

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        val adapter = EvaluateComplexityAdapter(this, data,pos,conTextInfo)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter


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