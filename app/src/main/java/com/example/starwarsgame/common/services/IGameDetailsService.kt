package com.example.starwarsgame.common.services

import com.example.starwarsgame.common.models.MatchesDataModel
import com.example.starwarsgame.common.models.PlayerDetailsModel
import retrofit2.http.GET

interface IGameDetailsService {
    @GET("JNYL")
    suspend fun getMatches(): MatchesDataModel

    @GET("IKQQ")
    suspend fun getPoints(): List<PlayerDetailsModel>

}