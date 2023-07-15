package com.pd.xchange.features.currencyconverter.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pd.xchange.features.currencyconverter.compose.elements.CircularLoader
import com.pd.xchange.features.currencyconverter.compose.elements.DropDownData
import com.pd.xchange.features.currencyconverter.compose.elements.ErrorBar
import com.pd.xchange.features.currencyconverter.compose.elements.UserInput
import com.pd.xchange.features.currencyconverter.compose.elements.XchangeRates

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XchangeRatesScreen() {
    val viewModel = hiltViewModel<CurrencyConverterVM>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    ErrorBar(errorMessage = { viewState.error?.errorType?.message })
                    UserInput(
                        dropDownMenuData = { DropDownData(viewState.conversionData?.countryCodeList.orEmpty()) },
                        onValueChanges = { userInput ->
                            viewModel.getRatesForValue(userInput)
                        },
                        selectedCountryCode = { countryCode ->
                            viewModel.getRatesForCountryCode(countryCode)
                        }
                    )
                    CircularLoader { viewState.loading }
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            XchangeRates(currencies = { viewState.conversionData?.currencies.orEmpty() })
        }
    }
}

