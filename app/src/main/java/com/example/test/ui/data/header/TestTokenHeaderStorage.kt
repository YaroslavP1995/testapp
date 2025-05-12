package com.example.test.ui.data.header

import javax.inject.Inject

class TestTokenHeaderStorage @Inject constructor(
    private val tokenRepository: TokenRepository,
) : HeaderStorage {

    override fun getApiHeaders(existingHeaders: Map<String, String>): Map<String, String> {
        val headers = HashMap(existingHeaders)
        headers[Constants.AUTH] = "Bearer ${getCurrentToken()}"
        return headers
    }

    private fun getCurrentToken(): String {
        if (tokenRepository.isTokenExists()) {
            return getToken()
        } else {
            return ""
        }
    }

    private fun getToken(): String {
        val token = tokenRepository.getToken()
        return token
    }
}