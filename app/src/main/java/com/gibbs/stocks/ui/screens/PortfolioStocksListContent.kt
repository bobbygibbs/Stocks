package com.gibbs.stocks.ui.screens

import android.icu.text.NumberFormat
import android.icu.text.SimpleDateFormat
import android.icu.util.Currency
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gibbs.stocks.R
import com.gibbs.stocks.domain.StockPosition

@Composable
internal fun PortfolioStocksListContent(stocks: List<StockPosition>, modifier: Modifier = Modifier) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)) {
        itemsIndexed(stocks) { index, stock ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stock.symbol,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = stock.name,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = modifier
                        .weight(1f, false)
                        .padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center
                )
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = stock.getFormattedPrice(),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    if (stock.quantity > 0) {
                        Text(
                            text = stringResource(R.string.x_shares, stock.quantity),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = stock.getFormattedTimestamp(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.surfaceContainerLow
                    )
                }
            }
            if (index < stocks.lastIndex) {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

private fun StockPosition.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().also {
        it.currency = Currency.getInstance(currency)
    }.format(tradingPrice)

private fun StockPosition.getFormattedTimestamp(): String =
    SimpleDateFormat("M/dd/yy h:mm:ss aa", Locale.current.platformLocale).format(time * 1000)
