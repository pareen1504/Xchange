package com.pd.xchange.domain.di

import com.pd.xchange.domain.usecase.ConvertCurrencyUseCase
import com.pd.xchange.domain.usecase.XchangeRatesUseCase
import com.pd.xchange.domain.usecase.impl.ConvertCurrencyUseCaseImpl
import com.pd.xchange.domain.usecase.impl.XchangeRatesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CurrencyConverterUseCaseModule {
    @Binds
    internal abstract fun bindsXchangeRateUseCase(xchangeRatesUseCaseImpl: XchangeRatesUseCaseImpl): XchangeRatesUseCase

    @Binds
    internal abstract fun bindsConvertCurrencyUseCase(convertCurrencyUseCaseImpl: ConvertCurrencyUseCaseImpl): ConvertCurrencyUseCase
}