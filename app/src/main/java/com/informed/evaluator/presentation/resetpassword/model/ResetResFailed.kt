package com.informed.evaluator.presentation.resetpassword.model


import com.squareup.moshi.Json

data class ResetResFailed(
    @Json(name = "message")
    val message: String,
    @Json(name = "success")
    val success: Boolean
)