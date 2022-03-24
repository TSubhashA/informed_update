package com.informed.evaluator.presentation.evaluatescreens.evaluatestart.view

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.adapter.EvaluateListAdapter
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.EvaluationResponse
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.viewmodel.EvaluationVMFactory
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.viewmodel.EvaluationViewModel
import com.informed.evaluator.utils.showToast
import com.informed.trainee.data.model.ResultOf


class EvaluateStartActivity : BaseActivity() {

    private val evalVM by viewModels<EvaluationViewModel> { EvaluationVMFactory() }

    private lateinit var adapter:EvaluateListAdapter

    var fa: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate)
        setTopBar()
        fa=this

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
         adapter = EvaluateListAdapter(this)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        setData()

        callapi()

    }

    private fun callapi() {
        evalVM.getEvaluation()
    }

    private fun setData() {

        evalVM.dataSource?.observe(this) {
            when(it){
                is ResultOf.Empty -> {
                    showToast("Empty")
                }
                is ResultOf.Failed -> {
                showToast("Failed")
                    Log.e(TAG, "setData: ${it.value}" )
                }
                is ResultOf.Failure -> {
                    showToast("Failure")
                    Log.e(TAG, "setData: failure : ${it.message} + ${it.throwable}" )
                }
                is ResultOf.Progress -> {showToast("Progress")}
                is ResultOf.Success -> {
                    it.value as EvaluationResponse
                    if (it.value.data?.count!! > 0)
                    adapter.setData(it.value.data.rows!! as MutableList<RowsItem?>)


                }
            }

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