package com.pd.xchange.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class XchangeRatesDTO(
    @Json(name = "rates")
    val rates: Map<String, String>? = null,
    @Json(name = "timestamp")
    val timestamp: Long? = null,
)