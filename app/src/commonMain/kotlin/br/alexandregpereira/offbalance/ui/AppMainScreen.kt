package br.alexandregpereira.offbalance.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.alexandregpereira.offbalance.AppTab
import br.alexandregpereira.offbalance.MainIntent
import br.alexandregpereira.offbalance.MainState
import br.alexandregpereira.offbalance.feature.dashboard.DashboardFeature
import br.alexandregpereira.offbalance.feature.history.HistoryFeature
import br.alexandregpereira.offbalance.feature.settings.SettingsFeature
import br.alexandregpereira.offbalance.ui.components.NavigationBar
import br.alexandregpereira.offbalance.ui.components.NavigationBarItem
import br.alexandregpereira.offbalance.ui.components.Scaffold
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceIcons

@Composable
internal fun AppMainScreen(state: MainState, intent: MainIntent) {
    val contentPadding = PaddingValues(
        top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                state.tabs.forEach { tab ->
                    NavigationBarItem(
                        icon = tab.icon(),
                        label = tab.label(),
                        selected = tab == state.selectedTab,
                        onClick = { intent.onTabClick(tab) },
                    )
                }
            }
        }
    ) {
        when (state.selectedTab) {
            AppTab.DASHBOARD -> DashboardFeature(contentPadding)
            AppTab.HISTORY -> HistoryFeature(contentPadding)
            AppTab.SETTINGS -> SettingsFeature(contentPadding)
        }
    }
}

private fun AppTab.label(): String = when (this) {
    AppTab.DASHBOARD -> "Saldo"
    AppTab.HISTORY -> "Histórico"
    AppTab.SETTINGS -> "Config"
}

private fun AppTab.icon() = when (this) {
    AppTab.DASHBOARD -> OffbalanceIcons.Home
    AppTab.HISTORY -> OffbalanceIcons.History
    AppTab.SETTINGS -> OffbalanceIcons.Settings
}
