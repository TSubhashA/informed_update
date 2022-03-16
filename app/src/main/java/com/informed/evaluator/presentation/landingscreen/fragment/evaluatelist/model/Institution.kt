package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.model


import com.squareup.moshi.Json

data class Institution(
    @Json(name = "about")
    val about: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "logo")
    val logo: Any?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "plan")
    val plan: Any?
)