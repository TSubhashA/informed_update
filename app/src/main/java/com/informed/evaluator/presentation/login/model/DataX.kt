package com.informed.evaluator.presentation.login.model


import com.squareup.moshi.Json

data class DataX(
    @Json(name = "count")
    val count: Int,
    @Json(name = "rows")
    val rows: List<Row>
)