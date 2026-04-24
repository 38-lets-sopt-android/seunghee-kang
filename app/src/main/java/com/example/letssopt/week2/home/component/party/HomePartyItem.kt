package com.example.letssopt.week2.home.component.party

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.R
import com.example.letssopt.week2.home.PartyModel

@Composable
fun HomePartyItem(party: PartyModel) { // PartyModel 객체를 통째로 받음
    Box(
        modifier = Modifier
            .width(196.dp)
            .height(185.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(0xFF2A2A2A),
                    shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                )
        ) {
            // 뷰모델에 저장된 파티 이미지 출력
            Image(
                painter = painterResource(id = party.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(139.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                // 뷰모델에 저장된 시작 시간 출력
                Text(
                    text = party.time,
                    modifier = Modifier.padding(start = 8.dp, top = 6.dp, bottom = 6.dp),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color(0xFFE8003C),
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        fontWeight = FontWeight.Medium
                    )
                )

                // 뷰모델에 저장된 파티 제목 출력
                Text(
                    text = party.title,
                    modifier = Modifier.padding(start = 8.dp, bottom = 7.dp),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }

        // 알림 아이콘
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 7.dp, end = 5.dp)
                .size(35.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_noti_black),
                contentDescription = "noti",
                modifier = Modifier.size(18.dp),
                tint = Color.Black
            )
        }
    }
}