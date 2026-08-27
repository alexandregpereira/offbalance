package br.alexandregpereira.offbalance.data.di

import br.alexandregpereira.offbalance.balance.data.di.balanceDataModule
import br.alexandregpereira.offbalance.provider.data.di.providerDataModule
import br.alexandregpereira.offbalance.provider.data.pluggy.PluggyConfig
import br.alexandregpereira.offbalance.settings.data.di.settingsDataModule
import org.koin.core.module.Module

fun dataModules(
    databaseName: String,
    pluggyConfig: PluggyConfig = PluggyConfig(),
): List<Module> = listOf(
    databaseModule(databaseName),
    networkModule,
    balanceDataModule,
    providerDataModule(pluggyConfig),
    settingsDataModule,
)
