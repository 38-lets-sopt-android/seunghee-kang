package com.example.letssopt.week2.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    // 하단 탭 선택 상태 관리
    private val _selectedTab = MutableStateFlow("메인")
    val selectedTab: StateFlow<String> = _selectedTab

    // 탭 클릭 시 호출할 함수
    fun updateTab(newTab: String) {
        _selectedTab.value = newTab
    }
}