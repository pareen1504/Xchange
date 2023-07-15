package com.pd.xchange.features.currencyconverter.compose.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorBar(errorMessage: () -> String?) {
    errorMessage()?.let {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = MaterialTheme.colorScheme.error)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = it,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onError
                )
            )
        }
    }
}
