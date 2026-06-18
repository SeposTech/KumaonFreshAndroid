package com.himanshumehra.kumaonfresh.data.remote.api.dto.response

data class ItemResponse(
    val status: String,
    val message: String,
    val data: List<ItemData>?
)

data class ItemData(
    val categoryId: Long,
    val itemDescription: String,
    val itemId: Long,
    val itemImage: String,
    val itemName: String,
    val itemPrice: Double,
    val itemQuantity: Int


)