package com.himanshumehra.kumaonfresh.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshumehra.kumaonfresh.data.local.db.datastore.TokenManager
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.AddToCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.AddToCartResponse
import com.himanshumehra.kumaonfresh.domain.usecase.AddToCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToCartViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    sealed interface UiState {
        object Idle : UiState
        object Loading : UiState
        data class Success(val message: String, val data: AddToCartResponse) : UiState
        data class Error(val error: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun addToCart(itemId: String, itemQuantity: Int, itemPrice: Double) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val result = addToCartUseCase(
                    AddToCart(
                        userId = tokenManager.getUserId(),
                        itemId = itemId,
                        quantity = itemQuantity,
                        itemPrice = itemPrice
                    )
                )
                _uiState.value = UiState.Success(
                    message = result.message,
                    data = result
                )
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Idle
    }
}


