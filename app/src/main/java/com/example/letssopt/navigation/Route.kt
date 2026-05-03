package com.example.letssopt.navigation
sealed class Route(val route: String) {
    // 로그인 화면
    data object Login : Route("login")

    // 회원가입 화면
    data object SignUp : Route("signup")

    // 메인(홈) 화면
    data object Main : Route("main")

    data object Library : Route("library")
}