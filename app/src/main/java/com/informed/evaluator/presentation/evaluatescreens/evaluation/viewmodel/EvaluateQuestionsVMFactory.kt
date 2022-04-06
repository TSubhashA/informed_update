package com.informed.evaluator.presentation.evaluatescreens.evaluation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.informed.evaluator.data.remote.EvaluationServices
import com.informed.evaluator.presentation.evaluatescreens.evaluation.domain.EvaluateQuestionRepo
import com.informed.evaluator.presentation.evaluatescreens.evaluation.domain.IEvaluateQuestionRepo

class EvaluateQuestionsVMFactory:ViewModelProvider.Factory {

    init {
        getInstance()
    }

    companion object{
        @Volatile
        private var INSTANCE:IEvaluateQuestionRepo?=null

        fun getInstance()=
                INSTANCE ?:synchronized(EvaluateQuestionsVMFactory::class.java) {
                   INSTANCE ?: EvaluateQuestionRepo(EvaluationServices.getInstance()).also { INSTANCE = it }
                }

        fun destroyInstance(){
            INSTANCE=null
        }
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return modelClass.getConstructor(IEvaluateQuestionRepo::class.java).newInstance(INSTANCE)
    }
}