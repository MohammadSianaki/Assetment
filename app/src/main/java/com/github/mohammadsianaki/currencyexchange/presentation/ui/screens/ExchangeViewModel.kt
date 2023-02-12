package com.github.mohammadsianaki.currencyexchange.presentation.ui.screens

import androidx.lifecycle.viewModelScope
import com.github.mohammadsianaki.currencyexchange.core_common.Loadable
import com.github.mohammadsianaki.currencyexchange.core_common.Loaded
import com.github.mohammadsianaki.currencyexchange.core_common.NotLoaded
import com.github.mohammadsianaki.currencyexchange.core_common.base.BaseViewModel
import com.github.mohammadsianaki.currencyexchange.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
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

    private val _uiState = MutableStateFlow(ViewState())
    val uiState = _uiState.asStateFlow()

    data class ViewState(
        val exchangeRates: Loadable<List<ExchangeRateEntity>> = NotLoaded,
        val userBalance: Loadable<List<BalanceEntity>> = NotLoaded
    )

    init {
        initBalance()
        syncExchangeRate()
        listenToBalance()
        listenToExchangeRates()
    }

    fun foo() {

    }

    private fun initBalance() {
        viewModelScope.launch {
            onIO {
                initUserBalanceUseCase.execute()
            }
        }
    }

    private fun syncExchangeRate() {
        viewModelScope.launch {
            while (isActive) {
                onIO {
                    syncExchangeRatesUseCase.sync()
                }
                onBg {
                    delay(LONG_POLLING_DELAY)
                }
            }
        }
    }

    private fun listenToBalance() {
        viewModelScope.launch {
            getUserBalanceUseCase.execute().collect {
                println("==========> user balance observed $it")
                _uiState.update { oldViewState ->
                    oldViewState.copy(
                        userBalance = Loaded(it)
                    )
                }
            }
        }
    }

    private fun listenToExchangeRates() {
        viewModelScope.launch {
            getAllExchangeRatesUseCase.execute().collect {
                println("==========> collect = $it")
            }
        }
        viewModelScope.launch {
            getAllExchangeRatesUseCase.execute().onEach {
                println("==========> exchange rates observed $it")
//                _uiState.update { oldViewState ->
//                    oldViewState.copy(
//                        exchangeRates = Loaded(it.ma)
//                    )
//                }
            }.launchIn(this)
        }
    }

    private companion object {
        private const val LONG_POLLING_DELAY = 5000L
    }
}