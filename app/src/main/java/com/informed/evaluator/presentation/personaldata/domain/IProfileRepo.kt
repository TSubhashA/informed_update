package com.informed.evaluator.presentation.personaldata.domain

import com.informed.evaluator.presentation.personaldata.model.ProfileEditRequest
import com.informed.evaluator.presentation.personaldata.model.ProfileEditResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response


interface IProfileRepo {

    suspend fun editProfile(id: Int, request: ProfileEditRequest): Flow<ProfileEditResponse?>

    suspend fun uploadImage(part:RequestBody) : Flow<Response<Any>?>

}