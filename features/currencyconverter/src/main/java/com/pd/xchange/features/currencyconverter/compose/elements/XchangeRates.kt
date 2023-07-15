package com.pd.xchange.features.currencyconverter.compose.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.pd.xchange.domain.entity.Currency

@Composable
fun XchangeRates(currencies: () -> List<Currency>) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.testTag("rates_list"),
        state = scrollState,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        itemsIndexed(
            items = currencies(),
            key = { _: Int, item: Currency -> item.countryCode }
        ) { _, items ->
            CurrencyItem { items }
        }
    }
}