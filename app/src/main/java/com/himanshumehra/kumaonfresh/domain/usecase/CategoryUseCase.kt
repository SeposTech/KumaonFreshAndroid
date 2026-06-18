package com.himanshumehra.kumaonfresh.domain.usecase

import com.himanshumehra.kumaonfresh.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {

    suspend operator fun invoke() = categoryRepository.getCategories()
}