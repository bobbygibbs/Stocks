package com.gibbs.stocks.ui.screens

import android.icu.text.NumberFormat
import android.icu.text.SimpleDateFormat
import android.icu.util.Currency
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gibbs.stocks.domain.PortfolioStocksViewModel
import com.gibbs.stocks.domain.StockPosition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PortfolioStocksScreen() {

    val viewModel: PortfolioStocksViewModel = viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = "My Portfolio") })
        },
        content = { contentPadding ->
            val state = viewModel.state.collectAsState().value

            PullToRefreshBox(
                isRefreshing = state.isLoading,
                onRefresh = viewModel::refresh,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                state.stockPositions?.let { stocks ->
                    PortfolioStocksContent(stocks)
                } ?: run {
                    PortfolioStocksError()
                }

                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    )
}

@Composable
private fun PortfolioStocksContent(stocks: List<StockPosition>, modifier: Modifier = Modifier) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)) {
        itemsIndexed(stocks) { index, stock ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stock.symbol, style = MaterialTheme.typography.titleLarge)
                Text(stock.name, style = MaterialTheme.typography.bodySmall, modifier = modifier
                    .weight(1f, false)
                    .padding(horizontal = 16.dp), textAlign = TextAlign.Center)
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        NumberFormat.getCurrencyInstance().apply {
                            Currency.getInstance(stock.currency)
                        }.format(stock.tradingPrice),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    if (stock.quantity > 0) {
                        Text("${stock.quantity} shares")
                    }
                    Text(SimpleDateFormat("M/dd/yy h:mm:ss aa", Locale.current.platformLocale).format(stock.time * 1000), style = MaterialTheme.typography.labelSmall)
                }
            }
            if (index < stocks.lastIndex) {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@Composable
private fun PortfolioStocksError(modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .then(modifier)) {
        Text(text = "Oh my, something has gone horribly askew!", modifier = Modifier.align(Alignment.Center))
    }
}