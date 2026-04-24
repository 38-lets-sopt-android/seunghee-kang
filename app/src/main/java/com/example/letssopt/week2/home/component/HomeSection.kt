package com.example.letssopt.week2.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.R

@Composable
fun HomeSection(
    title: String,
    subtitle: String = "",
    showMore: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )
            if (subtitle.isNotEmpty()) {
                Text(
                    text = subtitle,
                    modifier = Modifier.padding(top = 4.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFBABAC1)
                    )
                )
            }
        }

        if (showMore) {
            Text(
                text = "더보기",
                modifier = Modifier.padding(bottom = 2.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF999999)
                )
            )
        }
    }
}