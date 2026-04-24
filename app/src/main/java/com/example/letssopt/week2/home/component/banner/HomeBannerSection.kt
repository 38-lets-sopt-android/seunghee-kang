package com.example.letssopt.week2.home.component.banner

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeBannerSection(bannerImages: List<Int>) {
    // 뷰모델에서 넘어온 데이터의 개수만큼 페이지 생성
    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { bannerImages.size }
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 40.dp),
            pageSpacing = 16.dp,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            // 리스트에서 실제 이미지 리소스를 꺼내서 전달
            HomeBannerItem(imageRes = bannerImages[page])
        }
    }
}