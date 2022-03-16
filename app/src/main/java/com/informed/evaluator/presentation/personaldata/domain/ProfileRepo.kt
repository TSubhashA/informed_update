package com.informed.evaluator.presentation.personaldata.domain

import com.informed.evaluator.data.remote.ProfileService
import com.informed.evaluator.data.remote.SignInService
import com.informed.evaluator.presentation.personaldata.model.ProfileEditRequest
import com.informed.evaluator.presentation.personaldata.model.ProfileEditResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class ProfileRepo(val prof: ProfileService):IProfileRepo {
    override suspend fun editProfile(id: Int, request:ProfileEditRequest): Flow<ProfileEditResponse?> = flow{

        val update= withContext(Dispatchers.IO){prof.editProfile(id,request)}

        emit(update!!)
    }.flowOn(Dispatchers.IO)

    override suspend fun uploadImage(part: RequestBody): Flow<Response<Any>?> = flow{

        val image=prof.uploadImage(part)
        emit(image)

    }.flowOn(Dispatchers.IO)
}