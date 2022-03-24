package com.informed.evaluator.presentation.evaluatescreens.evaluatestart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.informed.evaluator.data.remote.EvaluationServices
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.domain.EvaluationRepo
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.domain.IEvaluationRepo


class EvaluationVMFactory : ViewModelProvider.Factory {

    init {
        getInstance()
    }

    companion object {
        @Volatile
        private var INSTANCE: IEvaluationRepo? = null

        fun getInstance() =
            INSTANCE ?: synchronized(EvaluationVMFactory::class.java) {
                INSTANCE ?: EvaluationRepo(
                    EvaluationServices.getInstance()
                ).also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IEvaluationRepo::class.java).newInstance(INSTANCE)
    }

}