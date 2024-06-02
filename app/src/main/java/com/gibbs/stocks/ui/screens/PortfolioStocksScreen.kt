package com.gibbs.stocks.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gibbs.stocks.R
import com.gibbs.stocks.domain.PortfolioStocksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PortfolioStocksScreen() {

    val viewModel: PortfolioStocksViewModel = viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.my_portfolio)) })
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
                    if (stocks.isNotEmpty()) {
                        PortfolioStocksListContent(stocks)
                    } else {
                        // We need to make the screen "scrollable" to enable the PullToRefreshBox behavior
                        PortfolioStocksEmptyContent(modifier = Modifier.verticalScroll(rememberScrollState()))
                    }
                } ?: run {
                    // We need to make the screen "scrollable" to enable the PullToRefreshBox behavior
                    PortfolioStocksErrorContent(modifier = Modifier.verticalScroll(rememberScrollState()))
                }

                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    )
}