package com.example.letssopt.feature.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.common.util.UiState
import com.example.letssopt.core.component.SignTitle
import com.example.letssopt.core.component.WatchaLogo
import com.example.letssopt.core.data.dto.response.UserData
import com.example.letssopt.feature.user.component.ProfileInfoItem

@Composable
fun UserScreen(
    userId: Long,
    onShowToast: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = viewModel()
) {
    val userState by viewModel.userState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadUserInfo(userId)
    }

    LaunchedEffect(userState) {
        if (userState is UiState.Error) {
            onShowToast((userState as UiState.Error).message)
        }
    }

    UserContent(
        userState = userState,
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@Composable
private fun UserContent(
    userState: UiState<UserData>,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        SignTitle(
            text = "프로필",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 7.dp)
        )
        Spacer(modifier = Modifier.height(68.dp))

        if (userState is UiState.Success) {
            val user = userState.data
            ProfileInfoItem(label = "아이디", value = user.loginId)
            ProfileInfoItem(label = "이름", value = user.name)
            ProfileInfoItem(label = "이메일", value = user.email)
            ProfileInfoItem(label = "나이", value = "${user.age}세")
            ProfileInfoItem(label = "파트", value = user.part)
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier
                .padding(bottom = 26.dp)
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE8003C),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF333333),
                disabledContentColor = Color(0xFF666666)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "다른 유저들 보러가기",
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserScreenSuccessPreview() {
    val testUser = UserData(
        id = 1L,
        loginId = "sopt_tester",
        name = "강승희",
        email = "sopt@dlu.ac.kr",
        age = 24,
        part = "안드로이드"
    )

    UserContent(
        userState = UiState.Success(testUser),
        onBackClick = {}
    )
}