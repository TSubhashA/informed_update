package com.informed.evaluator.presentation.evaluatescreens.evaluatereview.view

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.common.Constants
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference
import com.informed.evaluator.presentation.evaluatescreens.evaluatereview.viewmodel.EvaluateConfirmVMFactory
import com.informed.evaluator.presentation.evaluatescreens.evaluatereview.viewmodel.EvaluateConfirmViewModel
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationInitiateActivity
import kotlinx.android.synthetic.main.activity_evaluate_review.*
import java.util.*


class EvaluateReviewActivity : BaseActivity() {

    private val vm by viewModels<EvaluateConfirmViewModel> { EvaluateConfirmVMFactory() }
    private val data by lazy {
        intent.getParcelableExtra<RowsItem>(Constants.ContextSendActivity.RowItems)
    }

    val conTextInfo by lazy {
        intent.getParcelableExtra<BeginSubmitEvaluateRequest>(Constants.ContextInfo.context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate_review)

        val insertPoint = findViewById<View>(R.id.linear_layout) as LinearLayout
        val vi =
            applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        insertPoint.removeAllViews()

        conTextInfo?.contextualInfo?.forEach { entry ->
            val v: View = vi.inflate(R.layout.review_view, null)

            val textViewTitle = v.findViewById<View>(R.id.title) as TextView
            val textViewSubTitle = v.findViewById<View>(R.id.sub_title) as TextView
            textViewTitle.text = entry.key.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            textViewSubTitle.text = entry.value.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }

//            if (v.parent != null) {
//                (v.parent as ViewGroup ).removeView(v) // <- fix
//            }
            insertPoint.addView(v, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) )
        }

        setTopBar()


        btn_confirm.setOnClickListener {


            startEvaluation()

            startActivity(
                Intent(
                    this,
                    EvaluationInitiateActivity::class.java
                ).putExtra(Constants.ContextSendActivity.RowItems, data)
            )

        }

    }

    private fun setTopBar() {
        val toolbar = findViewById<MaterialToolbar>(R.id.my_tool_bar)
        toolbar.title = ""
        val text = toolbar.findViewById(R.id.action_bar_title) as TextView
        text.text = "Evaluate Annet"
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_black)
    }

    private fun startEvaluation() {


        var beginEvaluate: BeginSubmitEvaluateRequest? = null

        if (conTextInfo != null)
            beginEvaluate = conTextInfo as BeginSubmitEvaluateRequest
        else {
            beginEvaluate =
                BeginSubmitEvaluateRequest()

            beginEvaluate.evaluateeRoleId =
                SharedPreference().getValueString(ConstantKeys.ROLE_ID)?.toInt()!!

            beginEvaluate.contextualInfo = mutableMapOf("comment" to "first evaluation")
        }


        Log.e(TAG, "startEvaluation: $beginEvaluate")

        vm.beginEvaluate(data?.id!!, beginEvaluate)

    }


}