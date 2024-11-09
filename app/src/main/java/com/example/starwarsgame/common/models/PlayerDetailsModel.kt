package com.example.starwarsgame.common.models


import kotlinx.serialization.SerialName

data class PlayerDetailsModel (

    @SerialName("id"   ) var id   : Int?    = null,
    @SerialName("name" ) var name : String? = null,
    @SerialName("icon" ) var icon : String? = null

)