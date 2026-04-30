package com.example.letssopt.week2.home

import androidx.lifecycle.ViewModel
import com.example.letssopt.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.collections.immutable.toPersistentList
import kotlinx.collections.immutable.ImmutableList
import com.example.letssopt.week2.home.model.PartyModel

class HomeViewModel : ViewModel() {
    // 상단 배너용 이미지
    private val _bannerImages = MutableStateFlow(
        listOf(
            R.drawable.img_banner1,
            R.drawable.img_banner2,
            R.drawable.img_banner3
        )
    )
    val bannerImages: StateFlow<List<Int>> = _bannerImages

    // 중간 포스터용 이미지 (왓고리즘, 공개 예정 등)
    private val _contentImages = MutableStateFlow(
        listOf(
            R.drawable.img_content1,
            R.drawable.img_content2,
            R.drawable.img_content3
        ) + listOf(
            R.drawable.img_content1,
            R.drawable.img_content2,
            R.drawable.img_content3
        )
    )
    val contentImages: StateFlow<List<Int>> = _contentImages

    // 왓챠 파티용 데이터 (이미지 + 제목)
    private val _partyList = MutableStateFlow(
        listOf(
            PartyModel("오늘 21:13", "# 왕과 사는 남자", R.drawable.img_party1),
            PartyModel("내일 22:22", "# 파묘", R.drawable.img_party2)
        ).toPersistentList() // Immutable 형태로 변환
    )
    val partyList: StateFlow<ImmutableList<PartyModel>> = _partyList
}