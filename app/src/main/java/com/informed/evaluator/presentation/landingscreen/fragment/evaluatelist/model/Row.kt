package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.model


import com.squareup.moshi.Json

data class Row(
    @Json(name = "activated_on")
    val activatedOn: String?,
    @Json(name = "createdAt")
    val createdAt: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "is_removed")
    val isRemoved: Boolean,
    @Json(name = "name")
    val name: String?,
    @Json(name = "otp")
    val otp: Any?,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "roles")
    val roles: List<Role?>,
    @Json(name = "status")
    val status: String?,
    @Json(name = "updatedAt")
    val updatedAt: String?
)