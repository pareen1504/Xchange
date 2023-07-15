package com.pd.xchange.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorDto(
    @Json(name = "error") val error: Boolean?,
    @Json(name = "status") val status: Int?,
    @Json(name = "message") val message: String?,
    @Json(name = "description") val description: String?
)