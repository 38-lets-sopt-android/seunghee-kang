package com.example.letssopt.feature.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.letssopt.R
import com.example.letssopt.feature.library.LibraryScreen
import com.example.letssopt.feature.catagory.CategoryScreen
import com.example.letssopt.feature.home.HomeScreen
import com.example.letssopt.feature.login.LoginScreen
import com.example.letssopt.feature.signup.SignUpScreen
import com.example.letssopt.navigation.Route
import com.example.letssopt.navigation.rememberAppState


@Composable
fun MainScreen() {

    val appState = rememberAppState()

    NavHost(
        navController = appState.navController,
        startDestination = appState.startDestination // "login"
    ) {
        // 로그인
        composable(Route.Login.route) {
            val context = LocalContext.current

            LoginScreen(
                onLoginSuccess = {
                    appState.navController.navigate(Route.Main.route) {
                        popUpTo(Route.Login.route) { inclusive = true }
                    }
                },
                onSignUpClick = { appState.navigateToSignUp() },

                onShowToast = { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            )
        }

        // 회원가입
        composable(Route.SignUp.route) {
            val context = LocalContext.current
            SignUpScreen(
                onShowToast = { message -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show() },
                onSignUpSuccess = { _, _ -> appState.navController.popBackStack() }
            )
        }

        // 메인 컨텐츠
        composable(Route.Main.route) {
            val mainViewModel: MainViewModel = viewModel()
            val selectedTab by mainViewModel.selectedTab.collectAsStateWithLifecycle()

            MainContent(
                selectedTab = selectedTab,
                onTabSelected = { mainViewModel.updateTab(it) }
            )
        }
    }
}

@Composable
private fun MainContent(
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF141414),
                tonalElevation = 0.dp
            ) {
                MainTab.entries.forEach { tab ->
                    val isSelected = (selectedTab == tab)

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { onTabSelected(tab) },
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
                MainTab.CATEGORY -> CategoryScreen() // 개별 구매 화면 연결
                MainTab.LIBRARY -> LibraryScreen()     // 보관함 화면 연결
                else -> Text(
                    text = selectedTab.label,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainContent(
        selectedTab = MainTab.HOME,
        onTabSelected = {}
    )
}