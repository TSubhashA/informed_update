package com.informed.evaluator.presentation.evaluatescreens.evaluatestart.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.informed.evaluator.base.BaseViewModel
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.domain.IEvaluationRepo
import com.informed.trainee.data.model.ResultOf
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class EvaluationViewModel(val evaluation:IEvaluationRepo) : BaseViewModel() {

    private var page: Int = 1
    private var pageSize: Int = 10
    var dataSource: MutableLiveData<ResultOf<*>>? = MutableLiveData()

    fun changePage() {
        Log.e(ContentValues.TAG, "changePage: $page")
        page++
       getEvaluation()
    }

    fun getEvaluation()=viewModelScope.launch(handlerException) {
        evaluation.getEvaluation(page,pageSize)
            .onStart { dataSource?.value = ResultOf.Progress(true) }
            .onCompletion { dataSource?.value = ResultOf.Progress(false) }
            .catch {
                dataSource?.value = ResultOf.Failure(it.cause?.message, it) }
            .collect {
                if (it.success==true) {
                        dataSource?.value = ResultOf.Success(it)
                } else {
                    dataSource?.value = ResultOf.Empty("data Empty")
                    page--
                }
            }
    }




}