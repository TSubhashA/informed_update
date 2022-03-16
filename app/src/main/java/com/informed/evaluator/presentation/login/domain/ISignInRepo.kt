package com.informed.evaluator.presentation.login.domain

import com.informed.evaluator.presentation.login.model.GetUserRole
import com.informed.evaluator.presentation.login.model.SignInRequest
import com.informed.evaluator.presentation.login.model.SignInResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ISignInRepo {

    suspend fun signIn(request: SignInRequest) : Flow<Response<Any>?>

    suspend fun getUserRole(id: String, auth:String) : Flow<GetUserRole?>

}