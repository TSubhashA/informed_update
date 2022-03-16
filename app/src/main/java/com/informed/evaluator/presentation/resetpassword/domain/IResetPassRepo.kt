package com.informed.evaluator.presentation.resetpassword.domain


import com.informed.evaluator.presentation.forgetpassword.model.ForgetPassResponse
import com.informed.evaluator.presentation.resetpassword.model.ResetPassRequest
import com.informed.evaluator.presentation.resetpassword.model.ResetPassRequestDemo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IResetPassRepo {

    suspend fun resetPass(request: ResetPassRequest) : Flow<Response<Any>?>



}