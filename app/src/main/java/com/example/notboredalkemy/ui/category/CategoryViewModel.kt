package com.example.notboredalkemy.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CategoryViewModel: ViewModel() {

    private val _dataResponseValidate: MutableLiveData<Pair<Boolean, Any?>> = MutableLiveData()
    val dataResponseValid: LiveData<Pair<Boolean, Any?>> get() = _dataResponseValidate

    fun getCategories(){
        viewModelScope.launch {

        }
    }
}