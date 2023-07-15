package com.pd.xchange.data.respository.local

import com.pd.xchange.database.dao.CurrencyConverterDao
import com.pd.xchange.database.entity.RatesEntity
import com.pd.xchange.domain.entity.XchangeRates
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class LocalDbRepositoryImplTest {

    private val currencyConverterDao = mockk<CurrencyConverterDao>()
    private val localDbRepositoryImpl = LocalDbRepositoryImpl(currencyConverterDao)
    val ratesEntity = RatesEntity(
        countryCode = "INR",
        exchangeRates = "200"
    )

    var ratesEntityList: List<RatesEntity>? = null


    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `get empty rates when getCurrencyRates returns null`() {
        val fakerates = XchangeRates(
            rates = mapOf()
        )
        coEvery { currencyConverterDao.getCurrencyRates() } returns ratesEntityList
        runTest {
            localDbRepositoryImpl.getAllCurrencyRates() shouldBe fakerates
        }
    }

    @Test
    fun `get saved rates when getCurrencyRates returns data list`() {
        val fakerates = XchangeRates(
            rates = mapOf<String,BigDecimal>("INR" to  BigDecimal.valueOf(200))
        )
        coEvery { currencyConverterDao.getCurrencyRates() } returns listOf(ratesEntity)
        runTest {
            localDbRepositoryImpl.getAllCurrencyRates() shouldBe fakerates
        }
    }


    @Test
    fun `save currency rates list`() {
        val fakeRates = mapOf<String,BigDecimal>("INR" to  BigDecimal.valueOf(200))
        coEvery { currencyConverterDao.upsertCurrencyRates(listOf(ratesEntity)) } returns Unit

        runTest {
            localDbRepositoryImpl.upsertCurrencyRates(mockk(relaxed = true) {
                every { rates } returns fakeRates
            }) shouldBe Unit
        }
    }
}