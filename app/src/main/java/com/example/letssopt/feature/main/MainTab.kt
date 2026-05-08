package com.example.letssopt.feature.main

import androidx.annotation.DrawableRes
import com.example.letssopt.R

enum class MainTab(
    val label: String,
    @DrawableRes val iconRes: Int
) {
    HOME("메인", R.drawable.ic_main),
    CATEGORY("개별 구매", R.drawable.ic_category),
    WEBTOON("웹툰", R.drawable.ic_wallet),
    SEARCH("찾기", R.drawable.ic_search),
    LIBRARY("보관함", R.drawable.ic_folder)
}