package com.example.letssopt.week1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.letssopt.MainActivity
import com.example.letssopt.ui.theme.LETSSOPTTheme

class LoginActivity : ComponentActivity() {

    private var registeredEmail by mutableStateOf("")
    private var registeredPw by mutableStateOf("")

    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
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
                    LoginScreen(
                        registeredEmail = registeredEmail,
                        registeredPw = registeredPw,
                        onSignUpClick = {
                            val intent = Intent(this, SignUpActivity::class.java)
                            signUpLauncher.launch(intent)
                        },
                        onLoginClick = { email, password ->
                            if (email == registeredEmail && password == registeredPw && email.isNotEmpty()) {
                                Toast.makeText(this, "✅ 로그인 성공!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, MainActivity::class.java))
                            } else {
                                Toast.makeText(this, "❌ 정보를 확인해주세요.", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}