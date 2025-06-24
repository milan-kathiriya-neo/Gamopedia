package com.example.game.domain.useCases

import com.example.common.domain.model.Game
import com.example.game.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetGamesUseCase(val gameRepository: GameRepository) {
    operator fun invoke() = flow<Result<List<Game>>> {
        emit(gameRepository.getGames())
    }.catch { error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.IO)
}