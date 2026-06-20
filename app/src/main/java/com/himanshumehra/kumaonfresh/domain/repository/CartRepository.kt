package com.himanshumehra.kumaonfresh.domain.repository

import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.AddToCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.AddToCartResponse
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CartDetailResponse

interface CartRepository {
    suspend fun addToCart(addToCart: AddToCart): AddToCartResponse
    suspend fun getCart(userId: String): CartDetailResponse
}