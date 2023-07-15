package com.pd.xchange.data.di

import com.pd.xchange.data.respository.local.LocalDbRepositoryImpl
import com.pd.xchange.data.respository.remote.GetRatesRepositoryImpl
import com.pd.xchange.domain.respository.GetRatesRepository
import com.pd.xchange.domain.respository.LocalDbRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CurrencyConverterRepositoryModule {

    @Binds
    internal abstract fun bindCurrencyConverterRepository(
        getRatesRepositoryImpl: GetRatesRepositoryImpl
    ): GetRatesRepository

    @Binds
    internal abstract fun bindLocalDbRepository(
        localDbRepositoryImpl: LocalDbRepositoryImpl
    ): LocalDbRepository
}
