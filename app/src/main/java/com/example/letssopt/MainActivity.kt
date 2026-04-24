package com.example.letssopt

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.letssopt.ui.theme.LETSSOPTTheme
import com.example.letssopt.week1.LoginScreen
import com.example.letssopt.week1.SignUpActivity
import com.example.letssopt.week2.data.PreferenceManager
import com.example.letssopt.week2.main.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. 자동 로그인 관리자 생성
        val prefManager = PreferenceManager(this)

        setContent {
            LETSSOPTTheme {
                // 2. 자동 로그인 여부에 따라 시작 화면 결정 (기억된 값이 true면 바로 main)
                var currentScreen by remember {
                    mutableStateOf(if (prefManager.getAutoLogin()) "main" else "login")
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF141414) // 왓챠 다크 배경색
                ) {
                    when (currentScreen) {
                        "login" -> {
                            // 에러 해결: LoginScreen에 필요한 4가지 인자를 모두 전달
                            LoginScreen(
                                registeredEmail = "", // 초기값
                                registeredPw = "",    // 초기값
                                onSignUpClick = {
                                    // 회원가입 화면으로 이동
                                    val intent = Intent(this@MainActivity, SignUpActivity::class.java)
                                    startActivity(intent)
                                },
                                onLoginClick = { email, password ->
                                    // 로그인 성공 시 로직 (여기서는 입력값이 있는지만 확인)
                                    if (email.isNotEmpty() && password.isNotEmpty()) {
                                        // 💡 자동 로그인 데이터 저장
                                        prefManager.setAutoLogin(true)
                                        // 💡 메인 화면으로 전환
                                        currentScreen = "main"
                                    }
                                }
                            )
                        }
                        "main" -> {
                            // 3. 우리가 만든 week2의 메인 화면 (BottomNav 포함)
                            MainScreen()
                        }
                    }
                }
            }
        }
    }
}