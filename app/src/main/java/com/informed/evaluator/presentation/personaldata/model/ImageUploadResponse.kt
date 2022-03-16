package com.informed.evaluator.presentation.personaldata.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageUploadResponse(

	@Json(name="data")
	val data: Data1? = null,

	@Json(name="success")
	val success: Boolean? = null
) : Parcelable

@Parcelize
data class Data1(
	@Json(name="url")
	val url: String? = null
) : Parcelable
