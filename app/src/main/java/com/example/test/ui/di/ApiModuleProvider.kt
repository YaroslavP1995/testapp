package com.example.test.ui.di

import retrofit2.Retrofit

interface ApiModuleProvider <T> {
    fun provideApi(retrofit: Retrofit): T
}