package com.pd.xchange.domain.usecase.mapper

import com.pd.xchange.domain.mapper.XchangeRatesToCurrencyMapper
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class XchangeRatesToCurrencyMapperTest {

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `map XchangeRates to currency list`() {
        val fakeRates: Map<String, BigDecimal> = mapOf("INR" to BigDecimal.valueOf(40.0))

        runTest {
            XchangeRatesToCurrencyMapper.map(
                mockk(relaxed = true) {
                    every { rates } returns fakeRates
                }
            ).first().countryCode shouldBe "INR"
        }
    }

    @Test
    fun `if XchangeRates is null then currency list should be empty`() {
        val fakeRates: Map<String, BigDecimal>? = null

        runTest {
            XchangeRatesToCurrencyMapper.map(
                mockk(relaxed = true) {
                    every { rates } returns fakeRates
                }
            ) shouldBe emptyList()
        }
    }
}