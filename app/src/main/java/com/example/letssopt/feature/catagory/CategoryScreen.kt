package com.example.letssopt.feature.catagory

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.feature.catagory.component.CategoryItem

data class MovieData(
    val title: String,
    val imageRes: Int
)

@Composable
fun CategoryScreen(viewModel: CategoryViewModel = viewModel()) {
    val context = LocalContext.current
    val toastMessage by viewModel.toastMessage.collectAsStateWithLifecycle(initialValue = null)

    // 토스트 메시지 처리
    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    // 데이터 리스트
    val movieList = listOf(
        MovieData("이 사랑 통역 되나요", R.drawable.img_content1),
        MovieData("이상한일5", R.drawable.img_content2),
        MovieData("하일매리", R.drawable.img_content3),
        MovieData("이 사랑 통역 되나요", R.drawable.img_content1),
        MovieData("이상한일5", R.drawable.img_content2),
        MovieData("하일매리", R.drawable.img_content3)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
    ) {
        // 타이틀
        Spacer(modifier = Modifier.height(70.dp))
        Text(
            text = "개별 구매",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.pretendard_bold)),
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(45.dp))

        // 그리드
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(movieList) { movie ->
                CategoryItem(
                    title = movie.title,
                    imageRes = movie.imageRes,
                    onSaveClick = {
                        // ViewModel의 저장 로직 호출
                        viewModel.saveMovie(movie.title, movie.imageRes)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF141414)
@Composable
fun CategoryScreenPreview() {
    // 더미 데이터
    val dummyMovies = listOf(
        MovieData("이 사랑 통역 되나요?", R.drawable.img_content1),
        MovieData("이상한일5", R.drawable.img_content2),
        MovieData("하일매리", R.drawable.img_content3),
        MovieData("이 사랑 통역 되나요?", R.drawable.img_content1),
        MovieData("이상한일5", R.drawable.img_content2),
        MovieData("하일매리", R.drawable.img_content3)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        Text(
            text = "개별 구매",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(dummyMovies) { movie ->
                CategoryItem(
                    title = movie.title,
                    imageRes = movie.imageRes,
                    onSaveClick = {}
                )
            }
        }
    }
}