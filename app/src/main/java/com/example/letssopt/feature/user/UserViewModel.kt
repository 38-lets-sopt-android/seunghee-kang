package com.example.letssopt.feature.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.common.util.UiState
import com.example.letssopt.core.data.dto.response.UserData
import com.example.letssopt.core.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _userState = MutableStateFlow<UiState<UserData>>(UiState.Idle)
    val userState: StateFlow<UiState<UserData>> = _userState.asStateFlow()

    fun loadUserInfo(userId: Long) = viewModelScope.launch {
        _userState.value = UiState.Loading

        runCatching {
            RetrofitClient.userService.getUserInfo(userId)
        }.onSuccess { response ->
            if (response.isSuccessful) {
                val userData = response.body()?.data
                if (userData != null) {
                    _userState.value = UiState.Success(userData)
                } else {
                    _userState.value = UiState.Error("데이터가 비어있습니다.")
                }
            } else {

                val message = response.body()?.message ?: "정보 조회에 실패했습니다"
                _userState.value = UiState.Error(message)
            }
        }.onFailure { e ->
            _userState.value = UiState.Error(e.message ?: "네트워크 오류가 발생했습니다")
        }
    }
}