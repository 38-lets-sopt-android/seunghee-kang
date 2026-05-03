package com.example.letssopt.feature.catagory.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.R

@Composable
fun CategoryItem(
    title: String,
    imageRes: Int,
    onSaveClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(100.dp)
    ) {
        Box(
            modifier = Modifier.size(width = 100.dp, height = 150.dp)
        ) {
            // 포스터 이미지
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .padding(top = 6.dp, end = 6.dp)
                    .size(28.dp)
                    .background(Color.Black, shape = CircleShape)
                    .align(Alignment.TopEnd)
                    .clickable { onSaveClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ticket),
                    contentDescription = "저장",
                    modifier = Modifier.size(18.dp),
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // 제목
        Text(
            text = title,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            textAlign = TextAlign.Left,
            lineHeight = 16.sp,
            modifier = Modifier.size(width = 100.dp, height = 38.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    Box(modifier = Modifier.padding(8.dp)) {
        CategoryItem(
            title = "이 사랑 통역 되나요?",
            imageRes = R.drawable.img_content1,
            onSaveClick = {}
        )
    }
}