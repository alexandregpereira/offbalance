package br.alexandregpereira.offbalance

enum class AppTab {
    DASHBOARD,
    HISTORY,
    SETTINGS,
}

data class MainState(
    val selectedTab: AppTab = AppTab.DASHBOARD,
    val tabs: List<AppTab> = AppTab.entries,
)
