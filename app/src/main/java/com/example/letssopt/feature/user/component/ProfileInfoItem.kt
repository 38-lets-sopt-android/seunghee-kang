package com.example.letssopt.feature.user.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.R

@Composable
fun ProfileInfoItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 3.dp)
    ) {
        Text(
            text = label,
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_bold)),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = value,
            color = Color(0xFF999999),
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileInfoItemPreview() {
    Column() {
        ProfileInfoItem(
            label = "아이디",
            value = "SOPT_Android"
        )
        ProfileInfoItem(
            label = "이름",
            value = "강승희"
        )
    }
}