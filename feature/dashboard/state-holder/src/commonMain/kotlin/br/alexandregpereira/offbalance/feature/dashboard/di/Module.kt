package br.alexandregpereira.offbalance.feature.dashboard.di

import br.alexandregpereira.offbalance.feature.dashboard.DashboardStateHolder
import org.koin.dsl.module

val dashboardModule = module {
    factory {
        DashboardStateHolder(
            getNetWorth = get(),
            getAccountsWithBalance = get(),
            syncAccounts = get(),
            syncEventManager = get(),
        )
    }
}
