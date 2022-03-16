package com.informed.evaluator.presentation.login.model


import com.squareup.moshi.Json

data class GetUserRole(
    @Json(name = "data")
    val `data`: DataX,
    @Json(name = "success")
    val success: Boolean
)