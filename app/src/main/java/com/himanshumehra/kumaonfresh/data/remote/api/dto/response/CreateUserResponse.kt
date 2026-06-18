package com.himanshumehra.kumaonfresh.data.remote.api.dto.response

data class CreateUserResponse(
    val status: String,
    val message: String,
    val data: CreateUserData?
)

data class CreateUserData(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val token: String
)