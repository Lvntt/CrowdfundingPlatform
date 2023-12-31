package com.example.crowdfundingplatform.data.datasource

import com.example.crowdfundingplatform.data.remote.model.TokenType

interface TokenDataSource {

    fun fetchToken(tokenType: TokenType): String?

    fun saveToken(token: String, tokenType: TokenType)

}