package com.example.notboredalkemy.ui.home

import android.widget.CheckBox
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var participantsValid = false
    private val _dataResponseValidate: MutableLiveData<Pair<Boolean, Any?>> = MutableLiveData()
    val dataResponseValid: LiveData<Pair<Boolean, Any?>> get() = _dataResponseValidate

    fun validateParticipantsAndTermsSelected(participants: String, checkBox: CheckBox){
        viewModelScope.launch {
        if(participants.isDigitsOnly() && checkBox.isSelected){
            participantsValid = true
            _dataResponseValidate.postValue(Pair(participantsValid, "Success"))
        }else{
        participantsValid = false
        _dataResponseValidate.postValue(Pair(participantsValid, "Error"))
            }
        }
    }
}
