package com.example.letssopt.feature.library

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
import androidx.compose.ui.Alignment
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
import com.example.letssopt.feature.library.component.LibraryItem
import com.example.letssopt.R
import com.example.letssopt.core.data.database.MovieEntity

@Composable
fun LibraryScreen(viewModel: LibraryViewModel = viewModel()) {
    val context = LocalContext.current
    val toastMessage by viewModel.toastMessage.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    val libraryList by viewModel.libraryList.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF141414))
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        Text(
            text = "찜한 목록",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.pretendard_bold)),
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(45.dp))

        if (libraryList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "찜해놓은 목록이 없습니다.", color = Color.Gray, fontSize = 16.sp)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(items = libraryList, key = { it.id }) { movie ->
                    LibraryItem(
                        movie = movie,
                        onDeleteClick = { viewModel.deleteMovie(movie) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LibraryScreenDataPreview() {
    // 더미 데이터
    val dummyLibraryList = listOf(
        MovieEntity(id = 1, title = "이 사랑 통역 되나요?", imageRes = R.drawable.img_content1),
        MovieEntity(id = 2, title = "이상한일5", imageRes = R.drawable.img_content2),
        MovieEntity(id = 3, title = "하일매리", imageRes = R.drawable.img_content3),
        MovieEntity(id = 4, title = "이 사랑 통역 되나요?", imageRes = R.drawable.img_content1),
        MovieEntity(id = 5, title = "이상한일5", imageRes = R.drawable.img_content2),
        MovieEntity(id = 6, title = "하일매리", imageRes = R.drawable.img_content3)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
    ) {
        // 타이틀
        Spacer(modifier = Modifier.height(70.dp))

        Text(
            text = "찜한 목록",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 그리드
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(dummyLibraryList) { movie ->
                LibraryItem(
                    movie = movie,
                    onDeleteClick = {}
                )
            }
        }
    }
}