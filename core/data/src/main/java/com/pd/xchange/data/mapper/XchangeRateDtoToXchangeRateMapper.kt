package com.pd.xchange.data.mapper

import com.pd.xchange.data.dto.XchangeRatesDTO
import com.pd.xchange.domain.entity.XchangeRates
import com.pd.xchange.domain.utils.DataSource
import com.pd.xchange.jvm.mapper.Mapper

object XchangeRateDtoToXchangeRateMapper : Mapper<XchangeRatesDTO, XchangeRates> {
    override suspend fun map(input: XchangeRatesDTO): XchangeRates =
        XchangeRates(
            rates = input.rates?.mapValues { it.value.toBigDecimal() }.orEmpty(),
            timestamp = input.timestamp,
            dataSource = DataSource.REMOTE
        )
}