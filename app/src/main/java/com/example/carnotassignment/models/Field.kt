package com.example.carnotassignment.models

import com.google.gson.annotations.SerializedName

data class Field (

	@SerializedName("name") val name : String,
	@SerializedName("id") val id : String,
	@SerializedName("type") val type : String
)