package br.alexandregpereira.offbalance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import br.alexandregpereira.offbalance.state.compose.rememberStateHolder
import br.alexandregpereira.offbalance.ui.AppMainScreen
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTheme

@Composable
fun OffbalanceApp() {
    OffbalanceTheme {
        // MainStateHolder is recreated whenever the composition is, so the selected tab has to
        // survive outside of it. Stored by name because an enum is not saveable on every target.
        var savedTab by rememberSaveable { mutableStateOf(AppTab.DASHBOARD.name) }

        val stateHolder: MainStateHolder = rememberStateHolder()

        // Seeded during composition, not from an effect: setState is synchronous, so the
        // collectAsState below already reads the restored tab and the first frame renders the
        // right screen. Restoring from an effect would compose the dashboard first and then
        // throw that state holder away.
        remember(stateHolder) { stateHolder.onTabClick(AppTab.valueOf(savedTab)) }

        val state by stateHolder.state.collectAsState()

        LaunchedEffect(state.selectedTab) { savedTab = state.selectedTab.name }

        AppMainScreen(state = state, intent = stateHolder)
    }
}
