package com.gibbs.stocks

import com.gibbs.stocks.data.ApiPortfolioResponse
import com.gibbs.stocks.data.ApiStock
import com.gibbs.stocks.data.GetPortfolioUseCase
import com.gibbs.stocks.data.PortfolioService
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetPortfolioUseCaseUnitTest {

    @get:Rule
    val mockkRule = MockKRule(this)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val portfolioService = mockk<PortfolioService>()
    private val dummyList = listOf(ApiStock("ABC", "Sesame Street", "CAN", 999, 27, 795363300))

    lateinit var objUnderTest: GetPortfolioUseCase
    @Before
    fun setup() {
        objUnderTest = GetPortfolioUseCase(Dispatchers.Unconfined, portfolioService)
    }

    @Test
    fun testErrorState() {
        coEvery { portfolioService.getPortfolio() } throws Exception()

        val response = runBlocking { objUnderTest() }

        assertNull(response)
    }

    @Test
    fun testEmptyState() {
        coEvery { portfolioService.getPortfolio() } returns ApiPortfolioResponse(emptyList())

        val response = runBlocking { objUnderTest() }

        assertNotNull(response)
        assert(response!!.isEmpty())
    }

    @Test
    fun testContentState() {
        coEvery { portfolioService.getPortfolio() } returns ApiPortfolioResponse(dummyList)

        val response = runBlocking { objUnderTest() }

        assertNotNull(response)
        assert(response!!.isNotEmpty())
    }
}