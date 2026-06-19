package com.himanshumehra.kumaonfresh.data.repository

import com.himanshumehra.kumaonfresh.data.remote.api.ApiService
import com.himanshumehra.kumaonfresh.data.remote.api.dto.request.LoginRequest
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.LoginResponse
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.UserData
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import org.mockito.kotlin.verify
import org.mockito.kotlin.doAnswer
import java.io.IOException

class LoginRepositoryImpTest {

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var loginRepository: LoginRepositoryImp

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        loginRepository = LoginRepositoryImp(apiService)
    }  // ← CLOSE setUp here

    @Test
    fun `login should return LoginResponse when api call is successful`() = runTest {
        val loginRequest = LoginRequest("test@example.com", "password")
        val expectedResponse = LoginResponse(
            status = "success",
            message = "Login successful",
            data = UserData("1", "Test User", "test@example.com", "1234567890", "token")
        )
        whenever(apiService.loginUser(loginRequest)).thenReturn(expectedResponse)

        val result = loginRepository.login(loginRequest)

        assertEquals(expectedResponse, result)
        verify(apiService).loginUser(loginRequest)
    }  // ← CLOSE test here

    @Test(expected = Exception::class)
    fun `login should throw exception when api call fails`() = runTest {
        val loginRequest = LoginRequest("test@example.com", "password")
        whenever(apiService.loginUser(loginRequest)).thenThrow(RuntimeException("Internal Server Error"))

        loginRepository.login(loginRequest)
    }

    @Test
    fun `login should return failure response when credentials are incorrect`() = runTest {
        val loginRequest = LoginRequest("wrong@example.com", "wrongpass")
        val expectedResponse = LoginResponse(
            status = "error",
            message = "Invalid credentials",
            data = null
        )
        whenever(apiService.loginUser(loginRequest)).thenReturn(expectedResponse)

        val result = loginRepository.login(loginRequest)

        assertEquals(expectedResponse, result)
        assertEquals("error", result.status)
        assertEquals(null, result.data)
        verify(apiService).loginUser(loginRequest)
    }

    @Test
    fun `login should handle empty request fields when api returns error`() = runTest {
        val loginRequest = LoginRequest("", "")
        val expectedResponse = LoginResponse(
            status = "error",
            message = "Validation failed",
            data = null
        )
        whenever(apiService.loginUser(loginRequest)).thenReturn(expectedResponse)

        val result = loginRepository.login(loginRequest)

        assertEquals(expectedResponse, result)
        verify(apiService).loginUser(loginRequest)
    }

    @Test(expected = IOException::class)
    fun `login should throw IOException when network is unavailable`() = runTest {
        val loginRequest = LoginRequest("test@example.com", "password")
        doAnswer { throw IOException("No Internet") }.whenever(apiService).loginUser(loginRequest)

        loginRepository.login(loginRequest)
    }
}