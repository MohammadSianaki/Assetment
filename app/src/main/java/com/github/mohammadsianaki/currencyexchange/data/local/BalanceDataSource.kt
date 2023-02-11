package com.github.mohammadsianaki.currencyexchange.data.local

import kotlinx.coroutines.flow.Flow


interface BalanceDataSource {
    suspend fun getUserBalance(): Map<String, Double>
    suspend fun saveUserBalance(balance: Map<String, Double>)
    fun observeUserBalance(): Flow<Map<String, Double>>
}