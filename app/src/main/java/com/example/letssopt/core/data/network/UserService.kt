package com.example.letssopt.core.data.network

import retrofit2.Response
import com.example.letssopt.core.data.dto.response.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("api/v1/users/{userId}")
    suspend fun getUserInfo(
        @Path("userId") userId: Long
    ): Response<UserInfoResponse>
}