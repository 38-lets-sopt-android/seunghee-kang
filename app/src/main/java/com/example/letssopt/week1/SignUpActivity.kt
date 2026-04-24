package com.example.letssopt.week1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.letssopt.ui.theme.LETSSOPTTheme
import com.example.letssopt.week1.signup.SignUpScreen
import com.example.letssopt.week1.signup.SignUpViewModel

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