package com.informed.evaluator.presentation.evaluatescreens.evaluatereview.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class BeginEvaluateResponse(

	@Json(name="data")
	val data: Data? = null,

	@Json(name="success")
	val success: Boolean? = null
) : Parcelable

@Parcelize
data class Data(

	@Json(name="evaluation_id")
	val evaluationId: Int? = null,

	@Json(name="scheduled_evaluation_id")
	val scheduledEvaluationId: @RawValue Any? = null,

	@Json(name="evaluatee_group")
	val evaluateeGroup:@RawValue Any? = null,

	@Json(name="evaluator_group")
	val evaluatorGroup: @RawValue Any? = null,

	@Json(name="contextual_info")
	val contextualInfo: ContextualInfo? = null,

	@Json(name="createdAt")
	val createdAt: String? = null,

	@Json(name="completed_at")
	val completedAt: @RawValue Any? = null,

	@Json(name="questionairre_id")
	val questionairreId: Int? = null,

	@Json(name="started_at")
	val startedAt: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="evaluatee")
	val evaluatee: Int? = null,

	@Json(name="evaluator")
	val evaluator: Int? = null,

	@Json(name="status")
	val status: String? = null,

	@Json(name="updatedAt")
	val updatedAt: String? = null
) : Parcelable

@Parcelize
data class ContextualInfo(

	@Json(name="comment")
	val comment: String? = null
) : Parcelable
