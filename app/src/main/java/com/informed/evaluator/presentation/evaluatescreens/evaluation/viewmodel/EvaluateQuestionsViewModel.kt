package com.informed.evaluator.presentation.evaluatescreens.evaluation.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.informed.evaluator.base.BaseViewModel
import com.informed.evaluator.presentation.evaluatescreens.evaluation.domain.IEvaluateQuestionRepo
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateRequest
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class EvaluateQuestionsViewModel(private val evaluate:IEvaluateQuestionRepo) : BaseViewModel() {


    fun saveEvaluate(id:Int, request:SaveEvaluateRequest)=

        viewModelScope.launch(handlerException){

            evaluate.saveEvaluate(id,request)
                .onStart { Log.i(TAG, "saveEvaluate: starting")}
                .onCompletion { Log.i(TAG, "saveEvaluate: completed $it") }
                .catch { Log.e(TAG, "saveEvaluate: ${it.localizedMessage}" ) }
                .collect{
if (it.success==true)
    Log.d(TAG, "saveEvaluate: success")
                }
        }

    fun submitEvaluate(id: Int,request: BeginSubmitEvaluateRequest)=
        viewModelScope.launch(handlerException) {
            evaluate.submitEvaluate(id,request)
                .onStart {
                    Log.i(TAG, "submitEvaluate: on start ")
                }
                .onCompletion { Log.i(TAG, "submitEvaluate: $it")}
                .catch { Log.e(TAG, "submitEvaluate: ${it.localizedMessage}" ) }
                .collect{
if (it.success==true)
                    Log.i(TAG, "submitEvaluate: success")
                }
        }


}