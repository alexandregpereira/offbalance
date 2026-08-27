package br.alexandregpereira.offbalance.state.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import br.alexandregpereira.offbalance.state.ActionHandler

@Composable
fun <Action : Any> ActionHandler<Action>.launchActionEffect(
    onAction: suspend (Action) -> Unit,
) {
    LaunchedEffect(this) {
        action.collect { action ->
            onAction(action)
        }
    }
}
