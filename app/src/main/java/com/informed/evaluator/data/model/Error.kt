package com.informed.trainee.data.model


import com.squareup.moshi.Json

data class Error(
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int
)


data class Error2(
    @Json(name = "message")
    val message: String,
    @Json(name = "success")
    val success: Boolean
)