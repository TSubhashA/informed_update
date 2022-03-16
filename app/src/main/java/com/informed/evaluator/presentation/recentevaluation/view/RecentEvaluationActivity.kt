package com.informed.evaluator.presentation.recentevaluation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.base.BaseActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.informed.evaluator.R
import com.informed.evaluator.presentation.recentevaluation.adapter.RecentEvaluationAdapter
import com.informed.evaluator.utils.toDateString
import kotlinx.android.synthetic.main.activity_recent_evaluation.*

class RecentEvaluationActivity : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_evaluation)
        setTopBar()

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val adapter = RecentEvaluationAdapter(this)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        date_btn.setOnClickListener {
            val dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker()
                    .setTitleText("Select dates")
                    .build()
            dateRangePicker.addOnPositiveButtonClickListener {
                // Respond to positive button click.
                date_btn.setText( "${it.first.toDateString()} - ${it.second.toDateString()}")
            }
            dateRangePicker.show(supportFragmentManager, "tag");
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setTopBar() {
        val toolbar = findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.setTitle("")
        val text = toolbar.findViewById(R.id.action_bar_title) as TextView
        text.text = "20 Recent Evaluation"
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

}