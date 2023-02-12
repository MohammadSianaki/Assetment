package com.github.mohammadsianaki.currencyexchange.domain

import com.github.mohammadsianaki.currencyexchange.data.CurrencyExchangeRepository
import com.github.mohammadsianaki.currencyexchange.data.memory.InMemoryExchangeRateDataStore
import javax.inject.Inject

class SyncExchangeRatesUseCase @Inject constructor(
    private val repository: CurrencyExchangeRepository
) {
    suspend fun sync() {
        repository.updateExchangesRates()
    }
}