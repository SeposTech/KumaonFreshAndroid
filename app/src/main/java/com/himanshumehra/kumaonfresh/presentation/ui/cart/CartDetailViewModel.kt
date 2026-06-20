package com.himanshumehra.kumaonfresh.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshumehra.kumaonfresh.data.local.db.datastore.TokenManager
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CartDetailResponse
import com.himanshumehra.kumaonfresh.domain.usecase.GetCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface CartDetailUiState {
    data object Idle : CartDetailUiState
    data object Loading : CartDetailUiState
    data class Success(val cart: CartDetailResponse) : CartDetailUiState
    data class Error(val message: String) : CartDetailUiState
}

@HiltViewModel
class CartDetailViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<CartDetailUiState>(CartDetailUiState.Idle)
    val uiState: StateFlow<CartDetailUiState> = _uiState.asStateFlow()

    fun loadCart() {
        val userId = tokenManager.getUserId()
        if (userId.isNullOrEmpty()) {
            _uiState.value = CartDetailUiState.Error("User id not found")
            return
        }

        viewModelScope.launch {
            _uiState.value = CartDetailUiState.Loading
            try {
                val cart = getCartUseCase(userId)
                _uiState.value = CartDetailUiState.Success(cart)
            } catch (e: Exception) {
                _uiState.value = CartDetailUiState.Error(
                    e.message ?: "Failed to load cart"
                )
            }
        }
    }
}