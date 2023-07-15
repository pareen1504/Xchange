package com.pd.xchange.domain.mapper

import com.pd.xchange.domain.entity.Currency
import com.pd.xchange.domain.entity.XchangeRates
import com.pd.xchange.jvm.mapper.Mapper
import com.pd.xchange.jvm.utils.NumberExt.roundToFourDecimalPlaces

object XchangeRatesToCurrencyMapper : Mapper<XchangeRates?, List<Currency>?> {
    override suspend fun map(input: XchangeRates?): List<Currency> {
        return input?.rates?.map {
            Currency(
                countryCode = it.key,
                exchangeRate = it.value.roundToFourDecimalPlaces().stripTrailingZeros().toPlainString()
            )
        }.orEmpty()
    }
}
