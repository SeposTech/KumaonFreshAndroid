package com.himanshumehra.kumaonfresh.presentation.ui.login

import com.himanshumehra.kumaonfresh.MainDispatcherRule
import com.himanshumehra.kumaonfresh.data.local.db.datastore.TokenManager
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.LoginResponse
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.UserData
import com.himanshumehra.kumaonfresh.domain.usecase.LoginUseCase
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class LoginViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var tokenManager: TokenManager
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        loginUseCase = mockk()
        tokenManager = mockk(relaxed = true)

        viewModel = LoginViewModel(
            loginUseCase,
            tokenManager
        )
    }

    @Test
    fun `onEmailChange should update email`() {
        viewModel.onEmailChange("test@gmail.com")

        assertEquals(
            "test@gmail.com",
            viewModel.uiState.value.email
        )
    }

    @Test
    fun `onPasswordChange should update password`() {
        viewModel.onPasswordChange("password")

        assertEquals(
            "password",
            viewModel.uiState.value.password
        )
    }

    @Test
    fun `login should show validation error when email is blank`() = runTest {
        viewModel.onEmailChange("")
        viewModel.onPasswordChange("password")

        viewModel.login()

        assertEquals(
            "Please enter email and password",
            viewModel.uiState.value.error
        )
    }

    @Test
    fun `login should set success when credentials are valid`() = runTest {
        val userData = UserData(
            id = "1",
            name = "John Doe",
            email = "john@example.com",
            phone = "1234567890",
            token = "abc123"
        )

        val response = LoginResponse(
            status = "success",
            message = "Login successful",
            data = userData
        )

        coEvery {
            tokenManager.saveToken(any())
        } just Runs

        coEvery {
            loginUseCase(any())
        } answers {
            println("UseCase Called")
            response
        }

        viewModel.onEmailChange("john@example.com")
        viewModel.onPasswordChange("password123")

        viewModel.login()

        advanceUntilIdle()

        assertEquals(userData, viewModel.uiState.value.success)
        assertEquals(null, viewModel.uiState.value.error)

        coVerify {
            tokenManager.saveToken("abc123")
        }
    }
}