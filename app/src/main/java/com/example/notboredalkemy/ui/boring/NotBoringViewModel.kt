package com.example.notboredalkemy.ui.boring

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notboredalkemy.data.Result
import com.example.notboredalkemy.data.repository.boring.BoringRepository
import kotlinx.coroutines.launch

class NotBoringViewModel(
    private val repository: BoringRepository
) : ViewModel() {

    private val _dataResponseActivities: MutableLiveData<Pair<Boolean, Any?>> = MutableLiveData()
    val dataResponseActivities: LiveData<Pair<Boolean, Any?>> get() = _dataResponseActivities
    private val _dataResponseActivityPrice: MutableLiveData<Pair<Boolean, Any?>> = MutableLiveData()
    val dataResponseActivityPrice: LiveData<Pair<Boolean, Any?>> get() = _dataResponseActivityPrice
    private val _dataResponseRandomActivity: MutableLiveData<Pair<Boolean, Any?>> = MutableLiveData()
    val dataResponseRandomActivity: LiveData<Pair<Boolean, Any?>> get() = _dataResponseRandomActivity
    private val _dataResponseRandomActivityService: MutableLiveData<Pair<Boolean, Any?>> = MutableLiveData()
    val dataResponseRandomActivityService: LiveData<Pair<Boolean, Any?>> get() = _dataResponseRandomActivityService

    fun getActivities(type: String, participants: Int){
        viewModelScope.launch {
            val response = repository.getActivityType(type, participants)
            when(response.status){
                Result.Status.SUCCESS -> _dataResponseActivities.value = Pair(true, response.data)
                Result.Status.ERROR -> _dataResponseActivities.value = Pair(false, response.data)
            }
        }
    }

    fun getActivityByPriceRange(type: String, participants: Int, minPrice: Double, maxPrice: Double){
        viewModelScope.launch {
            val response = repository.getActivityByPriceRange(type, participants, minPrice, maxPrice)
            when(response.status){
                Result.Status.SUCCESS -> _dataResponseActivityPrice.value = Pair(true, response.data)
                Result.Status.ERROR -> _dataResponseActivityPrice.value = Pair(false, response.data)
            }
        }
    }

    fun getRandomActivity(){
        viewModelScope.launch {
            val response = repository.getRandomActivity()
            when(response.status){
                Result.Status.SUCCESS -> _dataResponseRandomActivity.value = Pair(true, response.data)
                Result.Status.ERROR -> _dataResponseRandomActivity.value = Pair(false, response.data)
            }
        }
    }

    fun getRandomActivityService(){
        viewModelScope.launch {
            val response = repository.getRandomActivityService()
            when(response.status){
                Result.Status.SUCCESS -> _dataResponseRandomActivityService.value = Pair(true, response.data)
                Result.Status.ERROR -> _dataResponseRandomActivityService.value = Pair(false, response.data)
            }
        }
    }
}
