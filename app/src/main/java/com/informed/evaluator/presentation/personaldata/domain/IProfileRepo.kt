package com.informed.evaluator.presentation.personaldata.domain

import com.informed.evaluator.presentation.personaldata.model.ProfileEditRequest
import com.informed.evaluator.presentation.personaldata.model.ProfileEditResponse
import com.informed.evaluator.presentation.personaldata.model.SignedUrlResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response


interface IProfileRepo {

    suspend fun editProfile(id: Int, request: ProfileEditRequest): Flow<ProfileEditResponse?>

    suspend fun uploadImage(file:String) : Flow<SignedUrlResponse?>


    suspend fun uploading(url:String,content:String,part:MultipartBody.Part) : Flow<Response<Any>?>




}