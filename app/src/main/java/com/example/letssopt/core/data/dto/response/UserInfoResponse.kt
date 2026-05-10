package com.example.letssopt.core.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("status") val status: Int,
    @SerialName("message") val message: String,
    @SerialName("data") val data: UserData?
)

@Serializable
data class UserData(
    @SerialName("id")
    val id: Long,
    @SerialName("loginId")
    val loginId: String,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("age")
    val age: Int,
    @SerialName("part")
    val part: String
)