package com.example.notboredalkemy.data.repository.category

import com.example.notboredalkemy.data.Result
import com.example.notboredalkemy.utils.Constants

class CategoryRepositoryImpl: CategoryRepository {

    override suspend fun getListOfCategories(): Result<Any> {
        val listOfCategories = mutableListOf<String>()
        listOfCategories.addAll(Constants.categoriesList)
        return if (listOfCategories.isNotEmpty()) {
            Result.success(listOfCategories)
        } else {
            Result.error("No categories available")
        }
    }
}