package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.model


import com.squareup.moshi.Json

data class Data(
    @Json(name = "count")
    val count: Int,
    @Json(name = "rows")
    val rows: List<Row>
)