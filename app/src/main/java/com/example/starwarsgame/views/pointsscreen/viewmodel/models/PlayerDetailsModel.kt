package com.example.starwarsgame.views.pointsscreen.viewmodel.models

import androidx.compose.ui.graphics.Color

data class PlayerDataModel(
    val id: Int,
    var iconUrl: String,
    var playerName: String,
    var playerPoints : Int,
    var cardColor : Color
)