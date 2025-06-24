package com.example.search.data.di

import com.example.search.data.repository.SearchRepositoryImpl
import com.example.search.domain.repository.SearchRepository
import org.koin.dsl.module

fun getSearchDataModule() = module{
    factory<SearchRepository> { SearchRepositoryImpl(apiService = get()) }
}