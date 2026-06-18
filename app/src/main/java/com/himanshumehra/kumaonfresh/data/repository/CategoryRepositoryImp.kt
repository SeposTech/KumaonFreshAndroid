package com.himanshumehra.kumaonfresh.data.repository

import com.himanshumehra.kumaonfresh.data.remote.api.ApiService
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CategoryResponse
import com.himanshumehra.kumaonfresh.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImp @Inject constructor(private val apiService: ApiService) :
    CategoryRepository {
    override suspend fun getCategories(): CategoryResponse {
        return apiService.getCategory()
    }
}