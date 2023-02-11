package com.github.mohammadsianaki.currencyexchange.core_common.base

import androidx.lifecycle.ViewModel
import com.github.mohammadsianaki.currencyexchange.core_common.coroutine.CoroutineDispatcherProvider
import com.github.mohammadsianaki.currencyexchange.core_common.coroutine.DispatcherProvider
import kotlinx.coroutines.withContext

open class BaseViewModel(
    protected val dispatcherProvider: DispatcherProvider = CoroutineDispatcherProvider()
) : ViewModel() {

    protected suspend inline fun <T> onUI(crossinline coroutine: suspend () -> T): T {
        return withContext(dispatcherProvider.ui()) {
            coroutine()
        }
    }

    protected suspend inline fun <T> onBg(crossinline coroutine: suspend () -> T): T {
        return withContext(dispatcherProvider.bg()) {
            coroutine()
        }
    }

    protected suspend inline fun <T> onIO(crossinline coroutine: suspend () -> T): T {
        return withContext(dispatcherProvider.io()) {
            coroutine()
        }
    }
}