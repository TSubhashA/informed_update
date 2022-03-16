package com.informed.trainee.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class ErrorResponse(
    @Json(name = "error")
    val error: Error
)