package com.himanshumehra.kumaonfresh.domain.repository

import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.AddQuantityToCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.AddToCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.RemoveQuantityFromCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.AddToCartResponse
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CartDetailResponse
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.QuantityAddRemoveResponse

interface CartRepository {
    suspend fun addToCart(addToCart: AddToCart): AddToCartResponse
    suspend fun getCart(userId: String): CartDetailResponse

    suspend fun addQuantityToCart(addQuantityToCart: AddQuantityToCart): QuantityAddRemoveResponse

    suspend fun removeQuantityFromCart(removeQuantityFromCart: RemoveQuantityFromCart): QuantityAddRemoveResponse
}