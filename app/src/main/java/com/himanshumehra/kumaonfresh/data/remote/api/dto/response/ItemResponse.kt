package com.himanshumehra.kumaonfresh.data.remote.api.dto.response

data class ItemResponse(
    val status: String,
    val message: String,
    val data: List<Item>?
)