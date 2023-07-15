package com.pd.xchange.features.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pd.core.palette.ui.theme.XchangeTheme
import com.pd.xchange.features.currencyconverter.compose.screens.XchangeRatesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConverter : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XchangeTheme { XchangeRatesScreen() }
        }
    }
}