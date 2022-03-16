package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.model


import com.squareup.moshi.Json

data class AttendingListResp(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "success")
    val success: Boolean
)