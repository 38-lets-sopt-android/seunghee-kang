package com.example.letssopt.week2.home.model

import androidx.annotation.DrawableRes

data class PartyModel(
    val time: String,
    val title: String,
    @DrawableRes val image: Int
)