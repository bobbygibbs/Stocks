package com.gibbs.stocks

import com.gibbs.stocks.data.GetPortfolioUseCase
import com.gibbs.stocks.domain.PortfolioStocksViewModel
import com.gibbs.stocks.domain.StockPosition
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

class PortfolioViewModelUnitTest {
    @get:Rule
    val mockkRule = MockKRule(this)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getPortfolioUseCase = mockk<GetPortfolioUseCase>()
    private val dummyList = listOf(StockPosition("ABC", "Sesame Street", "CAN", 999f, 27, 795363300))

    lateinit var objUnderTest: PortfolioStocksViewModel

    @Test
    fun testErrorState() {
        coEvery { getPortfolioUseCase.invoke() } returns null
        objUnderTest = PortfolioStocksViewModel(getPortfolioUseCase)
        assert(!objUnderTest.state.value.isLoading)
        assertNull(objUnderTest.state.value.stockPositions)
    }
    @Test
    fun testEmptyState() {
        coEvery { getPortfolioUseCase.invoke() } returns emptyList()
        objUnderTest = PortfolioStocksViewModel(getPortfolioUseCase)
        assert(!objUnderTest.state.value.isLoading)
        assertNotNull(objUnderTest.state.value.stockPositions)
    }

    @Test
    fun testContentState() {
        coEvery { getPortfolioUseCase.invoke() } returns dummyList
        objUnderTest = PortfolioStocksViewModel(getPortfolioUseCase)
        assert(!objUnderTest.state.value.isLoading)
        assert(!objUnderTest.state.value.stockPositions.isNullOrEmpty())
    }

}