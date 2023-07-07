package com.ace.mobilecomputing.di


import com.ace.mobilecomputing.domain.services.*
import com.ace.mobilecomputing.domain.services.api.NewsAPIService
import com.ace.mobilecomputing.domain.services.api.SummarizeAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        return NewsAPIService.create()
    }

    @Singleton
    @Provides
    fun provideSummariseService(): SummariseService {
        return SummarizeAPIService.create()
    }
}