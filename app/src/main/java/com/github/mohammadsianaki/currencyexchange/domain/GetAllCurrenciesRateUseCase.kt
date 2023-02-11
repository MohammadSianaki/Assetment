package com.github.mohammadsianaki.currencyexchange.domain

import com.github.mohammadsianaki.currencyexchange.data.CurrencyExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllCurrenciesRateUseCase(
    private val repository: CurrencyExchangeRepository
) {
    suspend fun get(): Flow<List<CurrencyEntity>> {
        return repository.getExchangeRates().map {
            it.map { entry -> CurrencyEntity(entry.key, entry.value) }
        }
    }
}