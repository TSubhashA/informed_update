package com.informed.evaluator.presentation.evaluatescreens.evaluation.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluatereview.viewmodel.EvaluateConfirmViewModel
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.viewmodel.EvaluationVMFactory
import com.informed.evaluator.presentation.evaluatescreens.evaluation.adapter.EvaluationPagerAdapter
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.viewmodel.EvaluateQuestionsVMFactory
import com.informed.evaluator.presentation.evaluatescreens.evaluation.viewmodel.EvaluateQuestionsViewModel
import com.informed.evaluator.presentation.login.view.SignInActivity
import com.informed.evaluator.utils.CustomDialogue
import com.informed.evaluator.utils.CustomProgressDialogue
import com.informed.trainee.data.model.ResultOf
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator


class EvaluationActivity : BaseActivity() {

    lateinit var pager: ViewPager2

    val cpd by lazy {
        CustomProgressDialogue(this)
    }

    val data by lazy {
        intent.getParcelableExtra<RowsItem>("rowItems")
    }

    private val vm by viewModels<EvaluateQuestionsViewModel> { EvaluateQuestionsVMFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluation)

        setTopBar()

        observeSubmition()

        pager = findViewById<ViewPager2>(R.id.viewPager)
        Log.e(TAG, "onCreate: $data" )
        val adapter = EvaluationPagerAdapter(this, data)
        pager.adapter = adapter

        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.dots_indicator)
        dotsIndicator.setViewPager2(pager)

        pager.isUserInputEnabled = false
    }

    private fun observeSubmition() {
        vm.submitEvaluationResponse?.observe(this){
            when(it){
                is ResultOf.Empty -> Log.e(TAG, "observeSubmition: $it" )
                is ResultOf.Failed -> {
                    Log.e(TAG, "observeSubmition: $it" )
                    moveToEvaluation()
                }
                is ResultOf.Failure ->{
                    Log.e(TAG, "observeSubmition: $it" )
                    moveToEvaluation()
                }
                is ResultOf.Progress -> {

                    if (it.loading)
                        cpd.show()
                    else
                        cpd.dismiss()
                }
                is ResultOf.Success -> {
                    Log.e(TAG, "observeSubmition: $it" )
                    moveToEvaluation()
                }
            }
        }
    }

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


    }

    fun changeScreen(
        id: Int? = data?.questionnaire?.evaluationId,
        saveRequest: SaveEvaluateRequest? = null,
        submitRequest: BeginSubmitEvaluateRequest? = null
    ) {
        Log.e(
            TAG,
            "changeScreen: CITem - ${pager.currentItem} , totalItem - ${pager.adapter?.itemCount}"
        )

        if (pager.currentItem == pager.adapter?.itemCount!! - 1) {

//            saveQuestions(id, saveRequest)
    if (submitRequest!=null)
            submitQuestions(id, submitRequest)


        } else {
            if (saveRequest!=null)
            saveQuestions(id, saveRequest)

            if (pager.currentItem < pager.adapter?.itemCount!!)
                pager.currentItem = pager.currentItem + 1

        }


    }

    fun moveToEvaluation(){
        val intent = Intent(this, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        finish()

        startActivity(intent)
    }

    private fun submitQuestions(id: Int?, submitRequest: BeginSubmitEvaluateRequest?) {

        vm.submitEvaluate(id!!, submitRequest!!)
    }

    private fun saveQuestions(id: Int?, saveRequest: SaveEvaluateRequest?) {

        vm.saveEvaluate(id!!, saveRequest!!)


    }


    fun backScreen() {
        if (pager.currentItem != 0)
            pager.currentItem = pager.currentItem - 1
        else
            finish()
    }

}