package com.informed.evaluator.presentation.evaluatescreens.evaluation.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.adapter.EvaluationPagerAdapter
import com.informed.evaluator.presentation.login.view.SignInActivity
import com.informed.evaluator.utils.CustomDialogue
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator


class EvaluationActivity : BaseActivity() {

    lateinit var pager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluation)

        setTopBar()

        val data = intent.getParcelableExtra<RowsItem>("rowItem")


        pager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = EvaluationPagerAdapter(this, data)
        pager.adapter = adapter

        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.dots_indicator)
        dotsIndicator.setViewPager2(pager)

        pager.isUserInputEnabled = false

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

        val toolbar = findViewById<MaterialToolbar>(R.id.my_tool_bar)
        toolbar.title = ""
        val text = toolbar.findViewById(R.id.title) as TextView

        text.text = "Evaluate Annet"
        val btn = toolbar.findViewById(R.id.action_cancel) as ImageButton

        btn.setOnClickListener {
//            showToast("Hello")
            CustomDialogue(this)

        }

//        text.text = "Evaluate Annet"
//        setSupportActionBar(toolbar)
//        toolbar.setNavigationIcon(R.drawable.ic_fi_x_circle)


    }

    fun changeScreen() {
        Log.e(
            TAG,
            "changeScreen: CITem - ${pager.currentItem} , totalItem - ${pager.adapter?.itemCount}"
        )

        if (pager.currentItem == pager.adapter?.itemCount!! - 1) {
            val intent = Intent(this, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            finish()
//            EvaluationInitiateActivity.fa?.finish()


            startActivity(intent)
        }

        if (pager.currentItem < pager.adapter?.itemCount!!)
            pager.currentItem = pager.currentItem + 1


    }

    fun backScreen() {
        if (pager.currentItem != 0)
            pager.currentItem = pager.currentItem - 1
        else
            finish()
    }

}