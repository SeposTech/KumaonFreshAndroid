package com.himanshumehra.kumaonfresh.presentation.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshumehra.kumaonfresh.data.local.db.datastore.TokenManager
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.CreateUserRequest
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CreateUserData
import com.himanshumehra.kumaonfresh.domain.usecase.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {


    sealed interface UiState {
        object Idle : UiState
        object Loading : UiState
        data class Success(val message: String, val data: CreateUserData) : UiState
        data class Error(val error: String) : UiState
    }

    val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun createUser(
        name: String,
        email: String,
        phone: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val result = createUserUseCase(
                    CreateUserRequest(
                        name = name,
                        email = email,
                        phone = phone,
                        password = password
                    )
                )
                if (result.data != null) {
                    tokenManager.saveToken(result.data.token)
                    _uiState.value = UiState.Success(
                        message = result.message ?: "User created successfully",
                        data = result.data
                    )
                } else {
                    _uiState.value = UiState.Error(
                        error = result.message ?: "User creation failed"
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

