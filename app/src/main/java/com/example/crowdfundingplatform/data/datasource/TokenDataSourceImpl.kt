package com.example.crowdfundingplatform.data.datasource

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.data.remote.model.TokenType

class TokenDataSourceImpl(context: Context) : TokenDataSource {

    private val masterKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        Constants.TOKEN_PREFERENCES,
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun fetchToken(tokenType: TokenType): String? {
        return when (tokenType) {
            TokenType.ACCESS -> sharedPreferences.getString(Constants.USER_ACCESS_TOKEN, null)
            TokenType.REFRESH -> sharedPreferences.getString(Constants.USER_REFRESH_TOKEN, null)
        }
    }

    override fun saveToken(token: String, tokenType: TokenType) {
        when (tokenType) {
            TokenType.ACCESS -> {
                sharedPreferences.edit()
                    .putString(Constants.USER_ACCESS_TOKEN, token)
                    .apply()
            }
            TokenType.REFRESH -> {
                sharedPreferences.edit()
                    .putString(Constants.USER_REFRESH_TOKEN, token)
                    .apply()
            }
        }
    }

}