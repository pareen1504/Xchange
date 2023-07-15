package com.pd.xchange.data.mapper

import com.pd.xchange.database.entity.RatesEntity
import com.pd.xchange.domain.entity.XchangeRates
import com.pd.xchange.domain.utils.DataSource

object DbMapper {

    fun mapToEntity(input: XchangeRates?): List<RatesEntity> =
        input?.rates?.map {
            RatesEntity(
                countryCode = it.key,
                exchangeRates = it.value.toString()
            )
        }.orEmpty()

    fun mapFromEntity(rates: List<RatesEntity>?, dataSource: DataSource): XchangeRates =
        XchangeRates(
            rates = rates?.associate {
                it.countryCode to it.exchangeRates.toBigDecimal()
            }.orEmpty(),
            dataSource = dataSource
        )

}