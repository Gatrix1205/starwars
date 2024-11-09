package com.example.starwarsgame.common.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.starwarsgame.views.matchesscreen.view.MatchScreen
import com.example.starwarsgame.views.pointsscreen.view.PointsTable
import com.example.starwarsgame.views.pointsscreen.viewmodel.PointsPageViewModel


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Points.route,
    pointsPageViewModel: PointsPageViewModel
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            NavigationItem.Points.route,) {
           PointsTable(pointsPageViewModel, navController)
        }
        composable("${NavigationItem.Match.route}?id={id}",
            arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
            }
        )) {


            MatchScreen(pointsPageViewModel, navController)
        }
    }
}