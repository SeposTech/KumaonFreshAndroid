package com.himanshumehra.kumaonfresh.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CategoryData
import com.himanshumehra.kumaonfresh.domain.usecase.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val categoryUseCase: CategoryUseCase) :
    ViewModel() {

    sealed interface UiState {
        object Idle : UiState
        object Loading : UiState
        data class Success(val message: String, val data: List<CategoryData>?) : UiState
        data class Error(val error: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    init {
        viewModelScope.launch {

            try {
                val result = categoryUseCase.invoke()
                if (result.data != null) {
                    _uiState.value = UiState.Success(
                        message = result.message ?: "Categories fetched successfully",
                        data = result.data
                    )
                } else {
                    _uiState.value = UiState.Error(
                        error = result.message ?: "Failed to fetch categories"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    error = e.message ?: "An unexpected error occurred"
                )
            }

        }
    }
}

