package com.informed.evaluator.data.remote

import com.informed.evaluator.network.NetworkModule
import com.informed.evaluator.presentation.personaldata.model.ProfileEditRequest
import com.informed.evaluator.presentation.personaldata.model.ProfileEditResponse
import com.informed.evaluator.presentation.personaldata.model.SignedUrlResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ProfileService {


    @PUT("account/profile/{id}")
    suspend fun editProfile(@Path("id") id:Int,@Body request:ProfileEditRequest): ProfileEditResponse?

    @GET("gcp/signed-url")
    suspend fun uploadImage(@Query("file_name") file_name:String ): SignedUrlResponse?

    @PUT
    suspend fun uploading(@Body body: RequestBody) : Response<Any?>


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