package com.pd.xchange.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pd.xchange.database.dao.CurrencyConverterDao
import com.pd.xchange.database.entity.RatesEntity

@Database(entities = [RatesEntity::class], version = 1)
abstract class XchangeDatabase : RoomDatabase() {
    abstract fun currencyConverterDao(): CurrencyConverterDao
}