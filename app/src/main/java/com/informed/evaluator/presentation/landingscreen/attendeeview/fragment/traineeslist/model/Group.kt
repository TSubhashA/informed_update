package com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.model


import com.squareup.moshi.Json
import java.io.Serializable

data class Group(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
):Serializable