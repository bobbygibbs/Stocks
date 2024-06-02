package com.gibbs.stocks.data

import com.gibbs.stocks.di.IoCoroutineDispatcher
import com.gibbs.stocks.domain.StockPosition
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPortfolioUseCase @Inject constructor(
    @IoCoroutineDispatcher private val dispatcher: CoroutineDispatcher,
    private val portfolioService: PortfolioService
) {
    suspend operator fun invoke(): List<StockPosition>? =
        try {
            withContext(dispatcher) {
                portfolioService.getPortfolio()
            }.stocks?.mapToDomain()
        } catch (e: Exception) {
            null
        }
}

private fun List<ApiStock>.mapToDomain(): List<StockPosition> = mapNotNull {
    StockPosition(
        symbol = it.ticker.orEmpty(),
        name = it.name.orEmpty(),
        currency = it.currency.orEmpty(),
        tradingPrice = (it.currentPriceCents ?: 0) / 100f,
        quantity = it.quantity ?: 0,
        time = it.currentPriceTimestamp ?: 0
    )
}