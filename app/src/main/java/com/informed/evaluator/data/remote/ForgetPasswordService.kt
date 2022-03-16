package com.informed.evaluator.data.remote

import com.informed.evaluator.network.NetworkModule
import com.informed.evaluator.presentation.forgetpassword.model.ForgetPassRequest
import com.informed.evaluator.presentation.forgetpassword.model.ForgetPassResponse
import com.informed.evaluator.presentation.resetpassword.model.ResetPassRequest
import com.informed.evaluator.presentation.resetpassword.model.ResetPassRequestDemo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ForgetPasswordService {

    @POST("account/forgot-password")
    suspend fun forget(@Body request: ForgetPassRequest): Response<Any>?

    @POST("account/reset-password")
    suspend fun resetPass(@Body request: ResetPassRequest): Response<Any>?

    companion object {

        var forget: ForgetPasswordService? = null

        fun getInstance() : ForgetPasswordService {

            if (forget == null) {
                val retrofit = NetworkModule.makeApiService()
                forget = retrofit.create(ForgetPasswordService::class.java)
            }
            return forget!!
        }
    }


}