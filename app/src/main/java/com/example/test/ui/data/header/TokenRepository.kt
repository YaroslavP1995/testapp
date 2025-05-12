package com.example.test.ui.data.header

interface TokenRepository {
    fun getToken(): String
    fun deleteToken()
    fun saveToken(token: String)
    fun isTokenExists(): Boolean
}