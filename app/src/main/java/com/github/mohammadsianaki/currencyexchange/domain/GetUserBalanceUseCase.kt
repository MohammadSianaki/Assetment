package com.github.mohammadsianaki.currencyexchange.domain

import com.github.mohammadsianaki.currencyexchange.data.CurrencyExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserBalanceUseCase @Inject constructor(
    private val repository: CurrencyExchangeRepository
) {

    fun execute(): Flow<List<BalanceEntity>> {
        return repository.observeUserBalance()
            .map { it.map { BalanceEntity(symbol = it.key, amount = it.value) } }
    }
}