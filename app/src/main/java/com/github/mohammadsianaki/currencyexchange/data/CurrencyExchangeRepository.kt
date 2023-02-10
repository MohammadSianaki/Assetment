package com.github.mohammadsianaki.currencyexchange.data

import kotlinx.coroutines.flow.StateFlow

interface CurrencyExchangeRepository {
    suspend fun getExchangeRates(): StateFlow<Map<String, Double>>

    suspend fun updateExchangesRates()
}