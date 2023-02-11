package com.github.mohammadsianaki.currencyexchange.core_common

sealed class Loadable<out T> {
    inline fun onLoaded(onLoad: (value: T) -> Unit) {
        if (this is Loaded) onLoad(this.data)
    }

    inline fun onNotLoaded(notLoaded: () -> Unit) {
        if (this is NotLoaded) notLoaded()
    }

    inline fun onLoading(onLoading: () -> Unit) {
        if (this is Loading) onLoading()
    }

    inline fun onFailed(onFailed: (failed: Failed) -> Unit) {
        if (this is Failed) onFailed(this)
    }
}

fun <T> Loadable<T>.toLoaded() = this as Loaded

object NotLoaded : Loadable<Nothing>()
object Loading : Loadable<Nothing>()
data class Loaded<T>(val data: T) : Loadable<T>()
data class Failed(val throwable: Throwable) : Loadable<Nothing>()

sealed class CompletableTask {
    inline fun onCompleted(crossinline onCompleted: () -> Unit) {
        if (this is TaskComplete) onCompleted()
    }

    inline fun onLoading(onLoading: () -> Unit) {
        if (this is TaskLoading) onLoading()
    }

    inline fun onFailed(onFailed: (failed: TaskFailed) -> Unit) {
        if (this is TaskFailed) onFailed(TaskFailed(this.throwable))
    }
}

object NotStarted : CompletableTask()
object TaskLoading : CompletableTask()
object TaskComplete : CompletableTask()
data class TaskFailed(val throwable: Throwable) : CompletableTask()

sealed class Uploadable
object NotUploaded : Uploadable()
data class Uploading(val percentage: Int) : Uploadable()
data class Uploaded(val id: Int) : Uploadable()
data class UploadFailed(val throwable: Throwable) : Uploadable()
