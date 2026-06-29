package com.himanshumehra.kumaonfresh.domain.usecase

import com.himanshumehra.kumaonfresh.domain.repository.CategoryRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {
    suspend fun fetchHome() = categoryRepository.fetchHome()
}