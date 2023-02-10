package com.github.mohammadsianaki.currencyexchange.data

import com.github.mohammadsianaki.currencyexchange.data.remote.RemoteExchangeRateDataSource
import com.github.mohammadsianaki.currencyexchange.data.remote.api.CurrencyRateApi
import javax.inject.Inject

class CurrencyExchangeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteExchangeRateDataSource
) : CurrencyExchangeRepository {
    override suspend fun getExchangeRates(): Map<String, Double> {
        return remoteDataSource.getExchangeRates()
    }
}