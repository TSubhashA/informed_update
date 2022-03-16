package com.informed.evaluator.presentation.login.domain

import com.informed.evaluator.data.remote.SignInService
import com.informed.evaluator.presentation.login.model.GetUserRole
import com.informed.evaluator.presentation.login.model.SignInRequest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

class SigInRepo(val sign:SignInService): ISignInRepo {

    override suspend fun signIn(request: SignInRequest): Flow<Response<Any>?> = flow {
        val sign = withContext(Dispatchers.IO){sign.signIn(request)}
    emit(sign)
    }

    override suspend fun getUserRole(id: String, auth:String): Flow<GetUserRole?> = flow {
        val userRole = withContext(Dispatchers.IO){sign.getUserRole(id,auth)}
        emit(userRole)
    }


}


