package com.informed.evaluator.presentation.traineedetails


import com.squareup.moshi.Json

data class UserGroup(
    @Json(name = "group")
    val group: Group,
    @Json(name = "id")
    val id: Int
)