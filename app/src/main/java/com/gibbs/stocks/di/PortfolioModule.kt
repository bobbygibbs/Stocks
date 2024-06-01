package com.gibbs.stocks.di

import com.gibbs.stocks.data.PortfolioService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class PortfolioModule {
    @Provides
    fun providePortfolioService(retrofit: Retrofit): PortfolioService =
        retrofit.create(PortfolioService::class.java)

    @Provides
    @IoCoroutineDispatcher
    fun provideIoCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}