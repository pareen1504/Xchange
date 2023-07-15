package com.pd.xchange.features.currencyconverter.compose.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pd.core.network.tracker.NetworkMonitor
import com.pd.core.prefence.AppPreference
import com.pd.xchange.domain.entity.XchangeRates
import com.pd.xchange.domain.usecase.ConvertCurrencyUseCase
import com.pd.xchange.domain.usecase.XchangeRatesUseCase
import com.pd.xchange.features.currencyconverter.data.ErrorType
import com.pd.xchange.features.currencyconverter.state.CurrencyConverterAction
import com.pd.xchange.features.currencyconverter.state.CurrencyConverterIntent
import com.pd.xchange.features.currencyconverter.state.UiState
import com.pd.xchange.jvm.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterVM @Inject constructor(
    networkMonitor: NetworkMonitor,
    private val appPreference: AppPreference,
    private val xchangeRatesUseCase: XchangeRatesUseCase,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {

    private var _viewState = MutableStateFlow(UiState.init())
    val viewState = _viewState.asStateFlow()

    private val _countryCode = MutableStateFlow(Constants.DEFAULT_COUNTRY_CODE)
    private val _inputValue = MutableStateFlow(Constants.DEFAULT_VALUE)

    init {
        networkMonitor.isOnline.onEach { isOnline ->
            if (isOnline.not() && isDataEmpty()) {
                process(CurrencyConverterIntent.RatesError(ErrorType.NO_NETWORK_NO_DATA))
            } else {
                process(CurrencyConverterIntent.LoadLatestRates(_countryCode.value))
            }
        }.launchIn(viewModelScope)
    }

    private fun process(intent: CurrencyConverterIntent) {
        viewModelScope.launch {
            when (intent) {
                CurrencyConverterIntent.Loading -> showLoader()
                is CurrencyConverterIntent.LoadLatestRates -> getLatestRates()
                is CurrencyConverterIntent.ConvertRates ->
                    convertCurrency(intent.amount, intent.countryCode, intent.xchangeRates)

                is CurrencyConverterIntent.RatesError -> showError(intent)
            }
        }
    }

    private suspend fun showLoader() {
        if (isDataEmpty() || isDataStaled()) {
            updateViewState(CurrencyConverterAction.ShowLoader)
        }
    }

    private suspend fun showError(intent: CurrencyConverterIntent.RatesError) {
        updateViewState(CurrencyConverterAction.CurrencyConversionError(intent.errorType))
    }

    private fun convertCurrency(
        amount: String = _inputValue.value,
        baseCountryCode: String = _countryCode.value,
        xchangeRates: XchangeRates
    ) {
        convertCurrencyUseCase.execute(
            ConvertCurrencyUseCase.Input(
                value = amount,
                countryCode = baseCountryCode,
                xchangeRates = xchangeRates
            )
        ).onEach { rates ->
            updateViewState(CurrencyConverterAction.CurrencyConversionSuccess(rates))
        }.catch {
            Log.e("convertCurrency", it.message ?: "Something went wrong")
        }.launchIn(viewModelScope)
    }

    private fun getLatestRates() {
        process(CurrencyConverterIntent.Loading)
        xchangeRatesUseCase.execute(_countryCode.value).onEach { xchangeRates ->
            xchangeRates?.let {
                process(
                    CurrencyConverterIntent.ConvertRates(
                        _inputValue.value,
                        _countryCode.value,
                        xchangeRates
                    )
                )
            } ?: run {
                process(CurrencyConverterIntent.RatesError(ErrorType.NO_DATA))
            }
        }.catch {
            updateViewState(CurrencyConverterAction.CurrencyConversionError(ErrorType.GET_RATES_ERROR))
        }.launchIn(viewModelScope)
    }

    private suspend fun updateViewState(action: CurrencyConverterAction) {
        _viewState.update { state ->
            UiState.processData(
                state,
                action,
            )
        }
    }

    fun getRatesForCountryCode(countryCode: String) {
        _countryCode.value = countryCode
        process(CurrencyConverterIntent.LoadLatestRates(countryCode))
    }

    fun getRatesForValue(userInput: String = Constants.DEFAULT_VALUE) {
        _inputValue.value = userInput
        process(CurrencyConverterIntent.LoadLatestRates(_countryCode.value))
    }

    private fun isDataEmpty() = appPreference.isDataEmpty
    private fun isDataStaled() = appPreference.isDataStaled
}