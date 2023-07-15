package com.pd.xchange.features.currencyconverter.compose.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pd.xchange.domain.entity.Currency

@Composable
fun CurrencyItem(item: () -> Currency) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 3.dp, horizontal = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onTertiary,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .weight(1f)
                    .fillMaxWidth(),
                text = item().countryCode,
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .weight(1f)
                    .fillMaxWidth(),
                text = item().exchangeRate,
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }

    }
}