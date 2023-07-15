package com.pd.xchange.domain.usecase.impl

import com.pd.xchange.domain.entity.XchangeRates
import com.pd.xchange.domain.usecase.ConvertCurrencyUseCase
import com.pd.xchange.jvm.Dispatcher
import com.pd.xchange.jvm.XchangeDispatchers.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class ConvertCurrencyUseCaseImpl @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : ConvertCurrencyUseCase {
    override fun execute(input: ConvertCurrencyUseCase.Input): Flow<XchangeRates> = flow {
        val amount = input.value.toBigDecimal()
        input.xchangeRates.let {
            val usdRate: BigDecimal = it.rates?.get(input.countryCode)
                ?: throw IllegalArgumentException("No data found for selected currency ${input.countryCode}")
            val rateFactor = amount.divide(usdRate, 50, RoundingMode.HALF_UP)
            emit(
                it.copy(
                    rates = it.rates
                        .filter { rates -> rates.key != input.countryCode }
                        .mapValues { rates -> rates.value * rateFactor }
                )
            )
        }
    }.flowOn(ioDispatcher)
}