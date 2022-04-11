package com.informed.evaluator.presentation.personaldata.domain

import com.informed.evaluator.common.BaseApp
import com.informed.evaluator.data.remote.ImageUploadService
import com.informed.evaluator.data.remote.ProfileService
import com.informed.evaluator.data.remote.SignInService
import com.informed.evaluator.network.NetworkModule
import com.informed.evaluator.network.adapter.NullToEmptyString
import com.informed.evaluator.network.adapter.NullToEmptyStringAdapter
import com.informed.evaluator.presentation.personaldata.model.ProfileEditRequest
import com.informed.evaluator.presentation.personaldata.model.ProfileEditResponse
import com.informed.evaluator.presentation.personaldata.model.SignedUrlResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ProfileRepo(val prof: ProfileService):IProfileRepo {
    override suspend fun editProfile(id: Int, request:ProfileEditRequest): Flow<ProfileEditResponse?> = flow{

        val update= withContext(Dispatchers.IO){prof.editProfile(id,request)}

        emit(update!!)
    }.flowOn(Dispatchers.IO)

    override suspend fun uploadImage(file:String): Flow<SignedUrlResponse?> = flow{

        val image=prof.uploadImage(file)

        emit(image)

    }.flowOn(Dispatchers.IO)

    override suspend fun uploading(
        url: String,
        content: String,
        part: MultipartBody.Part
    ): Flow<Response<Any>?> = flow<Response<Any>?> {

        val signedBaseUrl="https://storage.googleapis.com/"

        val retrofit=Retrofit.Builder()
            .baseUrl(signedBaseUrl )
            .client(NetworkModule.okHttpClient(BaseApp.getAppContext()).build())
            .addConverterFactory(MoshiConverterFactory.create(NetworkModule.moshiFactory()))
            .build()

        val urlUpdated=url.replace(signedBaseUrl,"")
        val uploadService=retrofit.create(ImageUploadService::class.java)
        val response=uploadService.uploading(urlUpdated,content,part)
        emit(response)

    }.flowOn(Dispatchers.IO)


}