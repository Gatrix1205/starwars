package com.example.starwarsgame.views.matchesscreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.starwarsgame.views.matchesscreen.view.composables.MatchTableItem

import com.example.starwarsgame.views.pointsscreen.viewmodel.PointsPageViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchScreen(
    viewModel: PointsPageViewModel,
    navController: NavHostController,
) {

    val playerId: Int = navController.currentBackStackEntry?.arguments?.getInt("id") ?: 0
    val playerDetails = rememberSaveable(playerId) {
        viewModel.getMatchDetails(playerId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Star Wars Tournament")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    paddingValues = innerPadding
                )
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color.Cyan)
                    .height(45.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Star War Blasters Tournament", modifier = Modifier.padding(
                        vertical = 12.dp,
                        horizontal = 10.dp
                    )
                )
            }
            LazyColumn {
                items(playerDetails.size) { index ->
                    MatchTableItem(playerDetails[index])
                }
            }
        }
    }
}