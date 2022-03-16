package com.informed.evaluator.presentation.personaldata.viewmodel

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.net.toFile
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.informed.evaluator.base.BaseViewModel
import com.informed.evaluator.network.NetworkModule
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference
import com.informed.evaluator.presentation.personaldata.domain.IProfileRepo
import com.informed.evaluator.presentation.personaldata.model.ImageUploadResponse
import com.informed.evaluator.presentation.personaldata.model.ProfileEditRequest
import com.informed.evaluator.presentation.personaldata.model.ProfileEditResponse
import com.informed.evaluator.utils.URIPathHelper
import com.informed.trainee.data.model.ErrorResponse
import com.informed.trainee.data.model.ResultOf
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.net.URI

class ProfileViewModel(val profileRepo: IProfileRepo) : BaseViewModel() {

    private val user_id = SharedPreference().getValueString(ConstantKeys.ID)?.toInt()

    var imageData: MutableLiveData<ResultOf<*>>? = MutableLiveData()

    fun editProfile(request: ProfileEditRequest) = liveData {
        profileRepo.editProfile(user_id!!, request)
            .onStart { emit(ResultOf.Progress(true)) }
            .onCompletion { emit(ResultOf.Progress(false)) }
            .catch { emit(ResultOf.Failure(it.message, it)) }
            .collect {
                updateCache(it)
                emit(ResultOf.Success(it))
            }
    }

    private fun updateCache(profileEditResponse: ProfileEditResponse?) {
        profileEditResponse?.data?.name?.let { SharedPreference().setString(ConstantKeys.NAME, it) }
        profileEditResponse?.data?.phone?.let {
            SharedPreference().setString(
                ConstantKeys.MOBILE,
                it
            )
        }
        profileEditResponse?.data?.imageUrl?.let {
            SharedPreference().setString(
                ConstantKeys.IMAGE_URL,
                it
            )
        }

    }

    fun uploadImage(context: Context, uri: Uri)= viewModelScope.launch(handlerException){
val filePath=URIPathHelper()
      val file=  filePath.getPath(context,uri)
        Log.e(TAG, "uploadImage: afterpath $file" )
        Log.e(TAG, "uploadImage: api+${uri.toString()}" )
//        val file =  File(uri.toString())
        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file.toString())
        val partBuildinig=MultipartBody.Part.createFormData("file",file,requestFile)
        profileRepo.uploadImage(requestFile)
            .onStart { imageData?.value=ResultOf.Progress(true) }
            .onCompletion { imageData?.value=ResultOf.Progress(false) }
            .catch {imageData?.value=ResultOf.Failure(it.message, it) }
            .collect {
                if (it?.isSuccessful == true){
                    val successRes=NetworkModule.convertResponse(ImageUploadResponse::class.java, it.body()!!)
                    imageData?.value=ResultOf.Success(successRes)
                }else
                {
                    val failedRes=NetworkModule.convertResponse(ErrorResponse::class.java,it?.errorBody()!!)
                    imageData?.value=ResultOf.Failed(failedRes)
                }

            }

    }




}