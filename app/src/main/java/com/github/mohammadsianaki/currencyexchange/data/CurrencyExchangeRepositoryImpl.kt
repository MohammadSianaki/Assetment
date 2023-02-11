package com.github.mohammadsianaki.currencyexchange.data

import com.github.mohammadsianaki.currencyexchange.data.local.BalanceDataSource
import com.github.mohammadsianaki.currencyexchange.data.local.db.LocalBalanceEntity
import com.github.mohammadsianaki.currencyexchange.data.memory.ExchangeRateDataStore
import com.github.mohammadsianaki.currencyexchange.data.remote.ExchangeRateDataSource
import com.github.mohammadsianaki.currencyexchange.domain.BalanceEntity
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CurrencyExchangeRepositoryImpl @Inject constructor(
    private val remoteDataSource: ExchangeRateDataSource,
    private val exchangeRateDataStore: ExchangeRateDataStore,
    private val balanceDataSource: BalanceDataSource
) : CurrencyExchangeRepository {
    override suspend fun getExchangeRates(): StateFlow<Map<String, Double>> {
        return exchangeRateDataStore.getExchangesRates()
    }

    override suspend fun updateExchangesRates() {
        val freshRates = remoteDataSource.getExchangeRates()
        exchangeRateDataStore.setExchangeRates(freshRates)
    }

    override suspend fun getBalance(): Map<String, Double> {
        return balanceDataSource.getUserBalance()
    }

    override suspend fun saveBalance(balances: List<BalanceEntity>) {
        val userBalanceMap = balances.associateBy({ it.symbol }, { it.amount })
        balanceDataSource.saveUserBalance(userBalanceMap)
    }
}