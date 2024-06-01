package com.gibbs.stocks.data

import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface PortfolioService {

    @GET("cash-stocks-api/portfolio.json")
    suspend fun getPortfolio(): ApiPortfolioResponse
}