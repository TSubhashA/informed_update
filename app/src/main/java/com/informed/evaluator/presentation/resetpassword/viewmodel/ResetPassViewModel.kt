package com.informed.evaluator.presentation.resetpassword.viewmodel

import android.util.Log
import androidx.lifecycle.liveData
import com.informed.evaluator.base.BaseViewModel
import com.informed.evaluator.network.NetworkModule

import com.informed.evaluator.presentation.resetpassword.domain.IResetPassRepo
import com.informed.evaluator.presentation.resetpassword.model.ResetPassRequest
import com.informed.evaluator.presentation.resetpassword.model.ResetPassRequestDemo
import com.informed.evaluator.presentation.resetpassword.model.ResetPassResponse
import com.informed.trainee.data.model.ErrorResponse
import com.informed.trainee.data.model.ResultOf
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class ResetPassViewModel(val resetRepo: IResetPassRepo):BaseViewModel() {

    fun reset( request: ResetPassRequest)= liveData{
        emit(ResultOf.Progress(true))
        resetRepo.resetPass(request)
            .catch { emit(ResultOf.Failure(it.localizedMessage, it)) }
            .collect {
                emit(
                    if (it != null) {

                        if (it.isSuccessful) {

                            ResultOf.Success(it.body()?.let { it1 ->
                                NetworkModule.convertResponse(
                                    ResetPassResponse::class.java,
                                    it1
                                )
                            })
                        } else {

                            Log.d(" Failed : ", "${it.body()}")

                            ResultOf.Failed( NetworkModule.convertResponse(
                                ErrorResponse::class.java,
                                it.body()!!))
                        }
                    } else ResultOf.Empty("Unable to rest password")) }
        emit(ResultOf.Progress(false))
    }

}