package com.pd.xchange.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pd.xchange.database.DbConstants
import com.pd.xchange.database.entity.RatesEntity

@Dao
interface CurrencyConverterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCurrencyRates(rateEntities: List<RatesEntity>)

    @Query("SELECT * FROM ${DbConstants.TABLE_NAME_CURRENCY_RATES}")
    suspend fun getCurrencyRates(): List<RatesEntity>?

}