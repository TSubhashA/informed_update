package com.informed.evaluator.presentation.landingscreen.fragment.myprofile.domain

import com.informed.evaluator.data.remote.ImageUploadService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import retrofit2.Response

class ImageUploadRepo(val img:ImageUploadService):IImageUploadRepo {

    override suspend fun uploadImage(part: MultipartBody.Part): Flow<Response<Any>> = flow {
        val image= withContext(Dispatchers.IO){img.uploadImage(part)}
        emit(image!!)
    }
}