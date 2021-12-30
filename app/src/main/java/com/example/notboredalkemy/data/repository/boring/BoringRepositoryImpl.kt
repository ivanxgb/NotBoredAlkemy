package com.example.notboredalkemy.data.repository.boring

import com.example.notboredalkemy.data.Result
import com.example.notboredalkemy.data.model.Response
import com.example.notboredalkemy.data.remote.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback


@ExperimentalCoroutinesApi
class BoringRepositoryImpl(
    private val apiService: ApiService
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
}
