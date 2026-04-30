package com.example.letssopt.week2.home.component.banner

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import com.example.letssopt.R
import kotlinx.collections.immutable.persistentListOf

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

@Preview(showBackground = true)
@Composable
fun HomeBannerSectionPreview() {
    HomeBannerSection(
        bannerImages = persistentListOf(
            R.drawable.img_banner1,
            R.drawable.img_banner2,
            R.drawable.img_banner3
        )
    )
}