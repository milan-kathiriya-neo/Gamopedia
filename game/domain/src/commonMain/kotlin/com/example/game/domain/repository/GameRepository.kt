package com.example.game.domain.repository

import com.example.common.domain.model.Game
import com.example.game.domain.model.GameDetails

interface GameRepository {
    suspend fun getGames(): Result<List<Game>>
    suspend fun getDetails(id:Int): Result<GameDetails>
}