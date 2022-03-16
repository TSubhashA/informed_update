package com.informed.evaluator.presentation.resetpassword.model


import com.squareup.moshi.Json

data class ResetPassRequestDemo(
    @Json(name = "email")
    val email: String,
    @Json(name = "otp")
    val otp: String,
    @Json(name = "password")
    val password: String
)