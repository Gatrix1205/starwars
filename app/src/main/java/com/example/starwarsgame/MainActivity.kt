package com.example.starwarsgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.starwarsgame.common.injections.ServiceInjections
import com.example.starwarsgame.common.navigations.AppNavHost
import com.example.starwarsgame.ui.theme.StarWarsGameTheme
import com.example.starwarsgame.views.pointsscreen.viewmodel.PointsPageViewModel

class MainActivity : ComponentActivity() {
    private val pointsPageViewModel: PointsPageViewModel =
        PointsPageViewModel(ServiceInjections.provideGamesDetailsServiceInstance())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StarWarsGameTheme {
                AppNavHost(
                  pointsPageViewModel = pointsPageViewModel,
                    navController = rememberNavController()
                )
            }
        }
    }
}

