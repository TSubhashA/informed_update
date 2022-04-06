package com.informed.evaluator.data.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*


interface ImageUploadService {

    @Multipart
    @PUT
    suspend fun uploading(
        @Url url: String,
        @Header("Content-Type") contentType:String ,
        @Part part: MultipartBody.Part
    ): Response<Any>?


}