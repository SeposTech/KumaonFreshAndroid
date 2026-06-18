package com.himanshumehra.kumaonfresh.domain.repository

import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.LoginRequest
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.LoginResponse

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): LoginResponse

}