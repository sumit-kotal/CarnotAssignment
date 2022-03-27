package com.example.carnotassignment.api


import com.example.carnotassignment.models.ResponseData
import com.example.carnotassignment.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("9ef84268-d588-465a-a308-a864a43d0070")
    suspend fun getList(
        @Query("offset") offSet: Int,
        @Query("limit") limit: Int,
        @Query("api-key") action: String = Constants.api_key,
        @Query("format") format: String = Constants.format,
        ): ResponseData


}