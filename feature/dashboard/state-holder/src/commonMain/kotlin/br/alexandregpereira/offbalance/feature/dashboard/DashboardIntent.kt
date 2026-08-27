package br.alexandregpereira.offbalance.feature.dashboard

interface DashboardIntent {

    fun onSyncClick()
}

class EmptyDashboardIntent : DashboardIntent {

    override fun onSyncClick() = Unit
}
