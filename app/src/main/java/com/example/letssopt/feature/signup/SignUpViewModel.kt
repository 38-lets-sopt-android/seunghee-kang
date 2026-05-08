package com.example.letssopt.feature.signup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel : ViewModel() {
    // StateFlow로 변경
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _passwordConfirm = MutableStateFlow("")
    val passwordConfirm: StateFlow<String> = _passwordConfirm.asStateFlow()

    // 값 변경 함수 수정 (.value 사용)
    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun onPasswordConfirmChanged(newPasswordConfirm: String) {
        _passwordConfirm.value = newPasswordConfirm
    }

    // StateFlow의 현재 값을 참조
    val isEmailValid: Boolean
        get() = Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()

    val isPasswordValid: Boolean
        get() = _password.value.length in 8..12

    val isPasswordMatching: Boolean
        get() = _password.value == _passwordConfirm.value && _password.value.isNotEmpty()

    val isAllFieldsFilled: Boolean
        get() = _email.value.isNotEmpty() && _password.value.isNotEmpty() && _passwordConfirm.value.isNotEmpty()
}