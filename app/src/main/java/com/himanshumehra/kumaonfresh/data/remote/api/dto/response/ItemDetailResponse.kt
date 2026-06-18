package com.himanshumehra.kumaonfresh.data.remote.api.dto.response

data class ItemDetailResponse(
    val status: String,
    val message: String,
    val data: ItemDetailData?
)

data class ItemDetailData(
    val itemDescription: String,
    val itemId: Long,
    val itemImage: String,
    val itemName: String,
    val itemPrice: Double,
    val itemQuantity: Int
)

