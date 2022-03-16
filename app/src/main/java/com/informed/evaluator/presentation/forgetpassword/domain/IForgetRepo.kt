package com.informed.evaluator.presentation.forgetpassword.domain

import com.informed.evaluator.presentation.forgetpassword.model.ForgetPassRequest
import com.informed.evaluator.presentation.forgetpassword.model.ForgetPassResponse

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IForgetRepo {

    suspend fun forget(request: ForgetPassRequest) : Flow<Response<Any>?>



}