package com.informed.evaluator.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.informed.evaluator.data.remote.SignInService
import com.informed.evaluator.presentation.login.domain.ISignInRepo
import com.informed.evaluator.presentation.login.domain.SigInRepo

class SignInVMFactory: ViewModelProvider.Factory {

    init {
        getInstance()
    }

    companion object {
        @Volatile
        private var INSTANCE: ISignInRepo? = null

        fun getInstance() =
            INSTANCE ?: synchronized(SignInVMFactory::class.java) {
                INSTANCE ?: SigInRepo(
                    SignInService.getInstance()
                ).also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ISignInRepo::class.java).newInstance(INSTANCE)
    }
}