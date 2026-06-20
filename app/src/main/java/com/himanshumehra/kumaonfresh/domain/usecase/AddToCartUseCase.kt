package com.himanshumehra.kumaonfresh.domain.usecase

import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.AddToCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.AddToCartResponse
import com.himanshumehra.kumaonfresh.domain.repository.CartRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(private val cartRepository: CartRepository) {

    suspend operator fun invoke(addToCart: AddToCart): AddToCartResponse {
        return cartRepository.addToCart(addToCart)
    }
}