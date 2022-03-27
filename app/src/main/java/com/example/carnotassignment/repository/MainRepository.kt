package com.example.carnotassignment.repository

import android.app.Application
import androidx.core.net.toUri
import com.example.carnotassignment.api.ApiServiceImp
import com.example.carnotassignment.models.ResponseData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.HttpUrl.Companion.toHttpUrl
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainRepository @Inject constructor(
    private val apiServiceImp: ApiServiceImp
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun getList(offSet: Int,limit: Int): Flow<ResponseData> = flow {
        val response = apiServiceImp.getMainList(offSet,limit)
        emit(response)
    }.flowOn(Dispatchers.IO).conflate()

}
