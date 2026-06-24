package com.himanshumehra.kumaonfresh.domain.usecase

import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.RemoveQuantityFromCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.QuantityAddRemoveResponse
import com.himanshumehra.kumaonfresh.domain.repository.CartRepository
import javax.inject.Inject

class RemoveQuantityFromCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(
        removeQuantityFromCart: RemoveQuantityFromCart
    ): QuantityAddRemoveResponse {
        return cartRepository.removeQuantityFromCart(removeQuantityFromCart)
    }
}