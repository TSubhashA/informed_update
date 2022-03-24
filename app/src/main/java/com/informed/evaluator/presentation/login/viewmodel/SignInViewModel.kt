package com.informed.evaluator.presentation.login.viewmodel


import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.liveData
import com.informed.evaluator.base.BaseViewModel
import com.informed.evaluator.network.NetworkModule
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference
import com.informed.evaluator.presentation.login.domain.ISignInRepo
import com.informed.evaluator.presentation.login.model.SignInRequest
import com.informed.evaluator.presentation.login.model.SignInResponse
import com.informed.trainee.data.model.ErrorResponse
import com.informed.trainee.data.model.ResultOf
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import org.json.JSONObject
import java.lang.Error

class SignInViewModel(val signInRepo: ISignInRepo) : BaseViewModel() {


    fun getSignInResult(sign: SignInRequest) =
        liveData {

            signInRepo.signIn(sign)
                .onStart { emit(ResultOf.Progress(true)) }
                .onCompletion {
                    Log.e(TAG, "getSignInResult: Api Call Completed" )  }
                .catch { emit(ResultOf.Failure(it.message, it)) }
                .collect {
                    if (it != null) {
                        if (it.isSuccessful)
                        saveData(it.body()?.let { it1 ->
                            NetworkModule.convertResponse(
                                SignInResponse::class.java,
                                it1
                            )
                        } as SignInResponse)
                        emit(
                            if (it.isSuccessful) {
                                ResultOf.Success(it.body()?.let { it1 ->
                                    NetworkModule.convertResponse(
                                        SignInResponse::class.java,
                                        it1
                                    )

                                })
                            } else {
                                Log.d(" Failed : ", "${it.body()}")
                                Log.d(" Failed error : ", "${it.errorBody()?.source()}")
                                ResultOf.Failed(

                                    NetworkModule.convertResponse(
                                        com.informed.trainee.data.model.Error2::class.java,
                                    it.errorBody()?.source()!!
                                    )


                                )
                            }
                        )
                    }

                }
            emit(ResultOf.Progress(false))
        }

    fun getUserRole(id: String) = liveData {
        emit(ResultOf.Progress(true))
        signInRepo.getUserRole(
            id,
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywibmFtZSI6InN1cGVyIGFkbWluIDIiLCJwaG9uZSI6IjAwMDAwMDAwMCIsImVtYWlsIjoic3VwZXJhZG1pbjJAeW9wbWFpbC5jb20iLCJwYXNzd29yZCI6IiQyYiQxMCRNU2pDQW84VldKeVpHT1ZVSDdUZ0x1dThzRWRIVlEweXpZc1lQazZmWjBRZ3RJZmFrUkJqUyIsImFjdGl2YXRlZF9vbiI6IjIwMjEtMTEtMjZUMDk6MDg6MjUuMTAwWiIsImltYWdlX3VybCI6bnVsbCwic3RhdHVzIjoiYWN0aXZlIiwiaXNfcmVtb3ZlZCI6ZmFsc2UsInJlZnJlc2hfdG9rZW4iOm51bGwsImVuY3J5cHRlZEtleSI6bnVsbCwiY3JlYXRlZEF0IjoiMjAyMS0xMS0xMVQwNToyMToxMy4wMTZaIiwidXBkYXRlZEF0IjoiMjAyMS0xMS0xMVQwNToyMToxMy4wMTZaIiwicm9sZXMiOls1XSwiaXNfc3VwZXJfYWRtaW4iOnRydWUsImlhdCI6MTYzOTU2MTY3NSwiZXhwIjoxNjQyMTUzNjc1fQ.mhRx674YTfZlqbXafdolCh9-wHUWTdwNGYZOIUrYzFc"
        )
            .catch { emit(ResultOf.Failure(it.message, it)) }
            .collect { emit(if (it?.success == true) ResultOf.Success(it) else ResultOf.Empty("Unable to login")) }
        emit(ResultOf.Progress(false))
    }


    private fun saveData(value: SignInResponse) {
        SharedPreference().setString(
            ConstantKeys.ACCESS_TOKEN,
            value.data.roles[0]?.token.toString()
        )
        SharedPreference().setString(ConstantKeys.IMAGE_URL, value.data.imageUrl.toString())
        SharedPreference().setString(ConstantKeys.FIRSTNAME, value.data.name.toString())
        SharedPreference().setString(ConstantKeys.EMAIL, value.data.email)
        SharedPreference().setString(ConstantKeys.ID, value.data.id.toString())
        SharedPreference().setString(ConstantKeys.MOBILE, value.data.phone.toString())
        SharedPreference().setString(ConstantKeys.USER_ROLE, value.data.roles[0]?.role.toString())

        if (value.data.roles[0]?.role.toString() == "attending")
        SharedPreference().setBoolean(ConstantKeys.IS_ATTENDEE,true)
        else
            SharedPreference().setBoolean(ConstantKeys.IS_ATTENDEE,false)


    }


}