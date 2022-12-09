package com.example.myrestaurantcompose.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseDetailRestaurant(

	@field:SerializedName("restaurant")
	val restaurant: Restaurant,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
) : Parcelable

@Parcelize
data class DrinksItem(

	@field:SerializedName("name")
	val name: String
) : Parcelable

@Parcelize
data class CustomerReviewsItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("review")
	val review: String,

	@field:SerializedName("name")
	val name: String
) : Parcelable

@Parcelize
data class Restaurant(

	@field:SerializedName("customerReviews")
	val customerReviews: List<CustomerReviewsItem>,

	@field:SerializedName("address")
	val address: String,

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
	val id: String,

	@field:SerializedName("categories")
	val categories: List<CategoriesItem>,

	@field:SerializedName("menus")
	val menus: Menus
) : Parcelable

@Parcelize
data class Menus(

	@field:SerializedName("foods")
	val foods: List<FoodsItem>,

	@field:SerializedName("drinks")
	val drinks: List<DrinksItem>
) : Parcelable

@Parcelize
data class CategoriesItem(

	@field:SerializedName("name")
	val name: String
) : Parcelable

@Parcelize
data class FoodsItem(

	@field:SerializedName("name")
	val name: String
) : Parcelable
