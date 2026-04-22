package com.example.letssopt.week1

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordConfirm by mutableStateOf("")

    // [회원가입 성공조건]
    val isEmailValid: Boolean
        get() = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val isPasswordValid: Boolean
        get() = password.length in 8..12

    val isPasswordMatching: Boolean
        get() = password == passwordConfirm && password.isNotEmpty()

    val isAllFieldsFilled: Boolean
        get() = email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()

    fun onEmailChanged(newEmail: String) { email = newEmail }
    fun onPasswordChanged(newPassword: String) { password = newPassword }
    fun onPasswordConfirmChanged(newPasswordConfirm: String) { passwordConfirm = newPasswordConfirm }
}