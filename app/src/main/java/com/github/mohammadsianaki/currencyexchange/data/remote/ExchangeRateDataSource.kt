package com.github.mohammadsianaki.currencyexchange.data.remote

interface ExchangeRateDataSource {
    suspend fun getExchangeRates(): Map<String, Double>
}