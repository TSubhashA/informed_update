package com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.model


import com.squareup.moshi.Json

data class TraineeListResp(
    @Json(name = "data")
    val `data`: Data?,
    @Json(name = "success")
    val success: Boolean
)