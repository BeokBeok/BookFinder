package com.beok.common.ext

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Timber.d(throwable)
}

fun CoroutineScope.safeLaunch(
    exceptionHandler: CoroutineExceptionHandler = coroutineExceptionHandler,
    block: suspend () -> Unit
): Job = this.launch(exceptionHandler) {
    block.invoke()
}
