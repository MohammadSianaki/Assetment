package com.github.mohammadsianaki.currencyexchange.domain

import com.github.mohammadsianaki.currencyexchange.data.CurrencyExchangeRepository

class SyncCurrenciesRateUseCase(
    private val repository: CurrencyExchangeRepository
) {
    suspend fun sync() {
        repository.updateExchangesRates()
    }
}