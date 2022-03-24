package com.informed.evaluator.presentation.landingscreen.fragment.myprofile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.informed.evaluator.data.remote.ImageUploadService
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.viewmodel.EvaluatorVMFactory
import com.informed.evaluator.presentation.landingscreen.fragment.myprofile.domain.IImageUploadRepo
import com.informed.evaluator.presentation.landingscreen.fragment.myprofile.domain.ImageUploadRepo

class MyProfileVMFactory : ViewModelProvider.Factory {

    init {
        EvaluatorVMFactory.getInstance()
    }

    companion object {
        @Volatile
        private var INSTANCE: IImageUploadRepo? = null

        fun getInstance() =
            INSTANCE ?: synchronized(MyProfileVMFactory::class.java) {
                INSTANCE ?: ImageUploadRepo(
                    ImageUploadService.getInstance()
                ).also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IImageUploadRepo::class.java).newInstance(
            INSTANCE
        )
    }


}