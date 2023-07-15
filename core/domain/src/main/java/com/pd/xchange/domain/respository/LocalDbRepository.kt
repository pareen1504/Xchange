package com.pd.xchange.domain.respository

import com.pd.xchange.domain.entity.XchangeRates

interface LocalDbRepository {
    suspend fun getAllCurrencyRates(): XchangeRates
    suspend fun upsertCurrencyRates(xchangeRates: XchangeRates)
}