package com.himanshumehra.kumaonfresh.data.remote.api.interceptor

import android.util.Log
import com.himanshumehra.kumaonfresh.data.local.db.datastore.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenManager.getToken()
        Log.d("TOKEN_CHECK", "Token: $token")
        val request = chain.request()
            .newBuilder()
            .apply {
                token?.let {
                    addHeader("Authorization", "Bearer $it")
                }
            }
            .build()

        return chain.proceed(request)
    }
}