package com.beok.common

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Timber.d(throwable)
}

fun CoroutineScope.safeLaunch(
    exceptionHandler: CoroutineExceptionHandler = coroutineExceptionHandler,
    block: suspend () -> Unit
): Job = this.launch(exceptionHandler) {
    block.invoke()
}
