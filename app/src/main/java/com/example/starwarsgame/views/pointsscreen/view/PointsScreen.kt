package com.example.starwarsgame.views.pointsscreen.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.starwarsgame.common.injections.ServiceInjections
import com.example.starwarsgame.common.navigations.NavigationItem
import com.example.starwarsgame.views.pointsscreen.view.composables.PointsTableItem
import com.example.starwarsgame.views.pointsscreen.viewmodel.HomePageState
import com.example.starwarsgame.views.pointsscreen.viewmodel.PointsPageViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PointsTable(
    pointsPageViewModel: PointsPageViewModel,
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Star Wars Tournament") }
            )
        }
    )
    { innerPadding ->

        when (val homePageState = pointsPageViewModel.homePageState.value) {

            is HomePageState.Loading -> {
                Text("Hello")
            }

            is HomePageState.Error -> {
                Text(homePageState.message)
            }

            else -> {
                val playerDetails = (homePageState as HomePageState.Success).data
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
                            PointsTableItem(
                                playerDetails[index].iconUrl,
                                playerDetails[index].playerName,
                                playerDetails[index].playerPoints,
                                onClick = {
                                    navHostController.navigate("${NavigationItem.Match.route}?id=${playerDetails[index].id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PointsTablePreview() {
    PointsTable(
        PointsPageViewModel(
            ServiceInjections.provideGamesDetailsServiceInstance()
        ),
        rememberNavController()
    )

}