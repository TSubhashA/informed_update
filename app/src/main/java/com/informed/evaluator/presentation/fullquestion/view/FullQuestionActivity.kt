package com.informed.evaluator.presentation.fullquestion.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.presentation.fullquestion.FullQuestionListAdapter
import kotlinx.android.synthetic.main.activity_full_question.*


class FullQuestionActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_question)

        setTopBar()

        hint.setOnClickListener {
            if (hint.text=="Hide Question")
            {
                with(hint) { setText("Show Question") }
                question.visibility= View.GONE
            }else{

                hint.setText("Hide Question")
                question.visibility= View.VISIBLE
            }
        }
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val adapter = FullQuestionListAdapter(this)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }

    private fun setTopBar() {
        val toolbar=findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.setTitle("")
        val text=toolbar.findViewById(R.id.action_bar_title) as TextView
        text.text="Cateract Surgery"
        text.setTextColor(resources.getColor(R.color.black))
        setSupportActionBar(toolbar)

        val actionBar=supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_black)
    }



}