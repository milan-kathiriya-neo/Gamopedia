package com.example.game.ui.di

import com.example.game.ui.game.GameViewModel
import com.example.game.ui.gameDetails.GameDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getGameUiModule() = module {
    viewModel { GameViewModel(getGamesUseCase = get()) }
    viewModel { GameDetailsViewModel(gameDetailsUseCase = get()) }
}