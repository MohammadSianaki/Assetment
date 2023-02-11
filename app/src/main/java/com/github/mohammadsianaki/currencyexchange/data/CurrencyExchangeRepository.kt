package com.github.mohammadsianaki.currencyexchange.data

import com.github.mohammadsianaki.currencyexchange.domain.BalanceEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface CurrencyExchangeRepository {
    fun observeExchangeRates(): StateFlow<Map<String, Double>>

    suspend fun updateExchangesRates()
    suspend fun getBalance(): Map<String, Double>
    fun observeUserBalance(): Flow<Map<String, Double>>
    suspend fun saveBalance(balances: List<BalanceEntity>)
}