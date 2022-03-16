package com.informed.evaluator.presentation.traineedetails.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.informed.evaluator.base.BaseActivity
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.view.EvaluateStartActivity
import com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.model.Row
import com.informed.evaluator.presentation.traineedetails.adapter.ViewPagerAdapter
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_trainee_details.*

class TraineeDetailsActivity : BaseActivity() {

     lateinit var traineeDetails:Row

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainee_details)

        traineeDetails= intent.getSerializableExtra("trainee") as Row

        title_name.text=traineeDetails.name
       sub_mobile.text=traineeDetails.phone
        sub_email.text=traineeDetails.email

        setTopBar()
        setViewPager()

        val searchView=findViewById(R.id.search_button) as SearchView

        searchView.setOnQueryTextFocusChangeListener(object: View.OnFocusChangeListener{
            override fun onFocusChange(p0: View?, b: Boolean) {
                if(!b)
                {
                    searchView.onActionViewExpanded()
                    if(searchView.getQuery().toString().length < 1)
                    {
                        searchView.onActionViewCollapsed();
                        feeeback_text.visibility= View.VISIBLE
                    }
                    searchView.clearFocus();
                    feeeback_text.visibility= View.VISIBLE
                }
                else
                    feeeback_text.visibility= View.GONE
            }
        })

        sub_mobile.setOnClickListener {
            val intent=Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel:${sub_mobile.text.toString()}"))
            startActivity(intent)
        }

        sub_email.setOnClickListener {
            val intent=Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL,sub_email.text.toString())
            intent.setType("message/rfc822")
            startActivity(Intent.createChooser(intent,"Choose an Email Client :"))
        }

        btn_evaluate.setOnClickListener { startActivity(Intent(this,EvaluateStartActivity::class.java)) }
    }

    private fun setViewPager() {
        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.dots_indicator)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
        dotsIndicator.setViewPager(viewPager)
    }

    private fun setTopBar() {
        val toolbar = findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.setTitle("")
        val text = toolbar.findViewById(R.id.action_bar_title) as TextView
        text.text = ""
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_black)

    }
}