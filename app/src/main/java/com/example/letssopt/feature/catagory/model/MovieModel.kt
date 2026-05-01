package com.example.letssopt.feature.category.model

import androidx.annotation.DrawableRes

data class MovieModel(
    val id: Int,
    val title: String,
    val imageRes: Int,
    val isSaved: Boolean = false // 로컬 저장 여부
)