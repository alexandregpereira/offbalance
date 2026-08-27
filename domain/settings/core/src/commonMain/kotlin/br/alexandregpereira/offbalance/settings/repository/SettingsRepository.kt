package br.alexandregpereira.offbalance.settings.repository

import br.alexandregpereira.offbalance.settings.model.AppSettings

interface SettingsRepository {

    suspend fun getSettings(): AppSettings

    suspend fun saveSettings(settings: AppSettings)
}
