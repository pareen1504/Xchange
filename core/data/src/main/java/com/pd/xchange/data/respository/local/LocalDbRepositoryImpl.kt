package com.pd.xchange.data.respository.local

import com.pd.xchange.data.mapper.DbMapper
import com.pd.xchange.database.dao.CurrencyConverterDao
import com.pd.xchange.domain.entity.XchangeRates
import com.pd.xchange.domain.respository.LocalDbRepository
import com.pd.xchange.domain.utils.DataSource
import javax.inject.Inject

class LocalDbRepositoryImpl @Inject constructor(
    private val currencyConverterDao: CurrencyConverterDao,
) : LocalDbRepository {
    override suspend fun getAllCurrencyRates() =
        DbMapper.mapFromEntity(currencyConverterDao.getCurrencyRates(), DataSource.LOCAL)


    override suspend fun upsertCurrencyRates(xchangeRates: XchangeRates) =
        currencyConverterDao.upsertCurrencyRates(DbMapper.mapToEntity(xchangeRates))
}