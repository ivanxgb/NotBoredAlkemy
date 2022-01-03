package com.example.notboredalkemy.data.repository.boring

import com.example.notboredalkemy.data.Result
import com.example.notboredalkemy.data.local.SharedPreferenceHelper
import com.example.notboredalkemy.data.model.Response
import com.example.notboredalkemy.data.remote.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback


@ExperimentalCoroutinesApi
class BoringRepositoryImpl(
    private val apiService: ApiService,
    private val preferencesHelper: SharedPreferenceHelper
) : BoringRepository {

    override suspend fun getActivityType(type: String, participants: Int): Result<Any> =
        suspendCancellableCoroutine { cancellableContinuation ->
            val call: Call<Response> = apiService.getActivities(type, participants)
            call.enqueue(object : Callback<Response> {
                override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                    if (response.isSuccessful) {
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

    override suspend fun getActivityByPriceRange(type: String, participants: Int, minPrice: Double, maxPrice: Double): Result<Any> =
        suspendCancellableCoroutine { cancellableContinuation ->
            val call: Call<Response> = apiService.getActivitiesByPriceRange(type, participants, minPrice, maxPrice)
            call.enqueue(object : Callback<Response> {
                override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                    if (response.isSuccessful) {
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

    override suspend fun getRandomActivity(): Result<Any> =
        suspendCancellableCoroutine { cancellableContinuation ->
            cancellableContinuation.resume(Result.success(preferencesHelper.getCategory()), null)
    }


    override suspend fun getRandomActivityPrice(minPrice: Double, maxPrice: Double): Result<Any> =
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

