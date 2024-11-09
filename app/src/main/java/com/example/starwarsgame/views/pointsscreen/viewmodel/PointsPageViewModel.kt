package com.example.starwarsgame.views.pointsscreen.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsgame.common.models.MatchesDataModel
import com.example.starwarsgame.common.models.MatchesDataModelItem
import com.example.starwarsgame.common.models.Player
import com.example.starwarsgame.common.services.IGameDetailsService
import com.example.starwarsgame.views.pointsscreen.viewmodel.models.MatchDetailsDataModel
import com.example.starwarsgame.views.pointsscreen.viewmodel.models.PlayerDataModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class HomePageStatus {
    SUCCESS, FAILURE
}

enum class GameStatus{
    WIN, LOSS, DRAW
}


class PointsPageViewModel
  @Inject constructor(private val gamesRepository: IGameDetailsService) :
    ViewModel() {

    private var _homePageState: MutableState<HomePageState> =
        mutableStateOf(HomePageState.Loading)
    val homePageState: MutableState<HomePageState> = _homePageState
    private  var _matchDetails : MatchesDataModel? = null
    private var _playerDetailsMap: MutableMap<Int, String> = mutableMapOf()


    init {
        loadPlayerData()
    }

    private fun loadPlayerData() {
        viewModelScope.launch {
            try {
                coroutineScope {
                    val playerDetails = async { gamesRepository.getPoints() }
                    val matchDetails  = async { gamesRepository.getMatches() }
                    val playerDetailsList = playerDetails.await()
                    _matchDetails = matchDetails.await()
                    val playerDetailsModelList: MutableList<PlayerDataModel> = mutableListOf()
                    for (player in playerDetailsList) {
                        val iconUrl =  StringBuilder(player.icon ?: "")
                        iconUrl.insert(4, 's')
                        playerDetailsModelList.add(
                            PlayerDataModel(
                                id = player.id ?: 0,
                                iconUrl = iconUrl.toString(),
                                playerName = player.name ?: "",
                                playerPoints = 0,
                                cardColor = Color.White
                            )
                        )
                        _playerDetailsMap[player.id ?: 0] = player.name ?: ""
                    }
                    var points = 0
                    for (player in playerDetailsModelList) {
                        for (match in _matchDetails ?: mutableListOf()) {
                            points += calculatePoints(getPlayerPoints(player.id, match))
                            player.playerPoints = points
                        }
                        points = 0
                    }
                    playerDetailsModelList.sortByDescending { it.playerPoints }
                    _homePageState.value = HomePageState.Success(
                        playerDetailsModelList,
                        HomePageStatus.SUCCESS
                    )

                }

            } catch (e: Exception) {
                _homePageState.value = HomePageState.Error(
                    e.message ?: "Something went wrong",
                    HomePageStatus.FAILURE
                )
                println(e.message)
            }
        }
    }


    private fun getPlayerPoints(id: Int, matchDataModelItem: MatchesDataModelItem): GameStatus {
        if (id != matchDataModelItem.player1.id && id != matchDataModelItem.player2.id) {
            return GameStatus.LOSS
        }
        val currPlayer: Player = if (id == matchDataModelItem.player1.id) {
            matchDataModelItem.player1
        } else {
            matchDataModelItem.player2
        }

        val opponentPlayer: Player = if (id == matchDataModelItem.player1.id) {
            matchDataModelItem.player2
        } else {
            matchDataModelItem.player1
        }

        if (currPlayer.score > opponentPlayer.score) {
            return GameStatus.WIN
        } else if (currPlayer.score == opponentPlayer.score) {
            return GameStatus.DRAW
        }
        return GameStatus.LOSS

    }

    private fun calculatePoints(gameStatus: GameStatus) :  Int{
        var playerPoints = 0
        when(gameStatus){
            GameStatus.WIN -> {
                playerPoints+=3
            }
            GameStatus.DRAW ->{
                playerPoints+=1
            }
            else ->{
                playerPoints = 0
            }
        }
        return playerPoints
    }

    fun getMatchDetails( id: Int): List<MatchDetailsDataModel>{
        val listItems: List<MatchesDataModelItem> = _matchDetails?.let {
            _matchDetails?.filter {
                it.player1.id == id || it.player2.id == id
            }
        } ?: mutableListOf()
        val matchDetailsModel = mutableListOf<MatchDetailsDataModel>()
        for (item in listItems){
            matchDetailsModel.add(
                MatchDetailsDataModel(
                    matchId = item.match,
                    playerOneName = _playerDetailsMap[item.player1.id] ?: "",
                    playerTwoName = _playerDetailsMap[item.player2.id] ?: "",
                    matchScore = "${item.player1.score} - ${item.player2.score}",
                    gameStatus = getPlayerPoints(id, item)
                )
            )

        }
        matchDetailsModel.sortByDescending { it.matchId }
        return matchDetailsModel
    }


}

sealed class HomePageState {
    data object Loading : HomePageState()
    data class Success(val data: List<PlayerDataModel>,
                       val homePageStatus: HomePageStatus) :
        HomePageState()
    data class Error(val message: String, val homePageStatus: HomePageStatus) : HomePageState()

}