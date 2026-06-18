package com.himanshumehra.kumaonfresh.data.repository

import com.himanshumehra.kumaonfresh.data.remote.api.ApiService
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.CreateUserRequest
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CreateUserResponse
import com.himanshumehra.kumaonfresh.domain.repository.CreateUserRepository
import javax.inject.Inject

class CreateUserRepositoryImp @Inject constructor(private val apiService: ApiService) :
    CreateUserRepository {
    override suspend fun createUser(createUserRequest: CreateUserRequest): CreateUserResponse {
        return apiService.createUser(createUserRequest = createUserRequest)
    }

}