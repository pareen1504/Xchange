package com.pd.xchange.domain.entity

import com.pd.xchange.domain.utils.DataSource
import java.math.BigDecimal

data class XchangeRates(
    val rates: Map<String, BigDecimal>? = null,
    val timestamp: Long? = null,
    val dataSource: DataSource = DataSource.LOCAL,
    val isStaledData: Boolean = false
)