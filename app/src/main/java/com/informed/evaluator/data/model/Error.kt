package com.informed.trainee.data.model


import com.squareup.moshi.Json

data class Error(
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int
)