package com.informed.evaluator.presentation.forgetpassword.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.informed.evaluator.data.remote.ForgetPasswordService
import com.informed.evaluator.presentation.forgetpassword.domain.ForgetRepo
import com.informed.evaluator.presentation.forgetpassword.domain.IForgetRepo

class ForgetVMFactory: ViewModelProvider.Factory {

    init {
        getInstance()
    }

    companion object {
        @Volatile
        private var INSTANCE: IForgetRepo? = null

        fun getInstance() =
            INSTANCE ?: synchronized(ForgetVMFactory::class.java) {
                INSTANCE ?: ForgetRepo(
                    ForgetPasswordService.getInstance()
                ).also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IForgetRepo::class.java).newInstance(INSTANCE)
    }


}