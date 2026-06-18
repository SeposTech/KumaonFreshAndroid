package com.himanshumehra.kumaonfresh.domain.usecase

import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.LoginRequest
import com.himanshumehra.kumaonfresh.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(loginRequest: LoginRequest) = loginRepository.login(loginRequest)

}