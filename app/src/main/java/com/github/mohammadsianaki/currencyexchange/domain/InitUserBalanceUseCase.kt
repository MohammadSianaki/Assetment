package com.github.mohammadsianaki.currencyexchange.domain

import com.github.mohammadsianaki.currencyexchange.data.CurrencyExchangeRepository
import javax.inject.Inject

class InitUserBalanceUseCase @Inject constructor(
    private val repository: CurrencyExchangeRepository
) {

    suspend fun execute() {
        val userBalance = repository.getBalance()
        if (userBalance.isEmpty()) {
            repository.saveBalance(listOf(BalanceEntity(amount = 1000.0, symbol = EUR_SYMBOL)))
        }
    }

    companion object {
        private const val EUR_SYMBOL = "EUR"
    }
}