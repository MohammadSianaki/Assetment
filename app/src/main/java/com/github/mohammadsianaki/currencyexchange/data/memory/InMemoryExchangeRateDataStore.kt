package com.github.mohammadsianaki.currencyexchange.data.memory

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class InMemoryExchangeRateDataStore : ExchangeRateDataStore {
    private val cachedExchangeRates = MutableStateFlow<Map<String, Double>>(emptyMap())

    override fun setExchangeRates(rates: Map<String, Double>) {
        cachedExchangeRates.update { rates }
    }

    override fun getExchangesRates(): StateFlow<Map<String, Double>> {
        return cachedExchangeRates
    }
}