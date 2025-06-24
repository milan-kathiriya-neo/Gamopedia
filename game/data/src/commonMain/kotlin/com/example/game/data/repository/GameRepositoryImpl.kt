package com.example.game.data.repository

import com.example.common.data.mappers.toDomainListOfGames
import com.example.common.domain.model.Game
import com.example.coreNetwork.apiService.ApiService
import com.example.game.data.mappers.toDomainGameDetails
import com.example.game.domain.model.GameDetails
import com.example.game.domain.repository.GameRepository

class GameRepositoryImpl(val apiService: ApiService) : GameRepository {
    override suspend fun getGames(): Result<List<Game>> {
        val result = apiService.getGames()
        return if (result.isSuccess) {
            Result.success(result.getOrThrow().results.toDomainListOfGames())
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }

    override suspend fun getDetails(id:Int): Result<GameDetails> {
        val result = apiService.getDetail(id)
        return  if (result.isSuccess){
            Result.success(result.getOrThrow().toDomainGameDetails())
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }
}