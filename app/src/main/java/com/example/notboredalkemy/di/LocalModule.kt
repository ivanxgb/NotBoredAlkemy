package com.example.notboredalkemy.di

import com.example.notboredalkemy.data.local.SharedPreferenceHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {
    single { SharedPreferenceHelper(androidApplication()) }
}
