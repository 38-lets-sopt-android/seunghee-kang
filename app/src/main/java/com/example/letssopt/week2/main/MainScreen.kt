package com.example.letssopt.week2.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.week2.home.HomeScreen

@Composable
fun MainScreen(
    // 💡 1. MainViewModel 주입
    mainViewModel: MainViewModel = viewModel()
) {
    // 💡 2. 뷰모델의 상태(selectedTab)를 관찰하여 UI 업데이트
    val selectedTab by mainViewModel.selectedTab.collectAsState()

    val tabs = listOf(
        "메인" to R.drawable.ic_main,
        "개별 구매" to R.drawable.ic_category,
        "웹툰" to R.drawable.ic_wallet,
        "찾기" to R.drawable.ic_search,
        "보관함" to R.drawable.ic_folder
    )

    Scaffold(
        bottomBar = {
            // 하단 내비게이션 바 구현
            NavigationBar(
                containerColor = Color(0xFF141414),
                tonalElevation = 0.dp
            ) {
                tabs.forEach { (title, iconRes) ->
                    val isSelected = selectedTab == title

                    NavigationBarItem(
                        selected = isSelected,
                        // 💡 3. 클릭 시 뷰모델의 함수를 호출하여 상태 변경
                        onClick = { mainViewModel.updateTab(title) },
                        label = {
                            Text(
                                text = title,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.pretendard_regular))
                                )
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = iconRes),
                                contentDescription = title,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            unselectedIconColor = Color(0xFF707070),
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color(0xFF707070),
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFF141414))
        ) {
            // 💡 4. 뷰모델의 상태값에 따라 화면 분기
            when (selectedTab) {
                "메인" -> HomeScreen()
                else -> Text(
                    text = selectedTab,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}