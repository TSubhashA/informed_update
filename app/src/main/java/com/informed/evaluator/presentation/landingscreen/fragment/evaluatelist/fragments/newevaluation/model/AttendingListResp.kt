package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.model

import com.squareup.moshi.Json

data class AttendingListResp(

	@Json(name="data")
	val data: Data? = null,

	@Json(name="success")
	val success: Boolean? = null
)

data class RolesItem(

	@Json(name="createdAt")
	val createdAt: String? = null,

	@Json(name="institution")
	val institution: Institution? = null,

	@Json(name="role")
	val role: String? = null,

	@Json(name="user_id")
	val userId: Int? = null,

	@Json(name="year")
	val year: Any? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="institution_id")
	val institutionId: Int? = null,

	@Json(name="is_contact")
	val isContact: Boolean? = null,

	@Json(name="status")
	val status: String? = null,

	@Json(name="updatedAt")
	val updatedAt: String? = null
)

data class Institution(

	@Json(name="name")
	val name: String? = null,

	@Json(name="about")
	val about: String? = null,

	@Json(name="logo")
	val logo: Any? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="plan")
	val plan: Any? = null
)

data class Data(

	@Json(name="count")
	val count: Int? = null,

	@Json(name="rows")
	val rows: List<RowsItem?>? = null
)

data class RowsItem(

	@Json(name="image_url")
	val imageUrl: Any? = null,

	@Json(name="roles")
	val roles: List<RolesItem?>? = null,

	@Json(name="last_name")
	val lastName: String? = null,

	@Json(name="otp")
	val otp: Any? = null,

	@Json(name="createdAt")
	val createdAt: String? = null,

	@Json(name="deletedAt")
	val deletedAt: Any? = null,

	@Json(name="phone")
	val phone: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="activated_on")
	val activatedOn: Any? = null,

	@Json(name="first_name")
	val firstName: String? = null,

	@Json(name="email")
	val email: String? = null,

	@Json(name="status")
	val status: String? = null,

	@Json(name="updatedAt")
	val updatedAt: String? = null
)
