package com.informed.evaluator.presentation.evaluatescreens.evaluatecase.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.common.Constants
import com.informed.evaluator.presentation.evaluatescreens.evaluatedate.view.EvaluateDateActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatesite.view.EvaluateSelectSiteActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.adapter.ContextInfoType
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest
import kotlinx.android.synthetic.main.activity_evaluate_case.*

class EvaluateCaseActivity : BaseActivity() {

    val data by lazy { intent.getParcelableExtra<RowsItem>(Constants.ContextSendActivity.RowItems) }

    val pos by lazy {
        intent.getIntExtra(Constants.ContextInfo.info, -1)
    }

    val conTextInfo by lazy {
        intent.getParcelableExtra<BeginSubmitEvaluateRequest>(Constants.ContextInfo.context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate_case)

        val title = findViewById<TextView>(R.id.title)

        title.text = data?.contextualInfo!![pos]?.name.toString()

        val edit_text = findViewById<TextInputLayout>(R.id.edit_text)
        val hint = "Enter the ${data?.contextualInfo!![pos]?.name}"
        edit_text.editText?.setHint(hint)

        setTopBar()

        btn_next.setOnClickListener {

            val intent = Intent(
                this,

                if (data?.contextualInfo?.size!! > pos + 1) {
                    when (data?.contextualInfo!![pos + 1]?.type) {
                        ContextInfoType.DROPDOWN.type -> EvaluateSelectSiteActivity::class.java
                        ContextInfoType.SHORT_TEXT.type -> EvaluateCaseActivity::class.java
                        ContextInfoType.LONG_TEXT.type -> EvaluateCaseActivity::class.java

                        else -> EvaluateDateActivity::class.java
                    }

                } else
                    EvaluateDateActivity::class.java

            )
            intent.putExtra(Constants.ContextSendActivity.RowItems, data)
            val text = edit_text.editText?.text.toString()

            if (text.isNotEmpty()) {
                val addMap =
                    mapOf<String, String>(data?.contextualInfo!![pos]?.name.toString() to text)

                conTextInfo?.contextualInfo?.putAll(addMap)
            }
            intent.putExtra(Constants.ContextInfo.context, conTextInfo)

            Log.e(TAG, "onCreate: $conTextInfo")
            if (data?.contextualInfo!!.size > pos + 1) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                intent.putExtra(Constants.ContextInfo.info, pos + 1)
            }

            startActivity(intent)

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
}