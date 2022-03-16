package com.informed.evaluator.presentation.forgetpassword.domain

import com.informed.evaluator.data.remote.ForgetPasswordService
import com.informed.evaluator.presentation.forgetpassword.model.ForgetPassRequest
import com.informed.evaluator.presentation.forgetpassword.model.ForgetPassResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response


class ForgetRepo(val forget: ForgetPasswordService):IForgetRepo {


    override suspend fun forget(request: ForgetPassRequest): Flow<Response<Any>?> = flow{
        val forg = withContext(Dispatchers.IO){ forget.forget(request)}
        emit(forg)
    }




}