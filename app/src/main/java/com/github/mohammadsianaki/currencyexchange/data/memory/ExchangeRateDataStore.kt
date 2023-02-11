package com.github.mohammadsianaki.currencyexchange.data.memory

import kotlinx.coroutines.flow.StateFlow

interface ExchangeRateDataStore {
    fun setExchangeRates(rates: Map<String, Double>)
    fun getExchangesRates(): StateFlow<Map<String, Double>>
}