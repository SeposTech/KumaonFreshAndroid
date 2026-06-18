package com.himanshumehra.kumaonfresh.domain.repository

import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CategoryResponse

interface CategoryRepository {
    suspend fun getCategories(): CategoryResponse
}