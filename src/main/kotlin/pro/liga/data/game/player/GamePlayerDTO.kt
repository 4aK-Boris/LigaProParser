package com.example.data.game.player

data class GamePlayerDTO(
    val playerId: Int,
    val gameId: Int,
    val score: Int?,
    val pointsScore: Int?,
    val submission: Float?,
    val submissionOther: Float?,
    val win: Boolean?,
    val type: Int,
    val winningGames: Int?,
    val losingGames: Int?,
    val playingType: Int?
)
