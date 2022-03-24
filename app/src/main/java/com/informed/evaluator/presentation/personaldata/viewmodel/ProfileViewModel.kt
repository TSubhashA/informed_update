package com.informed.evaluator.presentation.personaldata.viewmodel

import android.content.ContentResolver
import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.os.Environment
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
import com.informed.trainee.data.model.Error2
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
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
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
        profileEditResponse?.data?.firstName?.let { SharedPreference().setString(ConstantKeys.FIRSTNAME, it) }
        profileEditResponse?.data?.lastName?.let { SharedPreference().setString(ConstantKeys.LASTNAME, it) }
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
        val file1=  filePath.getPath(context,uri)
        val file = File(file1.toString())

        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            // STEP 1: Create a tempFile for storing the image from scoped storage.
            val tempFile = createTempFile(context, "image_temp", file.extension)

            // STEP 2: copy inputStream into the tempFile
            copyStreamToFile(inputStream, tempFile)

            // STEP 3: get file path from tempFile for further upload process.
            val filePath = tempFile.absolutePath

            val requestFile: RequestBody =
                File(filePath).asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val body: MultipartBody.Part =
                MultipartBody.Part.createFormData("file", "image_temp", requestFile)

//        val content=ContentResolver(context).openInputStream(uri)
            Log.e(TAG, "uploadImage: afterpath $file")
            Log.e(TAG, "uploadImage: api+${uri.toString()}")
            profileRepo.uploadImage(body)
                .onStart { imageData?.value = ResultOf.Progress(true) }
                .onCompletion { imageData?.value = ResultOf.Progress(false) }
                .catch { imageData?.value = ResultOf.Failure(it.message, it) }
                .collect {
                    if (it?.code() == 200) {

                        val successRes = NetworkModule.convertResponse(
                            ImageUploadResponse::class.java,
                            it.body()!!
                        )

                        imageData?.value = ResultOf.Success(successRes)

                    } else {

                        val failedRes = NetworkModule.convertResponse(
                            ErrorResponse::class.java,
                            it?.errorBody()?.source()!!
                        )

                        imageData?.value = ResultOf.Failed(failedRes)

                    }

                }
        }
    }


    @Throws(IOException::class)
    fun createTempFile(context: Context, fileName: String?, extension: String?): File {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        return File(storageDir, "$fileName.$extension")
    }

    fun copyStreamToFile(inputStream: InputStream, outputFile: File) {
        inputStream.use { input ->
            val outputStream = FileOutputStream(outputFile)
            outputStream.use { output ->
                val buffer = ByteArray(4 * 1024) // buffer size
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
            }
        }
    }




}