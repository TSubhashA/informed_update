package com.informed.evaluator.presentation.traineedetails.model


import com.squareup.moshi.Json

data class Role(
    @Json(name = "user_groups")
    val userGroups: List<UserGroup>,
    @Json(name = "year")
    val year: String
)