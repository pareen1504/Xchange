package com.pd.xchange.features.currencyconverter.compose.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pd.xchange.jvm.utils.Constants.DEFAULT_COUNTRY_CODE

@Stable
data class DropDownData(val countryCodeList: List<String> = listOf(DEFAULT_COUNTRY_CODE))

@Composable
fun UserInput(
    dropDownMenuData: () -> DropDownData? = { DropDownData() },
    onValueChanges: (value: String) -> Unit,
    selectedCountryCode: (code: String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.background
            )
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {

        AmountField {
            onValueChanges.invoke(it)
        }

        dropDownMenuData()?.let {
            CountryCodeDropDown({ it }) { countryCode ->
                selectedCountryCode.invoke(countryCode)
            }
        }
    }
}