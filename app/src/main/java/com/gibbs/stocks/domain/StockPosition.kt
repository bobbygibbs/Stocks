package com.gibbs.stocks.domain

/**
 * Represents a snapshot of the position of a particular stock corresponding to the [name] and
 * [symbol] provided, including its individual share value and number of shares held, all accurate
 * at the [time] that the information was fetched.
 *
 * @param symbol Ticker symbol used in the given stock market for this stock (e.g. "SQ")
 * @param name Full company name (e.g. "Block")
 * @param currency Abbreviated currency descriptor (e.g. "USD")
 * @param tradingPrice Current value of an individual share of the stock in terms of the [currency]
 * @param quantity Number of shares held by the portfolio of this stock
 * @param time Unix timestamp in UTC of when the position information the was gathered
 */
data class StockPosition(
    val symbol: String,
    val name: String,
    val currency: String,
    val tradingPrice: Float,
    val quantity: Int,
    val time: Long
)