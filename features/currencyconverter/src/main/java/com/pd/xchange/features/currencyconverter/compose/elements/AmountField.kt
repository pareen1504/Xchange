package com.pd.xchange.features.currencyconverter.compose.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pd.xchange.jvm.utils.Constants.DEFAULT_VALUE
import com.pd.xchange.jvm.utils.checkIsValidInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountField(
    onValueChanges: (value: String) -> Unit
) {
    var value by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp, horizontal = 6.dp),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        value = value,
        textStyle = MaterialTheme.typography.headlineMedium.copy(
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.End,
        ),
        onValueChange = {
            if (it.checkIsValidInput()) {
                value = it
                onValueChanges.invoke(it)
            }
        },
        maxLines = 1,
        singleLine = true,
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = DEFAULT_VALUE,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                    textAlign = TextAlign.End,
                )
            )
        }
    )
}