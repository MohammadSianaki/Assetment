package com.github.mohammadsianaki.currencyexchange.data.local


interface BalanceDataSource {
    suspend fun getUserBalance(): Map<String, Double>
    suspend fun saveUserBalance(balance: Map<String, Double>)
}