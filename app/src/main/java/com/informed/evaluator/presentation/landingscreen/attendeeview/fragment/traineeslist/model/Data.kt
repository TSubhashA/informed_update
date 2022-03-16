package com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.model


import com.squareup.moshi.Json

data class Data(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "rows")
    var rows: List<Row?>
)