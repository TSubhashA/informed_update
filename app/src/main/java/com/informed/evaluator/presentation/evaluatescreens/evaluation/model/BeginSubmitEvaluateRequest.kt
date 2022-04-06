package com.informed.evaluator.presentation.evaluatescreens.evaluation.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class BeginSubmitEvaluateRequest(

	@Json(name="evaluatee_role_id")
    var evaluateeRoleId: Int? = null,

	@Json(name="contextual_info")
	var contextualInfo: Map<String,String>? = null
) : Parcelable

