package com.example.game.domain.di

import com.example.game.domain.useCases.GetGameDetailsUseCase
import com.example.game.domain.useCases.GetGamesUseCase
import org.koin.dsl.module

fun getGameDomainModule() = module {
    factory { GetGamesUseCase(gameRepository =  get()) }
    factory { GetGameDetailsUseCase(gameRepository =  get()) }
}