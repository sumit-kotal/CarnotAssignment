package com.example.carnotassignment.api

import com.example.carnotassignment.models.ResponseData
import javax.inject.Inject

class ApiServiceImp @Inject constructor(private val apiService: ApiService) {
    suspend fun getMainList(offSet: Int,limit: Int): ResponseData =
        apiService.getList(offSet,limit)

}