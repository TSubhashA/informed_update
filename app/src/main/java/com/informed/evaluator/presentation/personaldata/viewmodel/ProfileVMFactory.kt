package com.informed.evaluator.presentation.personaldata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.informed.evaluator.data.remote.ProfileService
import com.informed.evaluator.data.remote.SignInService
import com.informed.evaluator.presentation.login.domain.ISignInRepo
import com.informed.evaluator.presentation.login.domain.SigInRepo
import com.informed.evaluator.presentation.login.viewmodel.SignInVMFactory
import com.informed.evaluator.presentation.personaldata.domain.IProfileRepo
import com.informed.evaluator.presentation.personaldata.domain.ProfileRepo

class ProfileVMFactory:ViewModelProvider.Factory {

    init {
        getInstance()
    }

    companion object {
        @Volatile
        private var INSTANCE: IProfileRepo? = null

        fun getInstance() =
            INSTANCE ?: synchronized(ProfileVMFactory::class.java) {
                INSTANCE ?: ProfileRepo(
                    ProfileService.getInstance()
                ).also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }



    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IProfileRepo::class.java).newInstance(INSTANCE)

    }
}