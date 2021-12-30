package com.example.notboredalkemy.data.repository.boring

import com.example.notboredalkemy.data.Result

interface BoringRepository {

    suspend fun getActivityType(type: String, participants: Int): Result<Any>

}