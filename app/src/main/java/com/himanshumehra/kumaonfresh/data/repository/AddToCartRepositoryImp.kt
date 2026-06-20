package com.himanshumehra.kumaonfresh.data.repository

import com.himanshumehra.kumaonfresh.data.remote.api.ApiService
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.AddToCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CartResponse
import com.himanshumehra.kumaonfresh.domain.repository.CartRepository
import jakarta.inject.Inject

class AddToCartRepositoryImp @Inject constructor(private val apiService: ApiService) :
    CartRepository {

    override suspend fun addToCart(addToCart: AddToCart): CartResponse {
        return apiService.addToCart(addToCart)
    }

    override suspend fun getCart(userId: String): CartResponse {
        return apiService.getCart(userId)
    }
}