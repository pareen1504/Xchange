package com.pd.xchange.domain.usecase.impl

import com.pd.core.prefence.AppPreference
import com.pd.xchange.domain.entity.XchangeRates
import com.pd.xchange.domain.respository.GetRatesRepository
import com.pd.xchange.domain.respository.LocalDbRepository
import com.pd.xchange.domain.utils.DataSource
import com.pd.xchange.jvm.config.AppConfig
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class XchangeRatesUseCaseImplTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val localDbRepository = mockk<LocalDbRepository>()
    private val getRatesRepository = mockk<GetRatesRepository>()
    private val appPreference = mockk<AppPreference>()
    private val appConfig = mockk<AppConfig>()
    private val fakeXchangeRatesDb = XchangeRates(
        rates = mapOf("INR" to BigDecimal.valueOf(100.0)),
        timestamp = System.currentTimeMillis(),
        dataSource = DataSource.LOCAL,
        isStaledData = false
    )

    private val fakeXchangeRatesApi = XchangeRates(
        rates = mapOf("USD" to BigDecimal.valueOf(10.0)),
        timestamp = System.currentTimeMillis(),
        dataSource = DataSource.LOCAL,
        isStaledData = false
    )

    private val xchangeRatesUseCaseImpl = XchangeRatesUseCaseImpl(
        appConfig,
        appPreference,
        localDbRepository,
        getRatesRepository,
        testDispatcher
    )

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `if not data available throws exception`() {
        val xchangeRatesWithNullRates = XchangeRates()
        every { appPreference.isDataStaled } returns false
        every { appConfig.currentTimeMillis } returns System.currentTimeMillis()
        every { appConfig.appId } returns ""
        coEvery { localDbRepository.getAllCurrencyRates() } returns xchangeRatesWithNullRates
        coEvery { getRatesRepository.getLatestRates(appConfig.appId) } returns Result.failure(Exception())

        runTest {
            xchangeRatesUseCaseImpl.execute("INR").first() shouldBe "No data to show"
        }

    }

    @Test
    fun `if local data available and not staled should return saved data`() {
        every { appPreference.isDataStaled } returns false
        every { appConfig.currentTimeMillis } returns System.currentTimeMillis()
        coEvery { localDbRepository.getAllCurrencyRates() } returns fakeXchangeRatesDb

        runTest {
            xchangeRatesUseCaseImpl.execute("INR").first() shouldBe fakeXchangeRatesDb
        }
    }

    @Test
    fun `if local data available and staled should returns repository data`() {
        val dataTimeStampSlot = slot<Long>()
        every { appConfig.appId } returns ""
        every { appConfig.currentTimeMillis } returns System.currentTimeMillis()
        every { appPreference.isDataStaled } returns true
        every { appPreference.dataTimestamp = capture(dataTimeStampSlot) } answers { System.currentTimeMillis() }
        coEvery { localDbRepository.getAllCurrencyRates() } returns fakeXchangeRatesDb
        coEvery { getRatesRepository.getLatestRates(appConfig.appId) } returns Result.success(fakeXchangeRatesApi)
        coEvery { localDbRepository.upsertCurrencyRates(fakeXchangeRatesApi) } returns Unit

        runTest { xchangeRatesUseCaseImpl.execute("INR").first() shouldBe fakeXchangeRatesApi }
    }

    @Test
    fun `if local data available and staled but api fails should return staled data`() {
        val staledDbData = fakeXchangeRatesDb.copy(isStaledData = true)
        every { appConfig.appId } returns ""
        every { appConfig.currentTimeMillis } returns System.currentTimeMillis()
        every { appPreference.isDataStaled } returns true
        coEvery { localDbRepository.getAllCurrencyRates() } returns staledDbData
        coEvery { getRatesRepository.getLatestRates(appConfig.appId) } returns Result.failure(Exception())
        coEvery { localDbRepository.upsertCurrencyRates(fakeXchangeRatesApi) } returns Unit

        runTest {
            xchangeRatesUseCaseImpl.execute("INR").first() shouldBe staledDbData
        }
    }
}