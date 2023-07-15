package com.pd.xchange.domain.respository

import com.pd.xchange.domain.entity.XchangeRates

interface GetRatesRepository {
    suspend fun getLatestRates(appId: String): Result<XchangeRates?>
}