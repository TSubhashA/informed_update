package com.informed.evaluator.presentation.evaluatescreens.evaluation.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class SaveEvaluateRequest(

	@Json(name="text_value")
	var textValue: @RawValue Any? = null,

	@Json(name="number_value")
	var numberValue: @RawValue Any? = null,

	@Json(name="date_value")
    var dateValue: @RawValue Any? = null,

	@Json(name="comment")
	var comment: @RawValue Any? = null,

	@Json(name="position")
	val position: @RawValue Any? = null,

	@Json(name="question_id")
    var questionId: Int? = null,

	@Json(name="choice_id")
	var choiceId: Int? = null,

	@Json(name="time_taken")
	val timeTaken: @RawValue Any? = null
) : Parcelable
