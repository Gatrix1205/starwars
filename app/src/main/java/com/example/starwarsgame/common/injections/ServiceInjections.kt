package com.example.starwarsgame.common.injections

import com.example.starwarsgame.common.models.MatchesDataModel
import com.example.starwarsgame.common.models.PlayerDetailsModel
import com.example.starwarsgame.common.services.IGameDetailsService
import com.example.starwarsgame.common.services.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceInjections {

    @Provides
    @Singleton
    fun provideApiService(): Retrofit {
        return RetrofitBuilder.retrofit
    }

    @Provides
    @Singleton
     fun provideGamesDetailsServiceInstance() : IGameDetailsService{
        return provideApiService().create(IGameDetailsService::class.java)
    }

    @Provides
    @Singleton
    suspend fun provideGetpoints() : List<PlayerDetailsModel>{
         return provideGamesDetailsServiceInstance().getPoints()
    }

    @Provides
    @Singleton
    suspend fun provideGetMatches() : MatchesDataModel {
        return provideGamesDetailsServiceInstance().getMatches()
    }
}