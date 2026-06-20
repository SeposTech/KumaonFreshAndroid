package com.himanshumehra.kumaonfresh.data.local.db.datastore

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences: SharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            "secure_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun saveToken(token: String) {
        sharedPreferences.edit {
            putString("jwt_token", token)
        }
    }

    fun saveUserId(userId: String) {
        sharedPreferences.edit {
            putString("user_id", userId)
        }
    }

    fun getToken(): String? {
        return sharedPreferences.getString("jwt_token", null)
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("user_id", null)
    }

    fun clearToken() {
        sharedPreferences.edit { clear() }
    }
}