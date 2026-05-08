package com.example.letssopt.feature.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    // LoginActivity에 있던 registeredEmail, registeredPw
    private val _registeredEmail = MutableStateFlow("")
    val registeredEmail: StateFlow<String> = _registeredEmail.asStateFlow()

    private val _registeredPw = MutableStateFlow("")
    val registeredPw = _registeredPw.asStateFlow()

    // 입력값 변경 시 호출
    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChanged(newPw: String) {
        _password.value = newPw
    }

    // 회원가입 완료 후 데이터를 받아오는 함수
    fun setRegisteredInfo(email: String, pw: String) {
        _registeredEmail.value = email
        _registeredPw.value = pw
        // 정보가 들어오면 입력란에 자동으로 채워주기
        onEmailChanged(email)
        onPasswordChanged(pw)
    }

    // 버튼 활성화 여부
    fun isLoginEnabled(email: String, pw: String): Boolean {
        return email.isNotEmpty() && pw.isNotEmpty()
    }
}