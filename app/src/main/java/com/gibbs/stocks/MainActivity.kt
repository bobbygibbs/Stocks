package com.gibbs.stocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gibbs.stocks.ui.screens.PortfolioStocksScreen
import com.gibbs.stocks.ui.theme.StocksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StocksTheme {
                PortfolioStocksScreen()
            }
        }
    }
}