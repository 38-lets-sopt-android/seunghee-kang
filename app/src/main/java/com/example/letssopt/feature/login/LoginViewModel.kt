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

    // [체크] SignInResponse의 userId가 Int이므로 <Int> 유지가 정석입니다! ✨
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
                // [수정] response.body()?.data는 LoginData 객체이므로 그 안의 userId를 꺼냅니다!
                val userId = response.body()?.data?.userId ?: -1L
                _loginState.value = UiState.Success(userId)
            } else {
                // 실습 포인트: 서버가 주는 에러 메시지 그대로 활용
                val errorMsg = response.errorBody()?.string() ?: "로그인 실패"
                _loginState.value = UiState.Error(errorMsg)
            }
        }.onFailure { t ->
            _loginState.value = UiState.Error(t.message ?: "네트워크 오류")
        }
    }

    // [명세 반영] Swagger에 적힌 아이디(4~20), 비번(8~20) 조건을 정확히 반영했습니다!
    fun isLoginEnabled(): Boolean {
        val isIdValid = _loginId.value.length in 4..20
        val isPwValid = _password.value.length in 8..20

        return isIdValid && isPwValid
    }
}