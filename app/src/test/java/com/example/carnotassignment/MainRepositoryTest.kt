package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

import com.example.carnotassignment.api.ApiService
import com.example.carnotassignment.api.ApiServiceImp
import com.example.carnotassignment.models.Records
import com.example.carnotassignment.models.ResponseData
import com.example.carnotassignment.repository.MainRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class MainRepositoryTest {

    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: ApiServiceImp

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainRepository = MainRepository(apiService)
    }

    @Test
    fun `get all movie test`() {
        runBlocking {
            Mockito.`when`(apiService.getMainList(0,100))
            val response = mainRepository.getList(0,100)
            assertEquals(listOf<Records>(), response)
        }

    }

}