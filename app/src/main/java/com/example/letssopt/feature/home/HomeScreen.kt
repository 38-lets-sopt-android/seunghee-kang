package com.example.letssopt.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.feature.home.component.HomeSection
import com.example.letssopt.feature.home.component.HomeTopBar
import com.example.letssopt.feature.home.component.banner.HomeBannerSection
import com.example.letssopt.feature.home.component.content.HomeContentSection
import com.example.letssopt.feature.home.component.party.HomePartySection
import kotlinx.collections.immutable.persistentListOf

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    HomeContent(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
private fun HomeContent(
    uiState: HomeUiState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
    ) {
        item { HomeTopBar() }

        // 배너 섹션
        item {
            Column(modifier = Modifier.padding(top = 24.dp)) {
                HomeSection(
                    title = "방금 막 도착한 신상 컨텐츠",
                    subtitle = "예능부터 드라마까지!",
                    showMore = false
                )
                Spacer(modifier = Modifier.height(24.dp))
                HomeBannerSection(bannerImages = uiState.bannerImages)
            }
        }

        // 왓고리즘 섹션
        item {
            Column(modifier = Modifier.padding(top = 26.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.watgorism),
                    contentDescription = "왓고리즘",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .height(24.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "예능부터 드라마까지!",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF999999)
                        )
                    )
                    Text(
                        text = "더보기",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                            color = Color(0xFF999999)
                        )
                    )
                }
                HomeContentSection(contents = uiState.contentImages)
            }
        }

        // 공개 예정 섹션
        item {
            Column(modifier = Modifier.padding(top = 26.dp)) {
                HomeSection(title = "공개 예정 콘텐츠")
                Spacer(modifier = Modifier.height(8.dp))
                HomeContentSection(contents = uiState.contentImages)
            }
        }

        // 왓챠 파티 섹션
        item {
            Column(modifier = Modifier.padding(top = 26.dp)) {
                HomeSection(title = "왓챠 파티")
                Spacer(modifier = Modifier.height(8.dp))
                HomePartySection(parties = uiState.partyList)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeContent(
        uiState = HomeUiState(
            bannerImages = persistentListOf(R.drawable.img_banner1, R.drawable.img_banner2),
            contentImages = persistentListOf(R.drawable.img_content1, R.drawable.img_content2)
        )
    )
}