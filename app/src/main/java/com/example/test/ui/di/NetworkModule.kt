package com.example.test.ui.di

import android.content.Context
import com.example.test.BuildConfig.BASE_URL
import com.example.test.ui.data.header.HeaderStorage
import com.example.test.ui.data.header.InterceptorHeader
import com.example.test.ui.data.header.TestTokenHeaderStorage
import com.example.test.ui.data.header.TokenRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    @Provides
    @Singleton
    fun provideRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit {
        return builder
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(converterFactory: Converter.Factory): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
    }

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .setDateFormat(TIME_PATTERN)
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        @ApplicationContext context: Context,
    ): OkHttpClient {
        return OkHttpModuleProvider.provideOkHttpClient(headerInterceptor,context)
    }

    @Provides
    @Singleton
    fun provideTokenHeaderStorage(tokenRepository: TokenRepository): HeaderStorage {
        return TestTokenHeaderStorage(tokenRepository)
    }

    @Provides
    @Singleton
    fun provideTokenHeaderInterceptor(headerStorage: HeaderStorage): Interceptor {
        return InterceptorHeader(headerStorage)
    }
}