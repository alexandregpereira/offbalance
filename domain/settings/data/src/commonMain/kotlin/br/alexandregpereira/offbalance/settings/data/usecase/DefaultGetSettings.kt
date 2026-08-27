package br.alexandregpereira.offbalance.settings.data.usecase

import br.alexandregpereira.offbalance.settings.model.AppSettings
import br.alexandregpereira.offbalance.settings.repository.SettingsRepository
import br.alexandregpereira.offbalance.settings.usecase.GetSettings

internal class DefaultGetSettings(
    private val settingsRepository: SettingsRepository,
) : GetSettings {

    override suspend fun invoke(): AppSettings = settingsRepository.getSettings()
}
