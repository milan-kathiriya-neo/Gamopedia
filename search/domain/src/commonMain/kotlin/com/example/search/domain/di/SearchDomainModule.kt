package com.example.search.domain.di

import com.example.search.domain.useCases.SearchGameUseCase
import org.koin.dsl.module

fun getSearchDomainModule() = module {
    factory { SearchGameUseCase(searchRepository = get()) }
}