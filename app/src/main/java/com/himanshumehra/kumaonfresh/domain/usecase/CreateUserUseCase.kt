package com.himanshumehra.kumaonfresh.domain.usecase

import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.CreateUserRequest
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CreateUserResponse
import com.himanshumehra.kumaonfresh.domain.repository.CreateUserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val createUserRepository: CreateUserRepository) {

    suspend operator fun invoke(createUserRequest: CreateUserRequest): CreateUserResponse {
        return createUserRepository.createUser(createUserRequest)
    }
}