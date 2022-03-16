package com.informed.evaluator.presentation.login.model


import com.squareup.moshi.Json

data class SignInResponse(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "success")
    val success: Boolean
)