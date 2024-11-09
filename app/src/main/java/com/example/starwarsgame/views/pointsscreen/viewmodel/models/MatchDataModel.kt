package com.example.starwarsgame.views.pointsscreen.viewmodel.models


import com.example.starwarsgame.views.pointsscreen.viewmodel.GameStatus


data class MatchDetailsDataModel(
    val matchId: Int,
    val playerOneName: String,
    val playerTwoName: String,
    val matchScore: String,
    val gameStatus: GameStatus
)