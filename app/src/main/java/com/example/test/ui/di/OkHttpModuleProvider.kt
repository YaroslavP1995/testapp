package com.example.test.ui.di

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpModuleProvider {

    private const val TIMEOUT = 50L

    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(getHttpLoggingInterceptor())
            addInterceptor(headerInterceptor)
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
        }.build()
    }

    private fun getHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

}