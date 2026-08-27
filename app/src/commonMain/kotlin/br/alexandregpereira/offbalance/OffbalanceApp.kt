package br.alexandregpereira.offbalance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import br.alexandregpereira.offbalance.state.compose.rememberStateHolder
import br.alexandregpereira.offbalance.ui.AppMainScreen
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTheme

@Composable
fun OffbalanceApp() {
    OffbalanceTheme {
        val stateHolder: MainStateHolder = rememberStateHolder()
        val state by stateHolder.state.collectAsState()

        AppMainScreen(state = state, intent = stateHolder)
    }
}
