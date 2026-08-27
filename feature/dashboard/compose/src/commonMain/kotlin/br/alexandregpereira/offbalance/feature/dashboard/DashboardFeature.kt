package br.alexandregpereira.offbalance.feature.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import br.alexandregpereira.offbalance.feature.dashboard.ui.DashboardScreen
import br.alexandregpereira.offbalance.state.compose.rememberStateHolder

@Composable
fun DashboardFeature(contentPadding: PaddingValues = PaddingValues()) {
    val stateHolder: DashboardStateHolder = rememberStateHolder()
    val state by stateHolder.state.collectAsState()

    DashboardScreen(
        state = state,
        intent = stateHolder,
        contentPadding = contentPadding,
    )
}
