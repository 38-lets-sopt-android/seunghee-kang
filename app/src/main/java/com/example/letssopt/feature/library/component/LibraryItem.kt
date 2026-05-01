package com.example.letssopt.feature.library.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.R
import com.example.letssopt.core.data.database.MovieEntity

@Composable
fun LibraryItem(
    movie: MovieEntity,
    onDeleteClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(100.dp)
    ) {
        // 포스터
        Image(
            painter = painterResource(id = movie.imageRes),
            contentDescription = movie.title,
            modifier = Modifier
                .size(width = 100.dp, height = 150.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(6.dp))

        // 제목
        Text(
            text = movie.title,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            textAlign = TextAlign.Left,
            modifier = Modifier.size(width = 100.dp, height = 38.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        //삭제 버튼
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "삭제",
                tint = Color.Unspecified
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LibraryItemPreview() {
    val dummyMovie = MovieEntity(
        id = 1,
        title = "이 사랑 통역 되나요?",
        imageRes = R.drawable.img_content1
    )

    Box(modifier = Modifier.padding(8.dp)) {
        LibraryItem(
            movie = dummyMovie,
            onDeleteClick = {}
        )
    }
}