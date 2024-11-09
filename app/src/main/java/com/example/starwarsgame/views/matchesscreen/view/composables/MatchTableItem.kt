package com.example.starwarsgame.views.matchesscreen.view.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starwarsgame.views.pointsscreen.viewmodel.GameStatus
import com.example.starwarsgame.views.pointsscreen.viewmodel.models.MatchDetailsDataModel

@Composable
fun MatchTableItem(matchDataModel: MatchDetailsDataModel) {
    val (matchId, playerOneName, playerTwoName, matchScore, gameStatus) = matchDataModel
    val backgroundColor = when (gameStatus) {
        GameStatus.WIN -> Color.Green
        GameStatus.LOSS -> Color.Red
        GameStatus.DRAW -> Color.White
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp)
            .background(
                color = backgroundColor
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = playerOneName, fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = matchScore,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = playerTwoName, fontSize = 25.sp)
    }
}

@Composable
@Preview
fun MatchTableItemPreview() {
    MatchTableItem(
        MatchDetailsDataModel(
            matchId = 1,
            playerOneName = "Gowri",
            playerTwoName = "Shankar",
            matchScore = "89 - 90",
            gameStatus = GameStatus.WIN
        )
    )
}