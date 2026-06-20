package com.himanshumehra.kumaonfresh.domain.usecase

import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CartResponse
import com.himanshumehra.kumaonfresh.domain.repository.CartRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(private val cartRepository: CartRepository) {

    suspend operator fun invoke(userId: String): CartResponse {
        return cartRepository.getCart(userId)
    }
}

