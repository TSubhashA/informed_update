package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.informed.evaluator.data.remote.EvaluatorService
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.domain.AttendingListRepo
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.domain.IAttendingListRepo


class EvaluatorVMFactory : ViewModelProvider.Factory {

    init {
        getInstance()
    }

    companion object {
        @Volatile
        private var INSTANCE: IAttendingListRepo? = null

        fun getInstance() =
            INSTANCE ?: synchronized(EvaluatorVMFactory::class.java) {
                INSTANCE ?: AttendingListRepo(
                    EvaluatorService.getInstance()
                ).also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IAttendingListRepo::class.java).newInstance(INSTANCE)

    }

}