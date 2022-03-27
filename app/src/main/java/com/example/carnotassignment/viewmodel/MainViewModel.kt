package com.example.carnotassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carnotassignment.models.ResponseData
import com.example.carnotassignment.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    val dataResponse: MutableLiveData<ResponseData> = MutableLiveData()

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getResults(offSet: Int,limit: Int) {
        viewModelScope.launch {
            mainRepository.getList(offSet,limit)
                .catch { e ->
                    Timber.d("$TAG -- ${e.message}")
                }.collect {
                    dataResponse.value = it
                }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}