package com.pd.xchange.domain.usecase.impl

import com.pd.xchange.domain.usecase.ConvertCurrencyUseCase
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class ConvertCurrencyUseCaseImplTest {
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val convertCurrencyUseCaseImpl = ConvertCurrencyUseCaseImpl(testDispatcher)

    @Before
    fun beforeEach() {

    }

    @After
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun `if input currency code is same to rates so it should be empty`() {
        val fakeRates: Map<String, BigDecimal> = mapOf("INR" to BigDecimal.valueOf(40.0))
        runTest {
            convertCurrencyUseCaseImpl.execute(ConvertCurrencyUseCase.Input("100",
                countryCode = "INR",
                mockk(relaxed = true) {
                    every { rates } returns fakeRates
                }
            )).first().rates shouldBe mapOf()
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `if rates data not available throw exception`() {
        val fakeRates: Map<String, BigDecimal> = mapOf("INR" to BigDecimal.valueOf(40.0))
        runTest {
            convertCurrencyUseCaseImpl.execute(ConvertCurrencyUseCase.Input(
                value = "10", countryCode = "USD",
                mockk(relaxed = true) {
                    every { rates } returns fakeRates
                }
            )).first().rates shouldBe "No data found for selected currency USD"
        }
    }

}