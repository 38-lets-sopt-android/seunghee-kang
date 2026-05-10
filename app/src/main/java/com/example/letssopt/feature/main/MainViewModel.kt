package com.example.letssopt.feature.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _userId = MutableStateFlow<Long>(-1L)
    val userId: StateFlow<Long> = _userId.asStateFlow()

    fun updateUserId(id: Long) {
        _userId.value = id
    }
    private val _selectedTab = MutableStateFlow(MainTab.HOME)
    val selectedTab: StateFlow<MainTab> = _selectedTab.asStateFlow()

    fun updateTab(tab: MainTab) {
        _selectedTab.value = tab
    }
}