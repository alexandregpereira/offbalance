package br.alexandregpereira.offbalance.feature.settings.di

import br.alexandregpereira.offbalance.feature.settings.SettingsStateHolder
import org.koin.dsl.module

val settingsFeatureModule = module {
    factory {
        SettingsStateHolder(
            getSettings = get(),
            selectProvider = get(),
            getProviderConnections = get(),
            syncAccounts = get(),
            providerRegistry = get(),
            syncEventManager = get(),
        )
    }
}
