package com.himanshumehra.kumaonfresh.data.remote.api.dto.response

data class LoginResponse(
    val status: String,
    val message: String,
    val data: UserData?
)

data class UserData(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val token: String
)