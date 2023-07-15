package com.pd.xchange.database.di

import com.pd.xchange.database.XchangeDatabase
import com.pd.xchange.database.dao.CurrencyConverterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesCurrencyConverterDao(
        database: XchangeDatabase
    ): CurrencyConverterDao = database.currencyConverterDao()
}