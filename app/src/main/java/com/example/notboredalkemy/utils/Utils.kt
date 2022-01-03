package com.example.notboredalkemy.utils

object Utils {
    val low = 0.0 .. 0.3
    val medium = 0.3 .. 0.6
    val high = 0.6 .. 1.0
    val categoriesList = listOf("education", "recreational", "social", "diy",
        "charity", "cooking", "relaxation", "music", "busywork")
    var participants = 1
    var category: String = Constants.EMPTY
    var isCategorySelected: Boolean = false
    var isPriceSelected: Boolean = false
    var minPrice: Double = 0.0
    var maxPrice: Double = 1.0
}