package br.alexandregpereira.offbalance.ktx

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun getDispatcherIO(): CoroutineDispatcher = Dispatchers.IO
