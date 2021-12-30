package com.example.notboredalkemy.di

import com.example.notboredalkemy.ui.category.CategoryViewModel
import com.example.notboredalkemy.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ HomeViewModel()}
    viewModel{ CategoryViewModel( get()) }
}