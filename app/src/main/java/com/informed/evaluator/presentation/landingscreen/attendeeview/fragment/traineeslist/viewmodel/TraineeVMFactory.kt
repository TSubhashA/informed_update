package com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.informed.evaluator.data.remote.TraineeService
import com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.domain.ITraineeListRepo
import com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.domain.TraineeListRepo

class TraineeVMFactory: ViewModelProvider.Factory {

    init {
        getInstance()
    }

    companion object {
        @Volatile
        private var INSTANCE: ITraineeListRepo? = null

        fun getInstance() =
            INSTANCE ?: synchronized(TraineeVMFactory::class.java) {
                INSTANCE ?: TraineeListRepo(
                    TraineeService.getInstance()
                ).also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ITraineeListRepo::class.java)
            .newInstance(TraineeVMFactory.INSTANCE)
    }
}