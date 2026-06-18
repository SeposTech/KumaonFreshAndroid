package com.himanshumehra.kumaonfresh.data.repository

import com.himanshumehra.kumaonfresh.data.remote.api.ApiService
import com.himanshumehra.kumaonfresh.domain.repository.ItemResponse
import javax.inject.Inject

class ItemRepositoryImp @Inject constructor(private val apiService: ApiService) : ItemResponse {
    override suspend fun getItems(categoryId: Long): com.himanshumehra.kumaonfresh.data.remote.api.dto.response.ItemResponse {
        return apiService.getItems(categoryId)
    }
}