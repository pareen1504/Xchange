package com.pd.xchange.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "currency_rates", primaryKeys = ["country_code"])
data class RatesEntity(
    @ColumnInfo("country_code")
    val countryCode: String = "",
    @ColumnInfo("exchange_rates")
    val exchangeRates: String = "0",
)
