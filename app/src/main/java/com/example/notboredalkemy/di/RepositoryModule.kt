package com.example.notboredalkemy.di

import com.example.notboredalkemy.data.repository.boring.BoringRepository
import com.example.notboredalkemy.data.repository.boring.BoringRepositoryImpl
import com.example.notboredalkemy.data.repository.category.CategoryRepository
import com.example.notboredalkemy.data.repository.category.CategoryRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val repositoryModule =  module{
    single<CategoryRepository> { CategoryRepositoryImpl(get(), get()) }
    single<BoringRepository> { BoringRepositoryImpl(get(), get()) }
}