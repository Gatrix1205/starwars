package com.example.starwarsgame.common.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.starwarsgame.BuildConfig

object RetrofitBuilder{
    val retrofit : Retrofit = Retrofit.Builder().baseUrl("https://www.jsonkeeper.com/b/").addConverterFactory(
        GsonConverterFactory.create()).build()
}