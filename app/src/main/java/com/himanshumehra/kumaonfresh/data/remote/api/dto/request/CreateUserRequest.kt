package com.himanshumehra.kumaonfresh.data.remote.api.dto.request

data class CreateUserRequest(
    val name: String,
    val email: String,
    val phone: String,
    val password: String
)