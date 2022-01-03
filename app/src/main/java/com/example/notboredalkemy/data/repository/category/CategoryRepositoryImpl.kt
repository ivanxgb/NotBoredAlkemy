package com.example.notboredalkemy.data.repository.category

import com.example.notboredalkemy.data.Result
import com.example.notboredalkemy.data.local.SharedPreferenceHelper
import com.example.notboredalkemy.data.model.Response
import com.example.notboredalkemy.data.remote.ApiService
import com.example.notboredalkemy.utils.Utils
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback

class CategoryRepositoryImpl(
    private val apiService: ApiService,
    private val preferencesHelper: SharedPreferenceHelper
) : CategoryRepository {

    override suspend fun getListOfCategories(): Result<Any> {
        val listOfCategories = mutableListOf<String>()
        listOfCategories.addAll(Utils.categoriesList)
        return if (listOfCategories.isNotEmpty()) {
            Result.success(listOfCategories)
        } else {
            Result.error("No categories available")
        }
    }

    override suspend fun getRandomCategory(): Result<Any> =
        suspendCancellableCoroutine { cancellableContinuation ->
            val call: Call<Response> = apiService.getRandomActivity()
            call.enqueue(object : Callback<Response> {
                override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>, ) {
                    if (response.isSuccessful) {
                        response.body()?.let { preferencesHelper.saveCategory(it) }
                        cancellableContinuation.resume(Result.success(response.body()), null)
                    } else {
                        cancellableContinuation.resume(Result.error(response.errorBody()), null)
                    }
                }
                override fun onFailure(call: Call<Response>, t: Throwable) {
                    cancellableContinuation.resume(Result.error(t.message), null)
                }
            })
        }

    override suspend fun getRandomByPrice(minPrice: Double, maxPrice: Double): Result<Any> =
        suspendCancellableCoroutine { cancellableContinuation ->
            val call: Call<Response> = apiService.getRandomByPriceRange(minPrice, maxPrice)
            call.enqueue(object : Callback<Response> {
                override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>, ) {
                    if (response.isSuccessful) {
                        response.body()?.let { preferencesHelper.saveCategory(it) }
                        cancellableContinuation.resume(Result.success(response.body()), null)
                    } else {
                        cancellableContinuation.resume(Result.error(response.errorBody()), null)
                    }
                }
                override fun onFailure(call: Call<Response>, t: Throwable) {
                    cancellableContinuation.resume(Result.error(t.message), null)
                }
            })
        }
}