package com.informed.evaluator.presentation.login.model


import com.squareup.moshi.Json

data class SignInRequest(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)