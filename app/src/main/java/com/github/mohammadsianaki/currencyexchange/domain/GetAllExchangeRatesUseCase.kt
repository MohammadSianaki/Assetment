package com.github.mohammadsianaki.currencyexchange.domain

import com.github.mohammadsianaki.currencyexchange.data.CurrencyExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllExchangeRatesUseCase @Inject constructor(
    private val repository: CurrencyExchangeRepository
) {
    fun execute(): Flow<List<ExchangeRateEntity>> {
        return repository.observeExchangeRates().map {
            it.map { entry -> ExchangeRateEntity(entry.key, entry.value) }
        }
    }
}