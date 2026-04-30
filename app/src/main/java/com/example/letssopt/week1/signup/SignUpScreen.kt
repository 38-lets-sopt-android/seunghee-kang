package com.example.letssopt.week1.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.letssopt.ui.theme.LETSSOPTTheme
import com.example.letssopt.week1.component.SignTextField
import com.example.letssopt.week1.component.SignTitle
import com.example.letssopt.week1.component.WatchaLogo

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel(),
    onSignUpSuccess: (String, String) -> Unit
) {
    val context = LocalContext.current

    // StateFlow 설정
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val passwordConfirm by viewModel.passwordConfirm.collectAsStateWithLifecycle()

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
        SignTitle(text = "회원가입", modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(36.dp))

        // 뷰모델 함수를 호출하여 값 변경 반영
        SignTextField(
            label = "이메일",
            value = email, // 변경
            onValueChange = { viewModel.onEmailChanged(it) },
            placeholder = "이메일 주소를 입력하세요",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        SignTextField(
            label = "비밀번호",
            value = password, // 변경
            onValueChange = { viewModel.onPasswordChanged(it) },
            placeholder = "비밀번호를 입력하세요",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        SignTextField(
            label = "비밀번호 확인",
            value = passwordConfirm, // 변경
            onValueChange = { viewModel.onPasswordConfirmChanged(it) },
            placeholder = "비밀번호를 다시 입력하세요",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                when {
                    !viewModel.isEmailValid -> Toast.makeText(
                        context,
                        "❌ 이메일 형식이 올바르지 않습니다.",
                        Toast.LENGTH_SHORT
                    ).show()

                    !viewModel.isPasswordValid -> Toast.makeText(
                        context,
                        "❌ 비밀번호는 8~12자여야 합니다.",
                        Toast.LENGTH_SHORT
                    ).show()

                    !viewModel.isPasswordMatching -> Toast.makeText(
                        context,
                        "❌ 비밀번호가 일치하지 않습니다.",
                        Toast.LENGTH_SHORT
                    ).show()

                    else -> {
                        Toast.makeText(context, "✅ 회원가입 성공!", Toast.LENGTH_SHORT).show()
                        onSignUpSuccess(email, password) // 변경
                    }
                }
            },
            modifier = Modifier
                .padding(bottom = 26.dp)
                .fillMaxWidth()
                .height(52.dp),
            enabled = viewModel.isAllFieldsFilled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE8003C),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF333333),
                disabledContentColor = Color(0xFF666666)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                "회원가입",
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
    LETSSOPTTheme {
        SignUpScreen(
            onSignUpSuccess = { _, _ -> }
        )
    }
}