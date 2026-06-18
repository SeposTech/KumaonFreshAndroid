package com.himanshumehra.kumaonfresh.domain.usecase

import com.himanshumehra.kumaonfresh.domain.repository.ItemResponse
import javax.inject.Inject

class ItemUseCase @Inject constructor(val itemResponse: ItemResponse) {

    suspend operator fun invoke(categoryId: Long) = itemResponse.getItems(categoryId)
}