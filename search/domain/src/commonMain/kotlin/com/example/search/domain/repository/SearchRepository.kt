package com.example.search.domain.repository

import com.example.common.domain.model.Game

interface SearchRepository {
    suspend fun search(q:String):Result<List<Game>>
}