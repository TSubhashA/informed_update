package com.informed.evaluator.presentation.personaldata.model


import com.squareup.moshi.Json

data class ProfileEditResponse(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "success")
    val success: Boolean
)