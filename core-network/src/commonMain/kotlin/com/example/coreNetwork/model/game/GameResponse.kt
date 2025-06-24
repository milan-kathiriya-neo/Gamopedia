package com.example.coreNetwork.model.game

import kotlinx.serialization.Serializable

@Serializable
data class GameResponse(
    val results: List<Result> = emptyList(),
)

@Serializable
data class Result(
    val id: Int,
    val background_image: String,
    val name: String,
)