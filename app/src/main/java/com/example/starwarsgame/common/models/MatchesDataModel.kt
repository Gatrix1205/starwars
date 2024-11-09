package com.example.starwarsgame.common.models

class MatchesDataModel : ArrayList<MatchesDataModelItem>()

data class MatchesDataModelItem(
    val match: Int,
    val player1: Player,
    val player2: Player
)

data class Player(
    val id: Int,
    val score: Int
)