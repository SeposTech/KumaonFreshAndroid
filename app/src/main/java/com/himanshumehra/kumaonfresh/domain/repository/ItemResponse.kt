package com.himanshumehra.kumaonfresh.domain.repository

import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.ItemResponse

interface ItemResponse {
    suspend fun getItems(categoryId: Long): ItemResponse
}