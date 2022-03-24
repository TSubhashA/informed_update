package com.informed.evaluator.presentation.personaldata.model


import com.squareup.moshi.Json

data class ProfileEditRequest(
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "status")
    val status: String
)