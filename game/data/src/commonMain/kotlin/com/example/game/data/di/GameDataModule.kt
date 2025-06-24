package com.example.game.data.di

import com.example.game.data.repository.GameRepositoryImpl
import com.example.game.domain.repository.GameRepository
import org.koin.dsl.module

fun getGameDataModule() = module {
    factory<GameRepository> { GameRepositoryImpl(apiService = get()) }
}