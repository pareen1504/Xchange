package com.pd.xchange.data.di

import com.pd.xchange.data.api.CurrencyConverterApiService
import com.pd.xchange.network.creator.ApiServiceCreator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrencyConverterApiModule {

    @Singleton
    @Provides
    fun provideCurrencyConverterApiService(
        apiServiceCreator: ApiServiceCreator
    ): CurrencyConverterApiService {
        return apiServiceCreator.create(serviceClass = CurrencyConverterApiService::class.java)
    }
}
