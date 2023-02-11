package com.github.mohammadsianaki.currencyexchange.core_common.extensions

import android.text.Editable
import kotlinx.coroutines.delay


suspend inline fun <R> retry(count: Int, delayMillis: Long = 100, block: () -> R): R {
    var counter = 0
    var error: Throwable? = null
    while (counter < count) {
        runCatching {
            return block()
        }.onFailure {
            counter++
            error = it
            if (count <= counter)
                throw it
        }

        delay(delayMillis)
    }

    throw error!!
}


fun <T> lazyUi(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun emptyString() = ""

val <T> T.checkAllMatched: T
    get() = this
