package com.informed.evaluator.presentation.evaluatescreens.evaluatesite.view

import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.base.BaseActivity
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatesite.adapter.EvaluateSiteAdapter

class EvaluateSelectSiteActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate_select_site)

        setTopBar()


        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val adapter = EvaluateSiteAdapter(this)
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