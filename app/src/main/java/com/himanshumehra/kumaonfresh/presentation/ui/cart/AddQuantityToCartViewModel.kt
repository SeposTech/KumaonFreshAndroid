package com.himanshumehra.kumaonfresh.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshumehra.kumaonfresh.data.local.db.datastore.TokenManager
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.AddQuantityToCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.RemoveQuantityFromCart
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.QuantityAddRemoveResponse
import com.himanshumehra.kumaonfresh.domain.usecase.AddQuantityToCartUseCase
import com.himanshumehra.kumaonfresh.domain.usecase.RemoveQuantityFromCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddQuantityToCartViewModel @Inject constructor(
    private val addQuantityToCartUseCase: AddQuantityToCartUseCase,
    private val removeQuantityFromCartUseCase: RemoveQuantityFromCartUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    sealed interface UiState {
        object Idle : UiState
        object Loading : UiState
        data class Success(
            val message: String,
            val data: QuantityAddRemoveResponse
        ) : UiState

        data class Error(val error: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun addQuantityToCart(itemId: String, quantity: Int) {
        viewModelScope.launch {

            _uiState.value = UiState.Loading

            try {
                val result = addQuantityToCartUseCase(
                    AddQuantityToCart(
                        userId = tokenManager.getUserId(),
                        itemId = itemId,
                        quantity = quantity
                    )
                )

                _uiState.value = UiState.Success(
                    message = result.message,
                    data = result
                )
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "Unknown Error"
                )
            }
        }
    }

    fun removeQuantityFromCart(itemId: String, quantity: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val result = removeQuantityFromCartUseCase(
                    RemoveQuantityFromCart(
                        userId = tokenManager.getUserId(),
                        itemId = itemId,
                        quantity = quantity
                    )
                )

                _uiState.value = UiState.Success(
                    message = result.message,
                    data = result
                )
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "Unknown Error"
                )
            }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Idle
    }
}