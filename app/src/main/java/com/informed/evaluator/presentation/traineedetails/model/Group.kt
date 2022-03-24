package com.informed.evaluator.presentation.traineedetails.model


import com.squareup.moshi.Json

data class Group(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)