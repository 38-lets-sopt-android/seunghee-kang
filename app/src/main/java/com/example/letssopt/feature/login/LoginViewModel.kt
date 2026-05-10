package com.example.letssopt.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.common.util.UiState
import com.example.letssopt.core.data.dto.request.SignInRequest
import com.example.letssopt.core.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _loginId = MutableStateFlow("")
    val loginId: StateFlow<String> = _loginId.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _loginState = MutableStateFlow<UiState<Long>>(UiState.Idle)
    val loginState: StateFlow<UiState<Long>> = _loginState.asStateFlow()
    fun onLoginIdChanged(newId: String) {
        _loginId.value = newId
    }

    fun onPasswordChanged(newPw: String) {
        _password.value = newPw
    }

    fun login() = viewModelScope.launch {
        _loginState.value = UiState.Loading

        runCatching {
            RetrofitClient.authService.signIn(
                SignInRequest(
                    loginId = _loginId.value,
                    password = _password.value
                )
            )
        }.onSuccess { response ->
            if (response.isSuccessful) {
                val userId = response.body()?.data?.userId ?: -1L
                _loginState.value = UiState.Success(userId)
            } else {
                val errorMsg = response.body()?.message ?: "로그인 실패"
                _loginState.value = UiState.Error(errorMsg)
            }
        }.onFailure { t ->
            _loginState.value = UiState.Error(t.message ?: "네트워크 오류")
        }
    }

    fun isLoginEnabled(): Boolean {
        val isIdValid = _loginId.value.length in 4..20
        val isPwValid = _password.value.length in 8..20

        return isIdValid && isPwValid
    }
}