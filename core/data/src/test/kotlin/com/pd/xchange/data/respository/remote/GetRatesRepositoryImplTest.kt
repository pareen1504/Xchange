package com.pd.xchange.data.respository.remote

import com.pd.xchange.data.api.CurrencyConverterApiService
import com.pd.xchange.data.dto.XchangeRatesDTO
import com.pd.xchange.domain.entity.XchangeRates
import com.pd.xchange.domain.utils.DataSource
import com.squareup.moshi.Moshi
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.math.BigDecimal

class GetRatesRepositoryImplTest {
    private val currencyConverterApiService = mockk<CurrencyConverterApiService>()
    private val moshi = Moshi.Builder().build()
    private lateinit var mockWebServer: MockWebServer
    private lateinit var getRatesRepositoryImpl: GetRatesRepositoryImpl

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        getRatesRepositoryImpl = GetRatesRepositoryImpl(currencyConverterApiService, moshi)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `api should response success result`() = runTest {
        val timestamp = System.currentTimeMillis()
        val fakeRatesDTO = mapOf("INR" to "82")
        val fakeRates = mapOf("INR" to BigDecimal.valueOf(82))
        val fakeXchangeRatesDTO = XchangeRatesDTO(
            rates = fakeRatesDTO,
            timestamp = timestamp,
        )

        val fakeXchangeRates = XchangeRates(
            rates = fakeRates,
            timestamp = timestamp,
            dataSource = DataSource.REMOTE,
            isStaledData = false
        )

        coEvery {
            currencyConverterApiService.getLatest("")
        } returns Response.success(fakeXchangeRatesDTO)

        getRatesRepositoryImpl.getLatestRates("") shouldBe Result.success(fakeXchangeRates)
    }

    @Test(expected = IllegalStateException::class)
    fun `api should response error result`() = runTest {
        coEvery {
            currencyConverterApiService.getLatest("")
        } returns Response.success(mockk(relaxed = true) {
            every { rates } returns mapOf()
        })

        getRatesRepositoryImpl.getLatestRates("") shouldBe Result.failure<XchangeRates>(IllegalStateException("")).getOrThrow()

    }

}