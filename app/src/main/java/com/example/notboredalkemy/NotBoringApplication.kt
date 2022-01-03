package com.example.notboredalkemy

import android.app.Application
import com.example.notboredalkemy.di.localModule
import com.example.notboredalkemy.di.networkModule
import com.example.notboredalkemy.di.repositoryModule
import com.example.notboredalkemy.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NotBoringApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NotBoringApplication)
            modules(arrayListOf(networkModule, viewModelModule, repositoryModule, localModule))
        }
    }
}