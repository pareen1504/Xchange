package com.pd.xchange.features.currencyconverter.compose.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pd.xchange.jvm.utils.Constants.DEFAULT_COUNTRY_CODE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodeDropDown(
    dropDownMenuData: () -> DropDownData,
    selectedCountryCode: (code: String) -> Unit
) {
    val state = rememberLazyListState()
    var isExpanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(DEFAULT_COUNTRY_CODE) }
    var selectedIndex by remember {
        mutableStateOf(
            dropDownMenuData().countryCodeList.indexOf(
                DEFAULT_COUNTRY_CODE
            )
        )
    }

    ExposedDropdownMenuBox(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxWidth(fraction = 0.27f)
            .height(50.dp),
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = !isExpanded
        }
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isExpanded
                )
            },
            textStyle = TextStyle(
                textAlign = TextAlign.Center
            ),
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .width(100.dp)
                    .height(400.dp),
                state = state
            ) {
                itemsIndexed(
                    items = dropDownMenuData().countryCodeList,
                    key = { index: Int, countryCode: String -> "${index}_$countryCode" },
                ) { index: Int, countryCode: String ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 16.dp)
                            .selectable(
                                selected = selectedIndex == index,
                                onClick = {
                                    selectedIndex =
                                        if (selectedIndex == index) return@selectable else index
                                    isExpanded = false
                                    selectedOptionText =
                                        dropDownMenuData().countryCodeList[selectedIndex]
                                    selectedCountryCode.invoke(selectedOptionText)
                                }
                            ),
                        text = countryCode,
                        style = TextStyle(
                            textAlign = TextAlign.Start
                        )
                    )
                }
            }
        }
    }
}