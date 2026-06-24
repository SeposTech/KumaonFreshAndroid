package com.himanshumehra.kumaonfresh.domain.usecase

import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.AddQuantityToCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.QuantityAddRemoveResponse
import com.himanshumehra.kumaonfresh.domain.repository.CartRepository
import javax.inject.Inject

class AddQuantityToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(
        addQuantityToCart: AddQuantityToCart
    ): QuantityAddRemoveResponse {
        return cartRepository.addQuantityToCart(addQuantityToCart)
    }
}