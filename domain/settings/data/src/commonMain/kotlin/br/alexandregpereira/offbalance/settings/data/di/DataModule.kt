package br.alexandregpereira.offbalance.settings.data.di

import br.alexandregpereira.offbalance.provider.repository.SelectedProviderRepository
import br.alexandregpereira.offbalance.settings.data.SettingsRepositoryImpl
import br.alexandregpereira.offbalance.settings.data.createSettings
import br.alexandregpereira.offbalance.settings.data.usecase.DefaultGetSettings
import br.alexandregpereira.offbalance.settings.data.usecase.DefaultSelectProvider
import br.alexandregpereira.offbalance.settings.repository.SettingsRepository
import br.alexandregpereira.offbalance.settings.usecase.GetSettings
import br.alexandregpereira.offbalance.settings.usecase.SelectProvider
import org.koin.dsl.binds
import org.koin.dsl.module

val settingsDataModule = module {
    single { createSettings() }
    single { SettingsRepositoryImpl(get()) } binds arrayOf(
        SettingsRepository::class,
        SelectedProviderRepository::class,
    )

    factory<GetSettings> { DefaultGetSettings(get()) }
    factory<SelectProvider> { DefaultSelectProvider(get()) }
}
