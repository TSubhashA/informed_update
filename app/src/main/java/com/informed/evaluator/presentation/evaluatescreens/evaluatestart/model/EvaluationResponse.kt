package com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class EvaluationResponse(

	@Json(name="data")
	val data: Data? = null,

	@Json(name="success")
	val success: Boolean? = null
) : Parcelable

@Parcelize
data class Questionnaire(

	@Json(name="question_count")
	val questionCount: Int? = null,

	@Json(name="evaluation_id")
	val evaluationId: Int? = null,

	@Json(name="createdAt")
	val createdAt: String? = null,

	@Json(name="image_url")
	val imageUrl: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="questions")
	val questions: List<QuestionsItem?>? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="status")
	val status: String? = null,

	@Json(name="updatedAt")
	val updatedAt: String? = null
) : Parcelable

@Parcelize
data class QuestionsItem(

	@Json(name="questionnaire_id")
	val questionnaireId: Int? = null,

	@Json(name="shape")
	val shape: @RawValue Any? = null,

	@Json(name="avg_value")
	val avgValue: @RawValue Any? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="scale")
	val scale: Int? = null,

	@Json(name="media")
	val media: List<String?>? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="min_range")
	val minRange: @RawValue Any? = null,

	@Json(name="steps")
	val steps: @RawValue Any? = null,

	@Json(name="labels")
	val labels: @RawValue Any? = null,

	@Json(name="button")
	val button: @RawValue Any? = null,

	@Json(name="createdAt")
	val createdAt: String? = null,

	@Json(name="short_title")
	val shortTitle: @RawValue Any? = null,

	@Json(name="max_range")
	val maxRange: @RawValue Any? = null,

	@Json(name="flag_based_on")
	val flagBasedOn: String? = null,

	@Json(name="is_required")
	val isRequired: Boolean? = null,

	@Json(name="date_format")
	val dateFormat: @RawValue Any? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="position")
	val position: Int? = null,

	@Json(name="choices")
	val choices: List<ChoicesItem?>? = null,

	@Json(name="default_country")
	val defaultCountry: @RawValue Any? = null,

	@Json(name="updatedAt")
	val updatedAt: String? = null
) : Parcelable

@Parcelize
data class Data(

	@Json(name="count")
	val count: Int? = null,

	@Json(name="rows")
	val rows: List<RowsItem?>? = null
) : Parcelable

@Parcelize
data class RowsItem(

	@Json(name="questionnaire")
	val questionnaire: Questionnaire? = null,

	@Json(name="avg_value")
	val avgValue: @RawValue Any? = null,

	@Json(name="for_role")
	val forRole: String? = null,

	@Json(name="created_by")
	val createdBy: Int? = null,

	@Json(name="institution_id")
	val institutionId: Int? = null,

	@Json(name="createdAt")
	val createdAt: String? = null,

	@Json(name="deletedAt")
	val deletedAt: @RawValue Any? = null,

	@Json(name="category_id")
	val categoryId: Int? = null,

	@Json(name="target_number")
	val targetNumber: Int? = null,

	@Json(name="is_anonymous")
	val isAnonymous: Boolean? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="flag_type")
	val flagType: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="status")
	val status: String? = null,

	@Json(name="updatedAt")
	val updatedAt: String? = null
) : Parcelable

@Parcelize
data class ChoicesItem(

	@Json(name="createdAt")
	val createdAt: String? = null,

	@Json(name="weight")
	val weight: Int? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="label")
	val label: String? = null,

	@Json(name="position")
	val position: Int? = null,

	@Json(name="media")
	val media: String? = null,

	@Json(name="question_id")
	val questionId: Int? = null,

	@Json(name="updatedAt")
	val updatedAt: String? = null
) : Parcelable
