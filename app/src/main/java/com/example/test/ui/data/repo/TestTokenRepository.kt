package com.example.test.ui.data.repo

import android.content.Context
import com.example.test.ui.base.extension.get
import com.example.test.ui.base.extension.put
import com.example.test.ui.base.extension.remove
import com.example.test.ui.data.header.TokenRepository
import javax.inject.Inject

class TestTokenRepository  @Inject constructor(
    context: Context,
) : TokenRepository {

    private val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    override fun getToken(): String {
        return sharedPreferences.get(TOKEN, "") ?: ""
    }

    override fun deleteToken() {
        sharedPreferences.remove(TOKEN)
    }

    override fun saveToken(token: String) {
        sharedPreferences.put(TOKEN, token)
    }

    override fun isTokenExists(): Boolean {
        return getToken().isNotEmpty()
    }


    companion object {
        private const val FILE_NAME = "AUTH_TOKENS"
        private const val TOKEN = "TOKEN"

    }
}