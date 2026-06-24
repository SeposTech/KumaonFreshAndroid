package com.himanshumehra.kumaonfresh.data.repository

import com.himanshumehra.kumaonfresh.data.remote.api.ApiService
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.AddQuantityToCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.AddToCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.RemoveQuantityFromCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.AddToCartResponse
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CartDetailResponse
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.QuantityAddRemoveResponse
import com.himanshumehra.kumaonfresh.domain.repository.CartRepository
import jakarta.inject.Inject

class CartRepositoryImp @Inject constructor(private val apiService: ApiService) :
    CartRepository {

    override suspend fun addToCart(addToCart: AddToCart): AddToCartResponse {
        return apiService.addToCart(addToCart)
    }

    override suspend fun getCart(userId: String): CartDetailResponse {
        return apiService.getCart(userId)
    }

    override suspend fun addQuantityToCart(addQuantityToCart: AddQuantityToCart): QuantityAddRemoveResponse {
        return apiService.addQuantityToCart(addQuantityToCart)
    }

    override suspend fun removeQuantityFromCart(removeQuantityFromCart: RemoveQuantityFromCart): QuantityAddRemoveResponse {
        return apiService.removeQuantityFromCart(removeQuantityFromCart)
    }
}