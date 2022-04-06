package com.informed.evaluator.presentation.personaldata.model

import com.squareup.moshi.Json

data class SignedUrlResponse(

	@Json(name="data")
	val data: Data1? = null,

	@Json(name="success")
	val success: Boolean? = null
)

data class Data1(

	@Json(name="getUrl")
	val getUrl: String? = null,

	@Json(name="url")
	val url: String? = null
)
