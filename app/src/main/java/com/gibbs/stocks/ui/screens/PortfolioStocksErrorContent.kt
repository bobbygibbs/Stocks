package com.gibbs.stocks.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gibbs.stocks.R

@Composable
internal fun PortfolioStocksErrorContent(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize().then(modifier)) {
        Text(
            text = stringResource(R.string.error_message),
            modifier = Modifier.align(Alignment.Center).padding(64.dp),
            textAlign = TextAlign.Center
        )
    }
}