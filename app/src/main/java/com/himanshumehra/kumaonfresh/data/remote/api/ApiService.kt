package com.himanshumehra.kumaonfresh.data.remote.api

import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.CreateUserRequest
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.LoginRequest
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CategoryResponse
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CreateUserResponse
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.ItemResponse
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

    @POST("users/create")
    suspend fun createUser(@Body createUserRequest: CreateUserRequest): CreateUserResponse

    @GET("category/getCategory")
    suspend fun getCategory(): CategoryResponse

    @FormUrlEncoded
    @POST("item/getItems")
    suspend fun getItems(@Field("categoryId") catId: Long): ItemResponse


}