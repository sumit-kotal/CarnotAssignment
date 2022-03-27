package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.carnotassignment.api.ApiServiceImp
import com.example.carnotassignment.models.Records
import com.example.carnotassignment.models.ResponseData
import com.example.carnotassignment.repository.MainRepository
import com.example.carnotassignment.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {


    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var mainViewModel: MainViewModel
    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: ApiServiceImp

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainRepository = MainRepository(apiService)
        mainViewModel = MainViewModel(mainRepository)
    }

    @OptIn(ObsoleteCoroutinesApi::class, kotlinx.coroutines.FlowPreview::class)
    @Test
    fun getAllMoviesTest() {
        runBlocking {
            Mockito.`when`(mainRepository.getList(0,100))
            mainViewModel.getResults(0,100)
            val result = mainViewModel.dataResponse
            assertEquals(ResponseData(records = listOf<Records>()), result)
        }
    }


    @Test
    fun `empty list test`() {
        runBlocking {
            Mockito.`when`(mainRepository.getList(0,100))
            mainViewModel.getResults(0,100)
            val result = mainViewModel.dataResponse
            assertEquals(ResponseData(), result)
        }
    }

}