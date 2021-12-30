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

    fun getActivities(type: String, participants: Int){
        viewModelScope.launch {
            val response = repository.getActivityType(type, participants)
            when(response.status){
                Result.Status.SUCCESS -> {
                    _dataResponseActivities.value = Pair(true, response.data)
                }
                Result.Status.ERROR -> {
                    _dataResponseActivities.value = Pair(false, response.data)
                }
            }
        }
    }
}
