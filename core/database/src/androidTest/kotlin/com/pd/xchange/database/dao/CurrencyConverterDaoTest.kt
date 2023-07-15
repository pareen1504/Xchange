package com.pd.xchange.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.pd.xchange.database.XchangeDatabase
import com.pd.xchange.database.entity.RatesEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CurrencyConverterDaoTest {
    private lateinit var currencyConverterDao: CurrencyConverterDao
    private lateinit var db: XchangeDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, XchangeDatabase::class.java).build()
        currencyConverterDao = db.currencyConverterDao()
    }

    @Test
    fun get_all_currency_rates_list_should_be_save_rates_list() = runTest {
        val ratesEntityList = listOf(
            RatesEntity(
                countryCode = "INR",
                exchangeRates = "100.0"
            )
        )

        currencyConverterDao.upsertCurrencyRates(ratesEntityList)
        val actualResult = currencyConverterDao.getCurrencyRates()

        assertEquals(
            actualResult,
            ratesEntityList
        )

    }

    @Test
    fun get_all_currency_rates_should_be_empty_() = runTest {
        val rates = currencyConverterDao.getCurrencyRates()
        assertEquals(
            rates,
            emptyList<RatesEntity>()
        )
    }
}