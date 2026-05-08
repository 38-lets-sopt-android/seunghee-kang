package com.example.letssopt.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.common.util.UiState
import com.example.letssopt.core.component.SignTextField
import com.example.letssopt.core.component.SignTitle
import com.example.letssopt.core.component.WatchaLogo
import com.example.letssopt.feature.login.LoginViewModel

@Composable
fun LoginScreen(
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    onShowToast: (String) -> Unit,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel(),
) {
    val loginId by loginViewModel.loginId.collectAsStateWithLifecycle()
    val password by loginViewModel.password.collectAsStateWithLifecycle()
    val loginState by loginViewModel.loginState.collectAsStateWithLifecycle()

    // 서버 응답 상태 관찰 (UI에는 안 보임!)
    LaunchedEffect(loginState) {
        when (loginState) {
            is UiState.Success -> {
                onShowToast("✅ 로그인 성공!")
                onLoginSuccess()
            }
            is UiState.Error -> {
                onShowToast("❌ ${(loginState as UiState.Error).message}")
            }
            else -> Unit
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(60.dp)) // 상단 여백 유지
        WatchaLogo()
        Spacer(modifier = Modifier.height(26.dp))
        SignTitle(text = "아이디로 로그인", modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(36.dp))

        SignTextField(
            label = "아이디",
            value = loginId,
            onValueChange = { loginViewModel.onLoginIdChanged(it) },
            placeholder = "아이디를 입력하세요"
        )

        SignTextField(
            label = "비밀번호",
            value = password,
            onValueChange = { loginViewModel.onPasswordChanged(it) },
            placeholder = "비밀번호를 입력하세요",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.weight(1f)) // [원본 복구] 아래로 밀어내기

        Text(
            text = "아직 계정이 없으신가요? 회원가입",
            color = Color(0xFF999999),
            fontSize = 14.sp,
            modifier = Modifier.clickable { onSignUpClick() }
        )

        Spacer(modifier = Modifier.height(20.dp)) // [원본 복구] 텍스트-버튼 사이 간격

        Button(
            onClick = { loginViewModel.login() }, // 로직만 서버로!
            modifier = Modifier
                .padding(bottom = 26.dp)
                .fillMaxWidth()
                .height(52.dp),
            enabled = loginViewModel.isLoginEnabled(), // 원본 조건 유지
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE8003C), // 왓챠 핑크
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF333333),
                disabledContentColor = Color(0xFF666666)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "로그인", // [원본 복구] 텍스트 고정
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        onSignUpClick = {},
        onLoginSuccess = {},
        onShowToast = {}
    )
}