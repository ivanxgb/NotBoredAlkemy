package com.example.notboredalkemy.data.remote

import com.example.notboredalkemy.data.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("activity")
    fun getActivities(
            @Query("type", encoded = true) type: String,
            @Query("participants", encoded = true) participants: Int
    ): Call<Response>
}