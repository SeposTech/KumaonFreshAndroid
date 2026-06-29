package com.himanshumehra.kumaonfresh.domain.repository

import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CategoryResponse
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.HomeResponse

interface CategoryRepository {
    suspend fun getCategories(): CategoryResponse

    suspend fun fetchHome(): HomeResponse
}