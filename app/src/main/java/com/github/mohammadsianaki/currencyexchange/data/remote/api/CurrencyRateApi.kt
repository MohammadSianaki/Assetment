package com.github.mohammadsianaki.currencyexchange.data.remote.api

import com.github.mohammadsianaki.currencyexchange.data.remote.model.CurrencyExchangeRateDto
import retrofit2.http.GET

interface CurrencyRateApi {
    @GET("currency-exchange-rates")
    suspend fun getCurrencies(): CurrencyExchangeRateDto
}