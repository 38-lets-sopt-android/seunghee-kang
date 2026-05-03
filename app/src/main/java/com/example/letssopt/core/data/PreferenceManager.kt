package com.example.letssopt.core.data

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("AUTH_PREFS", Context.MODE_PRIVATE)

    // 자동 로그인 여부 저장
    fun setAutoLogin(isAuto: Boolean) {
        prefs.edit().putBoolean("IS_AUTO_LOGIN", isAuto).apply()
    }

    // 자동 로그인 여부 확인
    fun getAutoLogin(): Boolean {
        return prefs.getBoolean("IS_AUTO_LOGIN", false)
    }
}