package com.himanshumehra.kumaonfresh.data.remote.api.dto.request

data class AddToCart(
    val userId: String? = null,
    val itemId: String? = null,
    val quantity: Int = 0,
    val itemPrice: Double = 0.00
)