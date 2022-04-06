package com.informed.evaluator.presentation.evaluatescreens.evaluatereview.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.informed.evaluator.base.BaseViewModel
import com.informed.evaluator.presentation.evaluatescreens.evaluatereview.domain.IEvaluateConfirmRepo
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class EvaluateConfirmViewModel(val evaluation: IEvaluateConfirmRepo):BaseViewModel() {


    fun beginEvaluate(id:Int, request: BeginSubmitEvaluateRequest)=
        viewModelScope.launch(handlerException)
    {
        evaluation.beginEvaluation(id,request)
            .onStart { Log.i(TAG, "beginEvaluate: start") }
            .onCompletion { Log.i(TAG, "beginEvaluate: complete") }
            .catch { Log.e(TAG, "beginEvaluate: ${it.localizedMessage} : ${it.cause} : ${it.message} : $it" ) }
            .collect{
                if (it?.success==true)
                    Log.i(TAG, "beginEvaluate: $it")
            }

    }



}