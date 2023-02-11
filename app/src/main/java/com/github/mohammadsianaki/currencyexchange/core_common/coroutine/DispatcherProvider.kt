package com.github.mohammadsianaki.currencyexchange.core_common.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


interface DispatcherProvider {
    fun bg(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
}

class CoroutineDispatcherProvider : DispatcherProvider {
    override fun bg(): CoroutineDispatcher = Dispatchers.Default
    override fun ui(): CoroutineDispatcher = Dispatchers.Main
    override fun io(): CoroutineDispatcher = Dispatchers.IO

}