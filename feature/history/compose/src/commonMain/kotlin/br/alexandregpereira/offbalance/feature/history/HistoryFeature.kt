package br.alexandregpereira.offbalance.feature.history

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import br.alexandregpereira.offbalance.feature.history.ui.HistoryScreen
import br.alexandregpereira.offbalance.state.compose.rememberStateHolder

@Composable
fun HistoryFeature(contentPadding: PaddingValues = PaddingValues()) {
    val stateHolder: HistoryStateHolder = rememberStateHolder()
    val state by stateHolder.state.collectAsState()

    HistoryScreen(
        state = state,
        intent = stateHolder,
        contentPadding = contentPadding,
    )
}
