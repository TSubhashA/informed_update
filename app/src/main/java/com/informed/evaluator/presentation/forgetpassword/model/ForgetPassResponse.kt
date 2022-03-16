package com.informed.evaluator.presentation.forgetpassword.model


import com.squareup.moshi.Json

data class ForgetPassResponse(
    @Json(name = "data")
    val `data`: String,
    @Json(name = "success")
    val success: Boolean
)