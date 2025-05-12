package com.example.test.ui.di

import com.example.test.ui.data.api.PartizanApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class InfoApiModule : ApiModuleProvider<PartizanApi> {
    @Singleton
    @Provides
    override fun provideApi(retrofit: Retrofit): PartizanApi {
        return retrofit.create(PartizanApi::class.java)
    }
}