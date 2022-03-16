package com.informed.evaluator.presentation.login.model


import com.squareup.moshi.Json

data class Role(
    @Json(name = "role")
    val role: String,
    @Json(name = "role_id")
    val roleId: Int,
    @Json(name = "token")
    val token: String
)