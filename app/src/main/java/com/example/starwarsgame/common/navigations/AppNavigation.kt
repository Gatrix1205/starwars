package com.example.starwarsgame.common.navigations

enum class Screen{
    PointsScreen,
    MatchScreen
}

sealed class NavigationItem(val route: String) {
    data object Points : NavigationItem(Screen.PointsScreen.name)
    data object Match : NavigationItem(Screen.MatchScreen.name)
}