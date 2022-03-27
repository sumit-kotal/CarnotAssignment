package com.example.carnotassignment.models

import com.google.gson.annotations.SerializedName
data class Records (

	@SerializedName("state") val state : String = "",
	@SerializedName("district") val district : String = "",
	@SerializedName("market") val market : String = "",
	@SerializedName("commodity") val commodity : String,
	@SerializedName("variety") val variety : String,
	@SerializedName("arrival_date") val arrivalDate : String,
	@SerializedName("min_price") val minPrice : Int,
	@SerializedName("max_price") val maxPrice : Int,
	@SerializedName("modal_price") val modalPrice : Int
)