package br.alexandregpereira.offbalance.settings.data

import br.alexandregpereira.offbalance.ktx.getDispatcherIO
import br.alexandregpereira.offbalance.money.Money
import br.alexandregpereira.offbalance.provider.model.ProviderType
import br.alexandregpereira.offbalance.provider.repository.SelectedProviderRepository
import br.alexandregpereira.offbalance.settings.model.AppSettings
import br.alexandregpereira.offbalance.settings.repository.SettingsRepository
import com.russhwolf.settings.Settings
import kotlinx.coroutines.withContext

internal class SettingsRepositoryImpl(
    private val settings: Settings,
) : SettingsRepository, SelectedProviderRepository {

    override suspend fun getSettings(): AppSettings = withContext(getDispatcherIO()) {
        AppSettings(
            selectedProvider = settings.getStringOrNull(KEY_PROVIDER)
                ?.let { name -> ProviderType.entries.firstOrNull { it.name == name } }
                ?: ProviderType.FAKE,
            currency = settings.getStringOrNull(KEY_CURRENCY) ?: Money.BRL,
        )
    }

    override suspend fun saveSettings(settings: AppSettings) = withContext(getDispatcherIO()) {
        this@SettingsRepositoryImpl.settings.putString(KEY_PROVIDER, settings.selectedProvider.name)
        this@SettingsRepositoryImpl.settings.putString(KEY_CURRENCY, settings.currency)
    }

    override suspend fun getSelectedProvider(): ProviderType = getSettings().selectedProvider

    override suspend fun setSelectedProvider(providerType: ProviderType) {
        saveSettings(getSettings().copy(selectedProvider = providerType))
    }

    private companion object {
        const val KEY_PROVIDER = "selected_provider"
        const val KEY_CURRENCY = "currency"
    }
}
