package com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.model


import com.squareup.moshi.Json
import java.io.Serializable

data class Role(
    @Json(name = "user_groups")
    val userGroups: List<UserGroup?>,
    @Json(name = "year")
    val year: Any?
):Serializable