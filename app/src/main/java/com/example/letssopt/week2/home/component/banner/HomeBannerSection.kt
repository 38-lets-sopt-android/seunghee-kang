package com.example.letssopt.week2.home.component.banner

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList

@Composable
fun HomeBannerSection(
    // ImmutableList 적용
    bannerImages: ImmutableList<Int>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { bannerImages.size }
    )

    // Column을 제거 -> HorizontalPager 최상위 배치
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 40.dp),
        pageSpacing = 16.dp,
        modifier = modifier.fillMaxWidth()
    ) { page ->
        HomeBannerItem(imageRes = bannerImages[page])
    }
}