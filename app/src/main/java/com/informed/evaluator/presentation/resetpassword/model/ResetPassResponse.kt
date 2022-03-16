package com.informed.evaluator.presentation.resetpassword.model


import com.squareup.moshi.Json

data class ResetPassResponse(
    @Json(name = "data")
    val `data`: String,
    @Json(name = "success")
    val success: Boolean
)