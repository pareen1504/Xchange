package com.pd.xchange.features.currencyconverter.state

import androidx.compose.runtime.Immutable
import com.pd.xchange.domain.entity.XchangeRates
import com.pd.xchange.domain.mapper.XchangeRatesToCurrencyMapper
import com.pd.xchange.features.currencyconverter.data.ConversionData
import com.pd.xchange.features.currencyconverter.data.Error
import com.pd.xchange.features.currencyconverter.data.ErrorType

data class CurrencyConverterState(
    val loading: Boolean,
    val conversionData: ConversionData?,
    val error: Error?
)

sealed class CurrencyConverterIntent {

    object Loading : CurrencyConverterIntent()
    data class LoadLatestRates(
        val baseCountry: String
    ) : CurrencyConverterIntent()

    @Immutable
    data class ConvertRates(
        val amount: String,
        val countryCode: String,
        val xchangeRates: XchangeRates
    ) : CurrencyConverterIntent()

    data class RatesError(val errorType: ErrorType) : CurrencyConverterIntent()
}

sealed class CurrencyConverterAction {
    object ShowLoader : CurrencyConverterAction()


    @Immutable
    data class CurrencyConversionSuccess(
        val xchangeRates: XchangeRates,
        val errorType: ErrorType? = null
    ) : CurrencyConverterAction()

    data class CurrencyConversionError(val errorType: ErrorType) : CurrencyConverterAction()
}

object UiState {
    fun init() = CurrencyConverterState(
        loading = false,
        conversionData = null,
        error = null
    )

    suspend fun processData(
        oldState: CurrencyConverterState,
        action: CurrencyConverterAction
    ): CurrencyConverterState =
        when (action) {
            CurrencyConverterAction.ShowLoader -> oldState.copy(loading = true, error = null)

            is CurrencyConverterAction.CurrencyConversionSuccess -> oldState.copy(
                loading = false,
                conversionData = ConversionData(
                    currencies = XchangeRatesToCurrencyMapper.map(action.xchangeRates),
                    countryCodeList = action.xchangeRates.rates?.map { it.key }.orEmpty(),
                    dataSource = action.xchangeRates.dataSource,
                    areStaledRates = action.xchangeRates.isStaledData
                ),
                error = if (action.xchangeRates.isStaledData) {
                    Error(ErrorType.STALED_DATA)
                } else {
                    null
                }
            )

            is CurrencyConverterAction.CurrencyConversionError -> oldState.copy(
                loading = false,
                error = Error(action.errorType)
            )
        }
}