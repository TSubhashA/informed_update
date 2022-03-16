package com.informed.evaluator.data.remote

import com.informed.evaluator.network.NetworkModule
import com.informed.evaluator.presentation.login.model.GetUserRole
import com.informed.evaluator.presentation.login.model.SignInRequest
import com.informed.evaluator.presentation.login.model.SignInResponse
import retrofit2.Response
import retrofit2.http.*


interface SignInService {

    @POST("account/login")
    suspend fun signIn(@Body request:SignInRequest): Response<Any>?

    @GET("account/user-roles/{user_id}")
    suspend fun getUserRole(@Path("user_id") user_id:String, @Header("Authorization") authHeader:String): GetUserRole?

    companion object {

        var signService: SignInService? = null

        fun getInstance() : SignInService {

            if (signService == null) {
                val retrofit =NetworkModule.makeApiService()
                signService = retrofit.create(SignInService::class.java)
            }
            return signService!!
        }
    }

}