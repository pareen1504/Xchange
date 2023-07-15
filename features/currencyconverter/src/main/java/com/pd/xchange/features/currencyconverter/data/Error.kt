package com.pd.xchange.features.currencyconverter.data

private const val NOT_CONNECTED = "⚠️ You aren’t connected to the internet"
private const val STALED_DATA_MESSAGE =
    "⚠️ Unable to fetch updated exchange Rates\nBelow are old exchange rates"
private const val GET_RATES_ERROR_MESSAGE =
    "⚠️ Unable to fetch updated exchange Rates"

data class Error(val errorType: ErrorType)

enum class ErrorType(val message: String?) {
    GET_RATES_ERROR(GET_RATES_ERROR_MESSAGE),
    NO_NETWORK_NO_DATA(NOT_CONNECTED),
    NO_DATA(null),
    STALED_DATA(STALED_DATA_MESSAGE);
}
