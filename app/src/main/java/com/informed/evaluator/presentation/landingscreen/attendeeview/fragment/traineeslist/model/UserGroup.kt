package com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.model


import com.squareup.moshi.Json
import java.io.Serializable

data class UserGroup(
    @Json(name = "group")
    val group: Group?,
    @Json(name = "id")
    val id: Int?
):Serializable