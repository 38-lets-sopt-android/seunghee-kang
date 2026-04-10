package com.example.letssopt

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
import com.example.letssopt.ui.theme.LETSSOPTTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting3(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("")}
    var pwConfirm by remember { mutableStateOf("") }

    val context = LocalContext.current

    // [회원가입 성공조건]
    // 이메일 형식
    val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    // 비밀번호 8~12자
    val isPasswordValid = pw.length in 8..12
    // 비밀번호 일치 확인
    val isPasswordMatching = pw == pwConfirm && pw.isNotEmpty()
    // 모든 필드 입력 여부
    val isAllFieldsFilled = email.isNotEmpty() && pw.isNotEmpty() && pwConfirm.isNotEmpty()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment . CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "watcha",
            fontSize = 36.sp,
            color = Color(0xFFE8003C),
            fontFamily = FontFamily(Font(R.font.pretendard_bold)),
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "회원가입",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.pretendard_bold)),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "이메일",
            fontSize = 14.sp,
            color = Color(0xFF999999),
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 3.dp)
        )

        TextField(
            modifier = Modifier
                .padding(bottom = 18.dp)
                .fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            shape = RoundedCornerShape(size = 8.dp),
            placeholder = {
                Text(
                    text = "이메일 주소를 입력하세요",
                    color = Color(0xFF666666),
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                )
            },
            colors =TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedContainerColor = Color(0xFF2A2A2A),
                focusedContainerColor = Color(0xFF2A2A2A),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            // 키보드 타입 이메일 전용
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Text(
            text = "비밀번호",
            fontSize = 14.sp,
            color = Color(0xFF999999),
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 3.dp)
        )
        TextField(
            modifier = Modifier
                .padding(bottom = 18.dp)
                .fillMaxWidth(),
            value = pw,
            onValueChange = { pw = it },
            shape = RoundedCornerShape(size = 8.dp),
            visualTransformation = PasswordVisualTransformation(), // 마스킹 처리
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // 키보드를 비밀번호 입력 모드(영문 위주, 자동완성 끄기)로 설정
            placeholder = {
                Text(
                    text = "비밀번호를 입력하세요",
                    color = Color(0xFF666666),
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                )
            },
            colors =TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedContainerColor = Color(0xFF2A2A2A),
                focusedContainerColor = Color(0xFF2A2A2A),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )

        Text(
            text = "비밀번호 확인",
            fontSize = 14.sp,
            color = Color(0xFF999999),
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 3.dp)
        )

        TextField(
            modifier = Modifier
                .padding(bottom = 18.dp)
                .fillMaxWidth(),
            value = pwConfirm,
            onValueChange = {pwConfirm = it},
            shape = RoundedCornerShape(size = 8.dp),
            visualTransformation = PasswordVisualTransformation(), // 마스킹 처리
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // 키보드를 비밀번호 입력 모드(영문 위주, 자동완성 끄기)로 설정
            placeholder = {
                Text(
                    text = "비밀번호를 다시 입력하세요",
                    color = Color(0xFF666666),
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                )
            },
            colors =TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedContainerColor = Color(0xFF2A2A2A),
                focusedContainerColor = Color(0xFF2A2A2A),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),

        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (!isEmailValid) {
                    Toast.makeText(context, "❌ 이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                } else if (!isPasswordValid) {
                    Toast.makeText(context, "❌ 비밀번호는 8~12자여야 합니다.", Toast.LENGTH_SHORT).show()
                } else if (!isPasswordMatching) {
                    Toast.makeText(context, "❌ 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "✅ 회원가입 성공!", Toast.LENGTH_SHORT).show()

                    val returnIntent = Intent(context, LoginActivity::class.java).apply {
                    putExtra("userEmail", email)
                    putExtra("userPassword", pw)
                }
                // LoginActivity에게 데이터 전달
                val activity = context as? android.app.Activity
                activity?.setResult(android.app.Activity.RESULT_OK, returnIntent)

                // LoginActivity로 돌아가기
                activity?.finish()
                }
            },
            modifier = Modifier
                .padding(bottom = 26.dp)
                .fillMaxWidth()
                .height(52.dp),

            // 버튼 활성화/비활성화 제어
            enabled = isAllFieldsFilled,

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE8003C),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF333333),
                disabledContentColor = Color(0xFF666666)),
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
fun GreetingPreview3() {
    LETSSOPTTheme {
        Greeting3("Android")
    }
}