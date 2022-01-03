package com.example.notboredalkemy.data.model

data class Response(
    val activity: String,
    val accessibility: Float,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String,
    val key: Long,
    val error: String
)
