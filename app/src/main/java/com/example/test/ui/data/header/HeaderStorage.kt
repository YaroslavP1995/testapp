package com.example.test.ui.data.header

interface HeaderStorage {
    fun getApiHeaders(existingHeaders: Map<String, String>): Map<String, String>
}