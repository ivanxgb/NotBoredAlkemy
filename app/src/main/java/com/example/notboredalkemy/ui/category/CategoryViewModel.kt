package com.example.notboredalkemy.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notboredalkemy.data.Result
import com.example.notboredalkemy.data.repository.category.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: CategoryRepository,
) : ViewModel() {

    private val _dataResponseCategories: MutableLiveData<Pair<Boolean, Any?>> = MutableLiveData()
    val dataResponseCategories: LiveData<Pair<Boolean, Any?>> get() = _dataResponseCategories
    private val _dataResponseRandom: MutableLiveData<Pair<Boolean, Any?>> = MutableLiveData()
    val dataResponseRandom: LiveData<Pair<Boolean, Any?>> get() = _dataResponseRandom

    fun getCategories() {
        viewModelScope.launch {
            val response = repository.getListOfCategories()
            when (response.status) {
                Result.Status.SUCCESS -> _dataResponseCategories.value = Pair(true, response.data)
                Result.Status.ERROR -> _dataResponseCategories.value = Pair(false, "Error")
            }
        }
    }

    fun getRandomCategory() {
        viewModelScope.launch {
            val response = repository.getRandomCategory()
            when (response.status) {
                Result.Status.SUCCESS -> _dataResponseRandom.value = Pair(true, response.data)
                Result.Status.ERROR -> _dataResponseRandom.value = Pair(false, "Error")
            }
        }
    }

    fun getRandomCategoryByPrice(minPrice: Double, maxPrice: Double){
        viewModelScope.launch {
            val response = repository.getRandomByPrice(minPrice, maxPrice)
            when (response.status) {
                Result.Status.SUCCESS -> _dataResponseRandom.value = Pair(true, response.data)
                Result.Status.ERROR -> _dataResponseRandom.value = Pair(false, "Error")
            }
        }
    }
}