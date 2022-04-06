package com.informed.evaluator.presentation.evaluatescreens.evaluatereview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.informed.evaluator.data.remote.EvaluationServices
import com.informed.evaluator.presentation.evaluatescreens.evaluatereview.domain.EvaluateConfirmRepo
import com.informed.evaluator.presentation.evaluatescreens.evaluatereview.domain.IEvaluateConfirmRepo
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.viewmodel.EvaluationVMFactory

class EvaluateConfirmVMFactory: ViewModelProvider.Factory {

    init {
        getInstance()
    }

    companion object{
        @Volatile
        private var INSTANCE:IEvaluateConfirmRepo? =null

        fun getInstance()=
            INSTANCE?: synchronized(EvaluateConfirmVMFactory::class.java){

                INSTANCE ?: EvaluateConfirmRepo(
                    EvaluationServices.getInstance()
                ).also{
                    INSTANCE=it
                }
            }


        fun destroyInstance() {
            INSTANCE = null
        }


    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return modelClass.getConstructor(IEvaluateConfirmRepo::class.java).newInstance(INSTANCE)
    }
}