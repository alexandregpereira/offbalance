package br.alexandregpereira.offbalance.state.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import br.alexandregpereira.offbalance.state.StateHolder
import org.koin.compose.koinInject

@Composable
inline fun <reified T : StateHolder<*>> rememberStateHolder(
    crossinline onCreated: (T) -> Unit = {},
): T {
    val stateHolder = koinInject<T>()

    DisposableEffect(stateHolder) {
        onCreated(stateHolder)
        onDispose {
            stateHolder.onCleared()
        }
    }

    return stateHolder
}
