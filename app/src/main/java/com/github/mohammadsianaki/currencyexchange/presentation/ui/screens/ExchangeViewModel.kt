package com.github.mohammadsianaki.currencyexchange.presentation.ui.screens

import androidx.lifecycle.viewModelScope
import com.github.mohammadsianaki.currencyexchange.core_common.base.BaseViewModel
import com.github.mohammadsianaki.currencyexchange.domain.GetAllExchangeRatesUseCase
import com.github.mohammadsianaki.currencyexchange.domain.GetUserBalanceUseCase
import com.github.mohammadsianaki.currencyexchange.domain.InitUserBalanceUseCase
import com.github.mohammadsianaki.currencyexchange.domain.SyncExchangeRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val initUserBalanceUseCase: InitUserBalanceUseCase,
    private val getAllExchangeRatesUseCase: GetAllExchangeRatesUseCase,
    private val getUserBalanceUseCase: GetUserBalanceUseCase,
    private val syncExchangeRatesUseCase: SyncExchangeRatesUseCase
) : BaseViewModel() {

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.printStackTrace()
        }

    init {
        listenToBalance()
        listenToExchangeRates()
        initBalance()
        syncExchangeRate()
    }

    fun foo() {

    }

    private fun initBalance() {
        viewModelScope.launch(coroutineExceptionHandler) {
            onIO {
                initUserBalanceUseCase.execute()
            }
        }
    }

    private fun syncExchangeRate() {
        viewModelScope.launch(coroutineExceptionHandler) {
            while (isActive) {
                onIO {
                    syncExchangeRatesUseCase.sync()
                }
                onBg {
                    delay(5000)
                }
            }
        }
    }

    private fun listenToBalance() {
        viewModelScope.launch(coroutineExceptionHandler) {
            getUserBalanceUseCase.execute().collect {
                println("==========> user balance observed $it")
            }
        }
    }

    private fun listenToExchangeRates() {
        viewModelScope.launch(coroutineExceptionHandler) {
            getAllExchangeRatesUseCase.execute().collect {
                println("==========> exchange rates observed $it")
            }
        }
    }
}