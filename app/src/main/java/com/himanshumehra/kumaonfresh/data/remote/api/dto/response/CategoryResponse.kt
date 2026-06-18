package com.himanshumehra.kumaonfresh.data.remote.api.dto.response

data class CategoryResponse(
    val status: String,
    val message: String,
    val data: List<CategoryData>?
)

data class CategoryData(
    val categoryImage: String,
    val categoryName: String,
    val CategoryId: Long
)