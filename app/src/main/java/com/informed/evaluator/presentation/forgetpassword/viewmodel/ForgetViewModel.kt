package com.informed.evaluator.presentation.forgetpassword.viewmodel

import android.util.Log
import androidx.lifecycle.liveData
import com.informed.evaluator.base.BaseViewModel
import com.informed.evaluator.network.NetworkModule
import com.informed.evaluator.presentation.forgetpassword.domain.IForgetRepo
import com.informed.evaluator.presentation.forgetpassword.model.ForgetPassRequest
import com.informed.evaluator.presentation.forgetpassword.model.ForgetPassResponse
import com.informed.trainee.data.model.ErrorResponse
import com.informed.trainee.data.model.ResultOf
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect


class ForgetViewModel(val forgetRepo: IForgetRepo): BaseViewModel() {


    fun forget(forgReq: ForgetPassRequest) = liveData {
        emit(ResultOf.Progress(true))
        forgetRepo.forget(forgReq)
            .catch { emit(ResultOf.Failure(it.message, it)) }
            .collect {
                emit(
                    if (it != null) {

                        if (it.isSuccessful) {

                            ResultOf.Success(it.body()?.let { it1 ->
                                NetworkModule.convertResponse(
                                    ForgetPassResponse::class.java,
                                    it1
                                )
                            })
                        } else {

                            Log.d(" Failed : ", "${it.errorBody()}")

                            ResultOf.Failed( NetworkModule.convertResponse(
                                ErrorResponse::class.java,
                                it.body()!!
                            ))
                        }
                    } else ResultOf.Empty("Unable to rest password")
                )
            }
        emit(ResultOf.Progress(false))
    }


}