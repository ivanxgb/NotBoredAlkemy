package com.example.notboredalkemy.data.repository.boring

import com.example.notboredalkemy.data.Result

interface BoringRepository {

    suspend fun getActivityType(type: String, participants: Int): Result<Any>
    suspend fun getActivityByPriceRange(type: String, participants: Int, minPrice: Double, maxPrice: Double ): Result<Any>
    suspend fun getRandomActivity(): Result<Any>
    suspend fun getRandomActivityService(): Result<Any>
}