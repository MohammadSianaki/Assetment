package com.github.mohammadsianaki.currencyexchange.data

import com.github.mohammadsianaki.currencyexchange.domain.BalanceEntity
import kotlinx.coroutines.flow.StateFlow

interface CurrencyExchangeRepository {
    suspend fun getExchangeRates(): StateFlow<Map<String, Double>>

    suspend fun updateExchangesRates()
    suspend fun getBalance(): Map<String, Double>
    suspend fun saveBalance(balances: List<BalanceEntity>)
}