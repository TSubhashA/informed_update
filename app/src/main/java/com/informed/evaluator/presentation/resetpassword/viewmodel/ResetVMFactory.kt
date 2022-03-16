package com.informed.evaluator.presentation.resetpassword.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.informed.evaluator.data.remote.ForgetPasswordService
import com.informed.evaluator.presentation.resetpassword.domain.IResetPassRepo
import com.informed.evaluator.presentation.resetpassword.domain.ResetPassRepo

class ResetVMFactory : ViewModelProvider.Factory {

    init {
        getInstance()
    }

    companion object {
        @Volatile
        private var INSTANCE: IResetPassRepo? = null

        fun getInstance() =
            INSTANCE ?: synchronized(ResetVMFactory::class.java) {
                INSTANCE ?: ResetPassRepo(
                    ForgetPasswordService.getInstance()
                ).also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IResetPassRepo::class.java).newInstance(INSTANCE)
    }
}