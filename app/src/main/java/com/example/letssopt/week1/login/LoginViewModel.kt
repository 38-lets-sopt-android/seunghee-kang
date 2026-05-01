package com.example.letssopt.week1.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    // 사용자가 입력 중인 이메일과 비밀번호 상태
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    // 입력값 변경 시 호출
    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChanged(newPw: String) {
        _password.value = newPw
    }

    // 버튼 활성화 여부 비즈니스 로직
    fun isLoginEnabled(email: String, pw: String): Boolean {
        return email.isNotEmpty() && pw.isNotEmpty()
    }
}