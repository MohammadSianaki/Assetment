package com.github.mohammadsianaki.currencyexchange.data.remote

interface RemoteExchangeRateDataSource {
    suspend fun getExchangeRates(): Map<String, Double>
}