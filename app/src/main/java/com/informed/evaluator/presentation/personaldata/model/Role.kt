package com.informed.evaluator.presentation.personaldata.model


import com.squareup.moshi.Json

data class Role(
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "institution_id")
    val institutionId: Int?,
    @Json(name = "is_contact")
    val isContact: Boolean,
    @Json(name = "role")
    val role: String?,
    @Json(name = "status")
    val status: String,
    @Json(name = "updatedAt")
    val updatedAt: String,
    @Json(name = "user_id")
    val userId: Int,
    @Json(name = "year")
    val year: Any?
)