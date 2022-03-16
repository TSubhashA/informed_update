package com.informed.evaluator.presentation.resetpassword.domain

import com.informed.evaluator.data.remote.ForgetPasswordService
import com.informed.evaluator.presentation.forgetpassword.model.ForgetPassResponse
import com.informed.evaluator.presentation.resetpassword.model.ResetPassRequest
import com.informed.evaluator.presentation.resetpassword.model.ResetPassRequestDemo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response

class ResetPassRepo(val forget: ForgetPasswordService): IResetPassRepo {

    override suspend fun resetPass(request: ResetPassRequest)= flow{
        val forg = withContext(Dispatchers.IO){ forget.resetPass(request)}
        emit(forg)
    }
}