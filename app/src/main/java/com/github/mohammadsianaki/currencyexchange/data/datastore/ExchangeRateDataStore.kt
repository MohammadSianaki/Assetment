package com.github.mohammadsianaki.currencyexchange.data.datastore

import kotlinx.coroutines.flow.StateFlow
import java.lang.Thread.State

interface ExchangeRateDataStore {
    fun setExchangeRates(rates: Map<String, Double>)
    fun getExchangesRates(): StateFlow<Map<String, Double>>
}