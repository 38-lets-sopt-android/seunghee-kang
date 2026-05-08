package com.example.letssopt.core.data.network

import com.example.letssopt.core.data.dto.request.SignInRequest
import com.example.letssopt.core.data.dto.request.SignUpRequest
import com.example.letssopt.core.data.dto.response.SignInResponse
import com.example.letssopt.core.data.dto.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
interface AuthService {
    @POST("api/v1/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @POST("api/v1/auth/signin")
    // [수정] SignUpResponse 대신 전용 모델인 SignInResponse 사용 ✨
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>
}