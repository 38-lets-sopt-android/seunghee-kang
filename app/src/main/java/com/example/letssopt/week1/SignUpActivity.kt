package com.example.letssopt.week1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.example.letssopt.R
import com.example.letssopt.ui.theme.LETSSOPTTheme
import androidx.activity.viewModels
import com.example.letssopt.week1.components.SignTitle
import com.example.letssopt.week1.components.WatchaLogo

class SignUpActivity : ComponentActivity() {

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpScreen(
                        viewModel = signUpViewModel,
                        onSignUpSuccess = { email, password ->
                            val returnIntent = Intent().apply {
                                putExtra("userEmail", email)
                                putExtra("userPassword", password)
                            }
                            setResult(RESULT_OK, returnIntent)
                            finish()
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel,
    onSignUpSuccess: (String, String) -> Unit
) {

    val context = LocalContext.current

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

        SignTextField(
            label = "이메일",
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            placeholder = "이메일 주소를 입력하세요",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        SignTextField(
            label = "비밀번호",
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            placeholder = "비밀번호를 입력하세요",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        SignTextField(
            label = "비밀번호 확인",
            value = viewModel.passwordConfirm,
            onValueChange = { viewModel.passwordConfirm = it },
            placeholder = "비밀번호를 다시 입력하세요",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (!viewModel.isEmailValid) {
                    Toast.makeText(context, "❌ 이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                } else if (!viewModel.isPasswordValid) {
                    Toast.makeText(context, "❌ 비밀번호는 8~12자여야 합니다.", Toast.LENGTH_SHORT).show()
                } else if (!viewModel.isPasswordMatching) {
                    Toast.makeText(context, "❌ 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "✅ 회원가입 성공!", Toast.LENGTH_SHORT).show()

                    onSignUpSuccess(viewModel.email, viewModel.password)
                }
            },
            modifier = Modifier
                .padding(bottom = 26.dp)
                .fillMaxWidth()
                .height(52.dp),

            // 버튼 활성화/비활성화 제어
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
            viewModel = SignUpViewModel(),
            onSignUpSuccess = { _, _ -> }
        )
    }
}