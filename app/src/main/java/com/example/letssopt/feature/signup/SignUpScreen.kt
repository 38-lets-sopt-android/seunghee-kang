package com.example.letssopt.feature.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun SignUpScreen(
    onShowToast: (String) -> Unit,
    onSignUpSuccess: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel()
) {
    // 변수명들을 뷰모델의 서버 통신용 변수와 매칭!
    val loginId by viewModel.loginId.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val passwordConfirm by viewModel.passwordConfirm.collectAsStateWithLifecycle()
    val name by viewModel.name.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val age by viewModel.age.collectAsStateWithLifecycle()
    val part by viewModel.part.collectAsStateWithLifecycle()

    val signUpState by viewModel.signUpState.collectAsStateWithLifecycle()

    // [추가] 결과 리액션 ✨
    LaunchedEffect(signUpState) {
        when (signUpState) {
            is UiState.Success -> {
                onShowToast("✅ 회원가입 성공!")
                onSignUpSuccess(loginId, password)
            }
            is UiState.Error -> {
                onShowToast("❌ ${(signUpState as UiState.Error).message}")
            }
            else -> Unit
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 원본 디자인 레이아웃 유지!
        Spacer(modifier = Modifier.height(60.dp))
        WatchaLogo()
        Spacer(modifier = Modifier.height(26.dp))
        SignTitle(text = "회원가입", modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(36.dp))

        // 각 필드들을 뷰모델의 상태와 함수에 쏙쏙!
        SignTextField(label = "아이디", value = loginId, onValueChange = { viewModel.onLoginIdChanged(it) }, placeholder = "아이디를 입력하세요")
        SignTextField(label = "비밀번호", value = password, onValueChange = { viewModel.onPasswordChanged(it) }, placeholder = "비밀번호를 입력하세요", visualTransformation = PasswordVisualTransformation())
        SignTextField(label = "비밀번호 확인", value = passwordConfirm, onValueChange = { viewModel.onPasswordConfirmChanged(it) }, placeholder = "비밀번호를 다시 입력하세요", visualTransformation = PasswordVisualTransformation())
        SignTextField(label = "이름", value = name, onValueChange = { viewModel.onNameChanged(it) }, placeholder = "이름을 입력하세요")
        SignTextField(label = "이메일", value = email, onValueChange = { viewModel.onEmailChanged(it) }, placeholder = "이메일을 입력하세요")
        SignTextField(label = "나이", value = age, onValueChange = { viewModel.onAgeChanged(it) }, placeholder = "나이를 입력하세요", keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        SignTextField(label = "파트", value = part, onValueChange = { viewModel.onPartChanged(it) }, placeholder = "파트를 입력하세요 (iOS / 안드로이드 / 웹)")

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = { viewModel.signUp() }, // 로직은 서버 호출로! 🔥
            modifier = Modifier
                .padding(bottom = 26.dp)
                .fillMaxWidth()
                .height(52.dp),
            // 모든 필드가 채워졌을 때만 활성화 (뷰모델 로직 활용)
            enabled = viewModel.isSignUpEnabled(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE8003C),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF333333),
                disabledContentColor = Color(0xFF666666)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "회원가입", // 승희님 요청대로 "회원가입" 고정! ✨
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    // 테마가 있다면 테마로 감싸주세요! (예: LETSSOPTTheme)
    SignUpScreen(
        onShowToast = {},
        onSignUpSuccess = { _, _ -> }
    )
}