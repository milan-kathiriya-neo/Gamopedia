package com.example.search.data.repository

import com.example.common.data.mappers.toDomainListOfGames
import com.example.common.domain.model.Game
import com.example.coreNetwork.apiService.ApiService
import com.example.search.domain.repository.SearchRepository

class SearchRepositoryImpl(val apiService: ApiService) : SearchRepository {

    override suspend fun search(q: String): Result<List<Game>> {
        return try {
            val response = apiService.search(q)
            Result.success(response.getOrThrow().results.toDomainListOfGames())
        }catch (e:Exception){
            Result.failure(e)
        }
    }
}