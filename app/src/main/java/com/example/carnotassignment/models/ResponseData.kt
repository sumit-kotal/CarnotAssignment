package com.example.carnotassignment.models

import com.google.gson.annotations.SerializedName

data class ResponseData (

    @SerializedName("created") val created : Int = 0,
    @SerializedName("updated") val updated : Int = 0,
    @SerializedName("created_date") val created_date : String = "",
    @SerializedName("updated_date") val updated_date : String = "",
    @SerializedName("active") val active : Int = 0,
    @SerializedName("index_name") val index_name : String = "",
    @SerializedName("org") val org : List<String> = listOf(),
    @SerializedName("org_type") val org_type : String = "",
    @SerializedName("source") val source : String = "",
    @SerializedName("title") val title : String = "",
    @SerializedName("external_ws_url") val external_ws_url : String = "",
    @SerializedName("visualizable") val visualizable : Int = 0,
    @SerializedName("field") val field : List<Field> = listOf(),
    @SerializedName("external_ws") val external_ws : Int = 0,
    @SerializedName("catalog_uuid") val catalog_uuid : String = "",
    @SerializedName("sector") val sector : List<String> = listOf(),
    @SerializedName("desc") val desc : String = "",
    @SerializedName("message") val message : String = "",
    @SerializedName("version") val version : String = "",
    @SerializedName("status") val status : String = "",
    @SerializedName("total") val total : Int = 0,
    @SerializedName("count") val count : Int = 0,
    @SerializedName("limit") val limit : Int = 0,
    @SerializedName("offset") val offset : Int = 0,
    @SerializedName("records") val records : List<Records> = listOf()
)