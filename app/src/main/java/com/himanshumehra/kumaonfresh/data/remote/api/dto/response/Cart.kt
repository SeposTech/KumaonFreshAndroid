package com.himanshumehra.kumaonfresh.data.remote.api.dto.response

data class AddToCartResponse(
    val status: String,
    val message: String
)


data class CartDetailResponse(
    val status: String,
    val message: String,
    val data: List<CartItem>?
)

data class CartItem(
    val categoryId: String,
    val itemDescription: String,
    val itemId: String,
    val itemImage: String,
    val itemName: String,
    val itemPrice: Double,
    val itemQuantity: Int
)

data class QuantityAddRemoveResponse(
    val status: String,
    val message: String,
    val data: QuantityAddRemove
)

data class QuantityAddRemove(
    val userId: String,
    val itemId: String,
    val quantity: Int
)