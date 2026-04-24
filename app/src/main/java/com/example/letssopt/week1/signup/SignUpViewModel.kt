package com.example.letssopt.week1.signup

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    // 💡 Compose의 State를 사용하여 변경 사항이 UI에 즉시 반영되도록 함
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordConfirm by mutableStateOf("")

    // [회원가입 성공조건 체크]
    val isEmailValid: Boolean
        get() = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val isPasswordValid: Boolean
        get() = password.length in 8..12

    val isPasswordMatching: Boolean
        get() = password == passwordConfirm && password.isNotEmpty()

    val isAllFieldsFilled: Boolean
        get() = email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()

    // 💡 값 변경 함수들
    fun onEmailChanged(newEmail: String) {
        email = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        password = newPassword
    }

    fun onPasswordConfirmChanged(newPasswordConfirm: String) {
        passwordConfirm = newPasswordConfirm
    }
}