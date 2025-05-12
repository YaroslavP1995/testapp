package com.example.test.ui.di

import android.content.Context
import android.content.SharedPreferences
import com.example.test.R
import com.example.test.ui.data.header.TokenRepository
import com.example.test.ui.data.repo.TestTokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTokenRepository(@ApplicationContext context: Context): TokenRepository {
        return TestTokenRepository(context)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }
}