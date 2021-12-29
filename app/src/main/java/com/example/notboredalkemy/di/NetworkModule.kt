package com.example.notboredalkemy.di

import com.example.notboredalkemy.BuildConfig
import com.example.notboredalkemy.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL: String = ""

val networkModule = module {
    factory { provideInterceptor() }
    factory { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    factory { provideClientApi(get()) }
}

fun provideInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
    }
    return interceptor
}

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideClientApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
