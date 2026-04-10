package com.example.letssopt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.ui.theme.LETSSOPTTheme

class LoginActivity : ComponentActivity() {

    // 회원가입에서 받아온 정보를 저장할 변수
    private var registeredEmail by mutableStateOf("")
    private var registeredPw by mutableStateOf("")

    // 회원가입 화면으로부터 결과를 받아오기 위한 런처
    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // SignUpActivity에서 보낸 데이터를 받음
        if (result.resultCode == RESULT_OK) {
            registeredEmail = result.data?.getStringExtra("userEmail") ?: ""
            registeredPw = result.data?.getStringExtra("userPassword") ?: ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        // 런처와 저장된 데이터를 Composable에 전달
                        registeredEmail = registeredEmail,
                        registeredPw = registeredPw,
                        onSignUpClick = {
                            // SignUpActivity로 이동하기 위해 런처를 실행
                            val intent = Intent(this, SignUpActivity::class.java)
                            signUpLauncher.launch(intent)
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(

    // SignUpActivity에서 받아온 데이터
    registeredEmail: String,
    registeredPw: String,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var inputEmail by remember { mutableStateOf("") }
    var inputPw by remember { mutableStateOf("")}

    // 로그인 버튼 활성화 조건
    val isLoginEnabled = inputEmail.isNotEmpty() && inputPw.isNotEmpty()

    // SignUpActivity에서 받아온 데이터를 입력창에 자동으로 적용
    LaunchedEffect(registeredEmail) {
        if (registeredEmail.isNotEmpty()) inputEmail = registeredEmail
        if (registeredPw.isNotEmpty()) inputPw = registeredPw
    }

    val context = LocalContext.current

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
            text = "이메일로 로그인",
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
            value = inputEmail,
            onValueChange = { inputEmail = it },
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
            // 키보드 타입 이메일 전용으로 변경
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
            value = inputPw,
            onValueChange = { inputPw = it },
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

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "아직 계정이 없으신가요? 회원가입",
            color = Color(0xFF999999),
            fontSize = 14.sp,
            modifier = Modifier
                .clickable{
                    // 회원가입 문구 클릭 시 런처 실행
                    onSignUpClick()
                }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                // 로그인 성공 조건 확인
                if (inputEmail == registeredEmail && inputPw == registeredPw && inputEmail.isNotEmpty()) {
                    Toast.makeText(context, "✅ 로그인 성공!", Toast.LENGTH_SHORT).show()

                    // 메인 화면으로 이동
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "❌ 이메일 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .padding(bottom = 26.dp)
                .fillMaxWidth()
                .height(52.dp),

            // 버튼 활성화/비활성화 제어
            enabled = isLoginEnabled,

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE8003C),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF333333),
                disabledContentColor = Color(0xFF666666)),
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    LETSSOPTTheme {
        // 임시 데이터 넣어주기
        Greeting2(
            "sopt@sopt.org",
            "12345678",
            {},
        )
    }
}