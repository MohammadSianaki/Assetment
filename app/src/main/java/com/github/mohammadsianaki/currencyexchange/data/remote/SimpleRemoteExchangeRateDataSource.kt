package com.github.mohammadsianaki.currencyexchange.data.remote

import com.github.mohammadsianaki.currencyexchange.data.remote.api.CurrencyRateApi
import javax.inject.Inject


class SimpleRemoteExchangeRateDataSource @Inject constructor(
    private val currencyRateApi: CurrencyRateApi
) : RemoteExchangeRateDataSource {
    override suspend fun getExchangeRates(): Map<String, Double> {
        return currencyRateApi.getCurrencies().rates
    }
}