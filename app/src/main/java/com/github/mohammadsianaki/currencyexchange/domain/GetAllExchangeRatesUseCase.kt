package com.github.mohammadsianaki.currencyexchange.domain

import com.github.mohammadsianaki.currencyexchange.data.CurrencyExchangeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetAllExchangeRatesUseCase @Inject constructor(
    private val repository: CurrencyExchangeRepository
) {
    fun execute(): StateFlow<Map<String, Double>> {
        return repository.observeExchangeRates()
    }
}