package com.github.mohammadsianaki.currencyexchange.data

interface CurrencyExchangeRepository {
    suspend fun getExchangeRates(): Map<String, Double>
}