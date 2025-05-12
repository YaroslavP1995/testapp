package com.example.test.ui.di

import com.example.test.ui.data.domain.TestInfoRepository
import com.example.test.ui.data.repo.TestRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface UseCasesModule {

    @Binds
    @Singleton
    abstract fun bindTempRepository(
        tempRepositoryImpl: TestRepositoryImpl
    ): TestInfoRepository
}