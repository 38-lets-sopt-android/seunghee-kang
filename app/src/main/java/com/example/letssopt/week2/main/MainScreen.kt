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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.week2.home.HomeScreen

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel()
) {
    val selectedTab by mainViewModel.selectedTab.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF141414),
                tonalElevation = 0.dp
            ) {
                // MainTab.entries를 사용하여 루프를 돕니다.
                MainTab.entries.forEach { tab ->
                    val isSelected = selectedTab == tab

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { mainViewModel.updateTab(tab) },
                        label = {
                            Text(
                                text = tab.label,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.pretendard_regular))
                                )
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = tab.iconRes),
                                contentDescription = tab.label,
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
            when (selectedTab) {
                MainTab.HOME -> HomeScreen()
                else -> Text(
                    text = selectedTab.label,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}