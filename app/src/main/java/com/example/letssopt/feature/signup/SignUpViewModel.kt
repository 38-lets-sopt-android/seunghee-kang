package com.example.letssopt.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.common.util.UiState
import com.example.letssopt.core.data.dto.request.SignUpRequest
import com.example.letssopt.core.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _loginId = MutableStateFlow("")
    val loginId: StateFlow<String> = _loginId.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _passwordConfirm = MutableStateFlow("")
    val passwordConfirm: StateFlow<String> = _passwordConfirm.asStateFlow()

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _age = MutableStateFlow("")
    val age: StateFlow<String> = _age.asStateFlow()

    private val _part = MutableStateFlow("")
    val part: StateFlow<String> = _part.asStateFlow()

    private val _signUpState = MutableStateFlow<UiState<Int>>(UiState.Idle)
    val signUpState: StateFlow<UiState<Int>> = _signUpState.asStateFlow()

    fun onLoginIdChanged(id: String) {
        _loginId.value = id
    }

    fun onPasswordChanged(pw: String) {
        _password.value = pw
    }

    fun onPasswordConfirmChanged(pw: String) {
        _passwordConfirm.value = pw
    }

    fun onNameChanged(name: String) {
        _name.value = name
    }

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onAgeChanged(age: String) {
        _age.value = age
    }

    fun onPartChanged(part: String) {
        _part.value = part
    }

    fun signUp() = viewModelScope.launch {
        _signUpState.value = UiState.Loading

        runCatching {
            RetrofitClient.authService.signUp(
                SignUpRequest(
                    loginId = _loginId.value,
                    password = _password.value,
                    name = _name.value,
                    email = _email.value,
                    age = _age.value.toIntOrNull() ?: 0,
                    part = _part.value
                )
            )
        }.onSuccess { response ->
            if (response.isSuccessful) {
                _signUpState.value = UiState.Success(response.body()?.data ?: -1)
            } else {
                val errorMsg = response.body()?.message ?: "회원가입 실패"
                _signUpState.value = UiState.Error(errorMsg)
            }
        }.onFailure { t ->
            _signUpState.value = UiState.Error(t.message ?: "네트워크 오류")
        }
    }

    fun isSignUpEnabled(): Boolean {
        val isIdValid = _loginId.value.length in 4..20
        val isPwValid = _password.value.length in 8..20
        val isPwConfirmValid = _password.value == _passwordConfirm.value
        val isNameValid = _name.value.length in 1..10
        val isEmailValid = _email.value.contains("@") && _email.value.isNotBlank()
        val ageInt = _age.value.toIntOrNull() ?: 0
        val isAgeValid = ageInt in 1..150
        val isPartValid = _part.value.isNotBlank()

        return isIdValid &&
                isPwValid &&
                isPwConfirmValid &&
                isNameValid &&
                isEmailValid &&
                isAgeValid &&
                isPartValid
    }
}