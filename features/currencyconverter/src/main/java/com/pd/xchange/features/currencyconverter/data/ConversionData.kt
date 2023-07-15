package com.pd.xchange.features.currencyconverter.data

import androidx.compose.runtime.Immutable
import com.pd.xchange.domain.entity.Currency
import com.pd.xchange.domain.utils.DataSource

@Immutable
data class ConversionData(
    val currencies: List<Currency> = emptyList(),
    val countryCodeList: List<String> = emptyList(),
    val dataSource: DataSource = DataSource.LOCAL,
    val areStaledRates: Boolean = false
)