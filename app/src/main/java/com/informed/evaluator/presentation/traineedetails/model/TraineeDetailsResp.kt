package com.informed.evaluator.presentation.traineedetails.model


import com.squareup.moshi.Json

data class TraineeDetailsResp(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "success")
    val success: Boolean
)