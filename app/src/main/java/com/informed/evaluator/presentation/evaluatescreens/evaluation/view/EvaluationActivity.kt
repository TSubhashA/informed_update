package com.informed.evaluator.presentation.evaluatescreens.evaluation.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluation.adapter.EvaluationPagerAdapter
import com.informed.evaluator.utils.CustomDialogue
import com.informed.evaluator.utils.showToast
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator


class EvaluationActivity : BaseActivity() {

     lateinit var pager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluation)

        setTopBar()

         pager = findViewById(R.id.viewPager) as ViewPager2
        val adapter=EvaluationPagerAdapter(this)
        pager.adapter = adapter

        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.dots_indicator)
        dotsIndicator.setViewPager2(pager)

          pager.setUserInputEnabled(false);

//        btn_back.setOnClickListener(View.OnClickListener {
//            if (pager.currentItem != 0) pager.setCurrentItem(
//                pager.getCurrentItem() - 1
//            )
//        })
//
//        btn_next.setOnClickListener(View.OnClickListener {
//            if (pager.getCurrentItem() < pager.getAdapter()?.itemCount!!)
//                pager.setCurrentItem(pager.getCurrentItem() + 1)
//        })

//        pager.registerOnPageChangeCallback(object : OnPageChangeCallback(){
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//            }
//
//            override fun onPageSelected(position: Int) {
//                if (position== pager.getAdapter()?.itemCount?.minus(1))
//                    reachLast()
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//                super.onPageScrollStateChanged(state)
//            }
//        })
    }
//    fun reachLast(){
//        btn_next.setText("Submit")
//    }

    private fun setTopBar() {

        val toolbar = findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.setTitle("")
        val text = toolbar.findViewById(R.id.title) as TextView

        text.text="Evaluate Annet"
        val btn=toolbar.findViewById(R.id.action_cancel) as ImageButton

        btn.setOnClickListener {
//            showToast("Hello")
            CustomDialogue(this)

        }

//        text.text = "Evaluate Annet"
//        setSupportActionBar(toolbar)
//        toolbar.setNavigationIcon(R.drawable.ic_fi_x_circle)


    }

      fun changeScreen(){
         if (pager.getCurrentItem() < pager.getAdapter()?.itemCount!!)
             pager.setCurrentItem(pager.getCurrentItem() + 1)
     }

     fun backScreen() {
         if (pager.getCurrentItem() !=0)
             pager.setCurrentItem(pager.getCurrentItem() - 1)
     }

 }