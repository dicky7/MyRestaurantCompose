package com.example.myrestaurantcompose.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseRestaurant(

	@field:SerializedName("count")
	val count: Int?,

	@field:SerializedName("founded")
	val founded: Int?,

	@field:SerializedName("restaurants")
	val restaurants: List<RestaurantsItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String?
) : Parcelable

@Parcelize
data class RestaurantsItem(

	@field:SerializedName("pictureId")
	val pictureId: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: String
) : Parcelable
