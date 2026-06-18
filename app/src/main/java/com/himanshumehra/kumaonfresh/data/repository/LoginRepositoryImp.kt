package com.himanshumehra.kumaonfresh.data.repository

import com.himanshumehra.kumaonfresh.data.remote.api.ApiService
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.LoginRequest
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.LoginResponse
import com.himanshumehra.kumaonfresh.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(private val apiService: ApiService) : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return apiService.loginUser(loginRequest = loginRequest)
    }


}