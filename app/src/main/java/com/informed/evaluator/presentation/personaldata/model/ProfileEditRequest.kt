package com.informed.evaluator.presentation.personaldata.model


import com.squareup.moshi.Json

data class ProfileEditRequest(
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "status")
    val status: String
)