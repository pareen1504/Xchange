package com.pd.xchange.domain.usecase

import com.pd.xchange.domain.entity.XchangeRates
import com.pd.xchange.jvm.usecase.SuspendingUseCase
import kotlinx.coroutines.flow.Flow

interface ConvertCurrencyUseCase :
    SuspendingUseCase<ConvertCurrencyUseCase.Input, Flow<XchangeRates>> {
    data class Input(val value: String, val countryCode: String, val xchangeRates: XchangeRates)
}