package com.informed.evaluator.data.remote

import com.informed.evaluator.network.NetworkModule
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Part


interface ImageUploadService {

    @POST("gcp/upload-image")
    suspend fun uploadImage(@Part part: MultipartBody.Part): Response<Any>?

    companion object {

        var image: ImageUploadService? = null

        fun getInstance() : ImageUploadService {

            if (image == null) {
                val retrofit = NetworkModule.makeApiServicewithAuth()
                image = retrofit.create(ImageUploadService::class.java)
            }
            return image!!
        }
    }
}