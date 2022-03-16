package com.informed.evaluator.presentation.login.model


import com.squareup.moshi.Json

data class Data(
    @Json(name = "activated_on")
    val activatedOn: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "encryptedKey")
    val encryptedKey: Any?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image_url")
    val imageUrl: Any?,
    @Json(name = "is_removed")
    val isRemoved: Boolean?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "phone")
    val phone: String?,
    @Json(name = "refresh_token")
    val refreshToken: Any?,
    @Json(name = "roles")
    val roles: List<Role?>,
    @Json(name = "status")
    val status: String?
)