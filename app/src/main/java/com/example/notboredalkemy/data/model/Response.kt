package com.example.notboredalkemy.data.model

data class Response(
    val activity: String,
    val accessibility: Float,
    val type: String,
    val participants: Int,
    val price: Float,
    val link: String,
    val key: Long
)
