package com.github.mohammadsianaki.currencyexchange.data

import com.github.mohammadsianaki.currencyexchange.data.datastore.ExchangeRateDataStore
import com.github.mohammadsianaki.currencyexchange.data.remote.ExchangeRateDataSource
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CurrencyExchangeRepositoryImpl @Inject constructor(
    private val remoteDataSource: ExchangeRateDataSource,
    private val exchangeRateDataStore: ExchangeRateDataStore,
) : CurrencyExchangeRepository {
    override suspend fun getExchangeRates(): StateFlow<Map<String, Double>> {
        return exchangeRateDataStore.getExchangesRates()
    }

    override suspend fun updateExchangesRates() {
        val freshRates = remoteDataSource.getExchangeRates()
        exchangeRateDataStore.setExchangeRates(freshRates)
    }
}