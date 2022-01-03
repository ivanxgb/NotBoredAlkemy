package com.example.notboredalkemy.ui.home

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _dataResponseValidate: MutableLiveData<Boolean> = MutableLiveData()
    val dataResponseValidate: LiveData<Boolean> get() = _dataResponseValidate

    fun validateParticipants(participants: String){
        _dataResponseValidate.value = participants.isDigitsOnly()
    }
}


