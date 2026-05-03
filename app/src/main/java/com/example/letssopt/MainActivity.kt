package com.example.letssopt

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.letssopt.core.data.PreferenceManager
import com.example.letssopt.core.theme.LETSSOPTTheme
import com.example.letssopt.feature.login.LoginScreen
import com.example.letssopt.feature.login.LoginViewModel
import com.example.letssopt.feature.main.MainScreen
import com.example.letssopt.feature.signup.SignUpScreen
import com.example.letssopt.navigation.Route

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val prefManager = PreferenceManager(this)

        setContent {
            LETSSOPTTheme {
                val navController = rememberNavController()

                // 자동 로그인 여부에 따른 시작점 결정
                val startDestination = if (prefManager.getAutoLogin()) Route.Main.route else Route.Login.route

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF141414)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = startDestination
                    ) {
                        // 로그인 화면
                        composable(Route.Login.route) { backStackEntry ->
                            val loginViewModel: LoginViewModel = viewModel()

                            // SignUpScreen에서 넘겨준 데이터 수신
                            val regEmail = backStackEntry.savedStateHandle.getStateFlow("userEmail", "").collectAsStateWithLifecycle()
                            val regPw = backStackEntry.savedStateHandle.getStateFlow("userPassword", "").collectAsStateWithLifecycle()

                            LaunchedEffect(regEmail.value, regPw.value) {
                                if (regEmail.value.isNotEmpty()) {
                                    loginViewModel.setRegisteredInfo(regEmail.value, regPw.value)
                                }
                            }

                            LoginScreen(
                                onSignUpClick = { navController.navigate(Route.SignUp.route) },
                                onLoginSuccess = {
                                    prefManager.setAutoLogin(true) // 자동 로그인 저장
                                    navController.navigate(Route.Main.route) {
                                        popUpTo(Route.Login.route) { inclusive = true }
                                    }
                                },
                                onShowToast = { msg ->
                                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                                },
                                loginViewModel = loginViewModel
                            )
                        }

                        // 회원가입 화면
                        composable(Route.SignUp.route) {
                            SignUpScreen(
                                onSignUpSuccess = { email, password ->
                                    // 데이터 전달하며 뒤로가기
                                    navController.previousBackStackEntry?.savedStateHandle?.set("userEmail", email)
                                    navController.previousBackStackEntry?.savedStateHandle?.set("userPassword", password)
                                    navController.popBackStack()
                                }
                            )
                        }

                        // 메인 화면
                        composable(Route.Main.route) {
                            MainScreen()
                        }
                    }
                }
            }
        }
    }
}