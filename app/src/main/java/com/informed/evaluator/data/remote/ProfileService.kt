package com.informed.evaluator.data.remote

import com.informed.evaluator.network.NetworkModule
import com.informed.evaluator.presentation.personaldata.model.ProfileEditRequest
import com.informed.evaluator.presentation.personaldata.model.ProfileEditResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ProfileService {


    @PUT("account/profile/{id}")
    suspend fun editProfile(@Path("id") id:Int,@Body request:ProfileEditRequest): ProfileEditResponse?

    @Multipart
    @POST("gcp/upload-image")
    suspend fun uploadImage(@Part part: MultipartBody.Part): Response<Any>?

    companion object {

        @Volatile
        var prof: ProfileService? = null

        fun getInstance() : ProfileService {

            if (prof == null) {
                val retrofit = NetworkModule.makeApiServicewithAuth()
                prof = retrofit.create(ProfileService::class.java)
            }
            return prof!!
        }
    }


}