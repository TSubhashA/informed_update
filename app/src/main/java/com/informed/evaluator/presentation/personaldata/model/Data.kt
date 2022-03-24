package com.informed.evaluator.presentation.personaldata.model


import com.squareup.moshi.Json

data class Data(
    @Json(name = "activated_on")
    val activatedOn: String,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "otp")
    val otp: Any?,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "roles")
    val roles: List<Role?>,
    @Json(name = "status")
    val status: String,
    @Json(name = "updatedAt")
    val updatedAt: String
)