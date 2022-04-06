package com.informed.evaluator.presentation.evaluatescreens.evaluation.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class SaveEvaluateResponse(

	@Json(name="data")
	val data: Data1? = null,

	@Json(name="success")
	val success: Boolean? = null
) : Parcelable

@Parcelize
data class Data1(

	@Json(name="questionairre_participant_id")
	val questionairreParticipantId: Int? = null,

	@Json(name="number_value")
	val numberValue:@RawValue Any? = null,

	@Json(name="questionnaire_id")
	val questionnaireId: Int? = null,

	@Json(name="date_value")
	val dateValue:@RawValue Any? = null,

	@Json(name="question_id")
	val questionId: Int? = null,

	@Json(name="choice_id")
	val choiceId: Int? = null,

	@Json(name="time_taken")
	val timeTaken:@RawValue Any? = null,

	@Json(name="text_value")
	val textValue:@RawValue Any? = null,

	@Json(name="user_role_id")
	val userRoleId: Int? = null,

	@Json(name="createdAt")
	val createdAt: String? = null,

	@Json(name="comment")
	val comment: @RawValue Any? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="position")
	val position:@RawValue Any? = null,

	@Json(name="updatedAt")
	val updatedAt: String? = null
) : Parcelable
