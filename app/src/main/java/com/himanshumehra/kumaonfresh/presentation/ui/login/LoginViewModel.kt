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

    sealed interface UiState {
        object Idle : UiState
        object Loading : UiState
        data class Success(val message: String, val data: UserData) : UiState
        data class Error(val error: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val result = loginUseCase(
                    LoginRequest(
                        email = email,
                        password = password
                    )
                )
                if (result.data != null) {
                    Log.d("TAG", "response: ${result.data}")
                    tokenManager.saveToken(result.data.token)
                    _uiState.value = UiState.Success(
                        message = result.message ?: "Login successful",
                        data = result.data
                    )
                } else {
                    _uiState.value = UiState.Error(
                        error = result.message ?: "Login failed"
                    )
                }
            } catch (e: Exception) {
                Log.e("TAG", "Login error: ${e.message}")
                _uiState.value = UiState.Error(
                    error = e.message ?: "An unexpected error occurred"
                )
            }


        }

    }

}