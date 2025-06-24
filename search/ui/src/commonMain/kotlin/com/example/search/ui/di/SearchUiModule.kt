package com.example.search.ui.di

import com.example.search.ui.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getSearchUiModule() = module {
    viewModel { SearchViewModel(searchGameUseCase = get()) }
}