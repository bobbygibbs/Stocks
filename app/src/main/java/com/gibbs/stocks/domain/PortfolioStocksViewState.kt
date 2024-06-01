package com.gibbs.stocks.domain

/**
 * Represents the state of the Portfolio Stocks list screen, which consists of a list of stock
 * positions, and whether or not we are actively trying to fetch data.
 *
 * @param stockPositions List of stock positions we have fetched, where null represents our error state
 * @param isLoading Whether or not we are currently loading data
 */
data class PortfolioStocksViewState(
    val stockPositions: List<StockPosition>? = emptyList(),
    val isLoading: Boolean = true
)
