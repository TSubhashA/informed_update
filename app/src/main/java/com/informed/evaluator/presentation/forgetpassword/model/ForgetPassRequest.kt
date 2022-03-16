package com.informed.evaluator.presentation.forgetpassword.model


import com.squareup.moshi.Json

data class ForgetPassRequest(
    @Json(name = "email")
    val email: String
)