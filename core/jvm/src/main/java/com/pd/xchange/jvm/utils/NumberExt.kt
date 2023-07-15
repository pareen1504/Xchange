package com.pd.xchange.jvm.utils

import java.math.BigDecimal
import java.math.RoundingMode

object NumberExt {
    fun BigDecimal.roundToFourDecimalPlaces(): BigDecimal = setScale(8, RoundingMode.HALF_DOWN)
}