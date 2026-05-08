package com.example.letssopt.feature.home

import androidx.lifecycle.ViewModel
import com.example.letssopt.R
import com.example.letssopt.feature.home.model.PartyModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeUiState(
    val bannerImages: ImmutableList<Int> = persistentListOf(),
    val contentImages: ImmutableList<Int> = persistentListOf(),
    val partyList: ImmutableList<PartyModel> = persistentListOf()
)

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = HomeUiState(
            bannerImages = listOf(
                R.drawable.img_banner1,
                R.drawable.img_banner2,
                R.drawable.img_banner3
            ).toPersistentList(),

            contentImages = (listOf(
                R.drawable.img_content1,
                R.drawable.img_content2,
                R.drawable.img_content3
            ) + listOf(
                R.drawable.img_content1,
                R.drawable.img_content2,
                R.drawable.img_content3
            )).toPersistentList(),

            partyList = listOf(
                PartyModel(
                    id = "1",
                    time = "오늘 21:13",
                    title = "# 왕과 사는 남자",
                    imageRes = R.drawable.img_party1
                ),
                PartyModel(
                    id = "2",
                    time = "내일 22:22",
                    title = "# 파묘",
                    imageRes = R.drawable.img_party2
                )
            ).toPersistentList()
        )
    }
}