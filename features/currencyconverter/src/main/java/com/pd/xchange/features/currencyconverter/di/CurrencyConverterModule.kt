package com.pd.xchange.features.currencyconverter.di

import com.pd.xchange.domain.di.CurrencyConverterUseCaseModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module(includes = [CurrencyConverterUseCaseModule::class])
@InstallIn(ViewModelComponent::class)
internal object CurrencyConverterModule