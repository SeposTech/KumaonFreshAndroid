package com.himanshumehra.kumaonfresh.presentation.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshumehra.kumaonfresh.data.local.db.datastore.TokenManager
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.LoginRequest
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.UserData
import com.himanshumehra.kumaonfresh.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    data class LoginUiState(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val error: String? = null,
        val success: UserData? = null
    )

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email, error = null)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password, error = null)
    }


    fun login() {
        val currentState = _uiState.value
        val email = currentState.email.trim()
        val password = currentState.password

        if (email.isBlank() || password.isBlank()) {
            _uiState.value = currentState.copy(error = "Please enter email and password")
            return
        }

        viewModelScope.launch {
            _uiState.value = currentState.copy(
                isLoading = true,
                error = null,
                success = null
            )

            try {
                val result = loginUseCase(
                    LoginRequest(
                        email = email,
                        password = password
                    )
                )

                val userData = result.data
                if (userData != null) {
                    Log.d("LoginViewModel", "response: $userData")
                    tokenManager.saveToken(userData.token)
                    _uiState.value = currentState.copy(
                        isLoading = false,
                        success = userData,
                        error = null
                    )
                } else {
                    _uiState.value = currentState.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Login error: ${e.message}", e)
                _uiState.value = currentState.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }
}