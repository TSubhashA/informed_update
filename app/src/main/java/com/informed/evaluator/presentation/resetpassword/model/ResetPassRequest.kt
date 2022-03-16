package com.informed.evaluator.presentation.resetpassword.model


import com.squareup.moshi.Json

data class ResetPassRequest(
    @Json(name = "encryptedKey")
    val encryptedKey: String,
    @Json(name = "password")
    val password: String
)