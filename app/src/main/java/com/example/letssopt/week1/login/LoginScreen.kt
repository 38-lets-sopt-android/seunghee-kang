package com.example.letssopt.week1

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.week1.component.SignTextField
import com.example.letssopt.week1.component.SignTitle
import com.example.letssopt.week1.component.WatchaLogo
import com.example.letssopt.week1.login.LoginViewModel

@Composable
fun LoginScreen(
    // 1. 필수 파라미터
    registeredEmail: String,
    registeredPw: String,
    onSignUpClick: () -> Unit,
    onLoginClick: (String, String) -> Unit,

    // 2. Modifier
    modifier: Modifier = Modifier,

    // 3. 선택적 파라미터
    loginViewModel: LoginViewModel = viewModel(),
) {
    val inputEmail by loginViewModel.email.collectAsStateWithLifecycle()
    val inputPassword by loginViewModel.password.collectAsStateWithLifecycle()

    // 회원가입에서 넘어온 데이터 자동 입력
    LaunchedEffect(registeredEmail) {
        if (registeredEmail.isNotEmpty()) loginViewModel.onEmailChanged(registeredEmail)
        if (registeredPw.isNotEmpty()) loginViewModel.onPasswordChanged(registeredPw)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        WatchaLogo()
        Spacer(modifier = Modifier.height(26.dp))
        SignTitle(text = "이메일로 로그인", modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(36.dp))

        SignTextField(
            label = "이메일",
            value = inputEmail,
            onValueChange = { loginViewModel.onEmailChanged(it) },
            placeholder = "이메일 주소를 입력하세요",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        SignTextField(
            label = "비밀번호",
            value = inputPassword,
            onValueChange = { loginViewModel.onPasswordChanged(it) },
            placeholder = "비밀번호를 입력하세요",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "아직 계정이 없으신가요? 회원가입",
            color = Color(0xFF999999),
            fontSize = 14.sp,
            modifier = Modifier.clickable { onSignUpClick() }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { onLoginClick(inputEmail, inputPassword) },
            modifier = Modifier
                .padding(bottom = 26.dp)
                .fillMaxWidth()
                .height(52.dp),
            enabled = loginViewModel.isLoginEnabled(inputEmail, inputPassword),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE8003C),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF333333),
                disabledContentColor = Color(0xFF666666)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                "로그인",
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}