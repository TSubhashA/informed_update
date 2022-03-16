package com.informed.evaluator.presentation.notification.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.presentation.notification.NotificationCallBack
import com.informed.evaluator.presentation.notification.adapter.NotificationListAdapter
import com.informed.evaluator.presentation.notification.model.NotificationData
import kotlinx.android.synthetic.main.activity_notification.*


class NotificationActivity : BaseActivity(), NotificationCallBack {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        setTopBar()


        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val adapter = NotificationListAdapter(this)
        adapter.setData(setData())
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        delete_button.setOnClickListener {
            adapter.deleteData()
        }

        cancel_delete.setOnClickListener {
            adapter.cancelSelection()
        }


    }


    private fun setTopBar() {
        val toolbar = findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.setTitle("")
        val text = toolbar.findViewById(R.id.action_bar_title) as TextView
        text.text = "Notification"
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_back)




    }

    override fun onSelectedMode(modeOn: Boolean) {
        if (modeOn)
            bottom_btn_layout.visibility = VISIBLE
        else
            bottom_btn_layout.visibility = GONE


    }

    fun setData(): MutableList<NotificationData> {
val list= mutableListOf<NotificationData>()
        for (i in 1..20 )
        {
val noti=NotificationData(i,"Notification Header","You have received a request for evaluation.","3rd Oct")
            list.add(noti)

        }

        return list

    }

}