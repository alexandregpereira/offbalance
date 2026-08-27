package br.alexandregpereira.offbalance.di

import br.alexandregpereira.offbalance.MainStateHolder
import br.alexandregpereira.offbalance.data.di.dataModules
import br.alexandregpereira.offbalance.feature.dashboard.di.dashboardModule
import br.alexandregpereira.offbalance.feature.history.di.historyModule
import br.alexandregpereira.offbalance.feature.settings.di.settingsFeatureModule
import br.alexandregpereira.offbalance.provider.data.pluggy.PluggyConfig
import org.koin.core.KoinApplication
import org.koin.dsl.module

private const val DATABASE_NAME = "offbalance.db"

private val featureModules = listOf(
    dashboardModule,
    historyModule,
    settingsFeatureModule,
)

private val appModule = module {
    factory {
        MainStateHolder(
            getProviderConnections = get(),
            syncAccounts = get(),
            syncEventManager = get(),
        )
    }
}

fun KoinApplication.initKoinModules(pluggyConfig: PluggyConfig = PluggyConfig()) {
    allowOverride(false)
    modules(dataModules(databaseName = DATABASE_NAME, pluggyConfig = pluggyConfig))
    modules(featureModules)
    modules(appModule)
}
