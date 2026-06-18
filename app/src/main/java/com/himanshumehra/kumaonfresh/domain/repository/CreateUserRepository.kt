package com.himanshumehra.kumaonfresh.domain.repository

import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.CreateUserRequest
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CreateUserResponse

interface CreateUserRepository {
    suspend fun createUser(createUserRequest: CreateUserRequest) : CreateUserResponse
}