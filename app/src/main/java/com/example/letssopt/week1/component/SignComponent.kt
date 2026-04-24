package com.example.letssopt.week1.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.R


/// 로고
@Composable
fun WatchaLogo(modifier: Modifier = Modifier) {
    Text(
        text = "watcha",
        fontSize = 36.sp,
        color = Color(0xFFE8003C),
        fontFamily = FontFamily(Font(R.font.pretendard_bold)),
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

// 타이틀
@Composable
fun SignTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 20.sp,
        color = Color.White,
        fontFamily = FontFamily(Font(R.font.pretendard_bold)),
        fontWeight = FontWeight.Bold,
        modifier = modifier.fillMaxWidth()
    )
}

// 입력 필드
@Composable
fun SignTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // 입력창 위 제목 (이메일, 비밀번호 등)
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF999999),
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 3.dp)
        )

        TextField(
            modifier = Modifier
                .padding(bottom = 18.dp)
                .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(size = 8.dp),
            visualTransformation = visualTransformation,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF666666),
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedContainerColor = Color(0xFF2A2A2A),
                focusedContainerColor = Color(0xFF2A2A2A),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = keyboardOptions
        )
    }
}