package com.example.letssopt.core.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: LoginData? = null
)

@Serializable
data class LoginData(
    @SerialName("userId")
    val userId: Long
)