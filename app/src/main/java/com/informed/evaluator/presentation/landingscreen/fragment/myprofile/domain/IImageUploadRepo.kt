package com.informed.evaluator.presentation.landingscreen.fragment.myprofile.domain

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.Response

interface IImageUploadRepo {
    suspend fun uploadImage(part : MultipartBody.Part) : Flow<Response<Any>>
}