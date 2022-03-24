package com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class TraineeListResp(

	@Json(name="data")
	val data: Data? = null,

	@Json(name="success")
	val success: Boolean? = null
) : Parcelable

@Parcelize
data class UserGroupsItem(

	@Json(name="id")
	val id: Int? = null,

	@Json(name="group")
	val group: Group? = null
) : Parcelable

@Parcelize
data class Data(

	@Json(name="count")
	val count: Int? = null,

	@Json(name="rows")
	val rows: List<RowsItem?>? = null
) : Parcelable

@Parcelize
data class RolesItem(

	@Json(name="year")
	val year: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="user_groups")
	val userGroups: List<UserGroupsItem?>? = null
) : Parcelable

@Parcelize
data class Group(

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null
) : Parcelable

@Parcelize
data class RowsItem(

	@Json(name="image_url")
	val imageUrl: String? = null,

	@Json(name="roles")
	val roles: List<RolesItem?>? = null,

	@Json(name="last_name")
	val lastName: String? = null,

	@Json(name="otp")
	val otp: @RawValue Any? = null,

	@Json(name="createdAt")
	val createdAt: String? = null,

	@Json(name="deletedAt")
	val deletedAt: @RawValue Any? = null,

	@Json(name="phone")
	val phone: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="activated_on")
	val activatedOn: String? = null,

	@Json(name="first_name")
	val firstName: String? = null,

	@Json(name="email")
	val email: String? = null,

	@Json(name="status")
	val status: String? = null,

	@Json(name="updatedAt")
	val updatedAt: String? = null
) : Parcelable
