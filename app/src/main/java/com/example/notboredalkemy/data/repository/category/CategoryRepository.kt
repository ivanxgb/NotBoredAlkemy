package com.example.notboredalkemy.data.repository.category

import com.example.notboredalkemy.data.Result

interface CategoryRepository {

    suspend fun getListOfCategories(): Result<Any>
    suspend fun getRandomCategory(): Result<Any>
}