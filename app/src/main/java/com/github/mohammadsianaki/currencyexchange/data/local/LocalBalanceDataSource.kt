package com.github.mohammadsianaki.currencyexchange.data.local

import com.github.mohammadsianaki.currencyexchange.data.local.db.LocalBalanceEntity
import com.github.mohammadsianaki.currencyexchange.data.local.db.UserBalanceDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalBalanceDataSource @Inject constructor(
    private val userBalanceDao: UserBalanceDao
) : BalanceDataSource {
    override suspend fun getUserBalance(): Map<String, Double> {
        return userBalanceDao.getAll().associateBy({ it.symbol }, { it.amount })
    }

    override suspend fun saveUserBalance(balance: Map<String, Double>) {
        balance.map { LocalBalanceEntity(symbol = it.key, amount = it.value) }.also {
            userBalanceDao.saveAll(it)
        }
    }

    override fun observeUserBalance(): Flow<Map<String, Double>> {
        return userBalanceDao.observeAll().map { it.associateBy({ it.symbol }, { it.amount }) }
    }
}