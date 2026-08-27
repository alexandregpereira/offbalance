package br.alexandregpereira.offbalance.settings.data.usecase

import br.alexandregpereira.offbalance.provider.model.ProviderType
import br.alexandregpereira.offbalance.settings.repository.SettingsRepository
import br.alexandregpereira.offbalance.settings.usecase.SelectProvider

internal class DefaultSelectProvider(
    private val settingsRepository: SettingsRepository,
) : SelectProvider {

    override suspend fun invoke(providerType: ProviderType) {
        val settings = settingsRepository.getSettings()
        settingsRepository.saveSettings(settings.copy(selectedProvider = providerType))
    }
}
