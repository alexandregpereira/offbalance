package br.alexandregpereira.offbalance.feature.settings

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import br.alexandregpereira.offbalance.feature.settings.ui.SettingsScreen
import br.alexandregpereira.offbalance.state.compose.rememberStateHolder

@Composable
fun SettingsFeature(contentPadding: PaddingValues = PaddingValues()) {
    val stateHolder: SettingsStateHolder = rememberStateHolder()
    val state by stateHolder.state.collectAsState()

    SettingsScreen(
        state = state,
        intent = stateHolder,
        contentPadding = contentPadding,
    )
}
