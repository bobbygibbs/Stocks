package com.gibbs.stocks.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gibbs.stocks.data.GetPortfolioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioStocksViewModel @Inject constructor(
    val getPortfolioUseCase: GetPortfolioUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PortfolioStocksViewState())
    val state: StateFlow<PortfolioStocksViewState> = _state

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            fetchPortfolioData()
        }
    }

    private suspend fun fetchPortfolioData() {
        _state.value = _state.value.copy(
            isLoading = true
        )
        val porfolio = getPortfolioUseCase()
        _state.value = _state.value.copy(
            isLoading = false,
            stockPositions = porfolio
        )
    }
}