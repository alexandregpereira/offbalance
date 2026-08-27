package br.alexandregpereira.offbalance.feature.settings

import br.alexandregpereira.offbalance.provider.FinanceProviderRegistry
import br.alexandregpereira.offbalance.provider.event.SyncEvent
import br.alexandregpereira.offbalance.provider.event.SyncEventManager
import br.alexandregpereira.offbalance.provider.model.ProviderType
import br.alexandregpereira.offbalance.provider.usecase.GetProviderConnections
import br.alexandregpereira.offbalance.provider.usecase.SyncAccounts
import br.alexandregpereira.offbalance.settings.usecase.GetSettings
import br.alexandregpereira.offbalance.settings.usecase.SelectProvider
import br.alexandregpereira.offbalance.state.UiModel
import kotlinx.coroutines.launch

class SettingsStateHolder internal constructor(
    private val getSettings: GetSettings,
    private val selectProvider: SelectProvider,
    private val getProviderConnections: GetProviderConnections,
    private val syncAccounts: SyncAccounts,
    private val providerRegistry: FinanceProviderRegistry,
    private val syncEventManager: SyncEventManager,
) : UiModel<SettingsState>(SettingsState()), SettingsIntent {

    init {
        load()
    }

    override fun onProviderClick(providerType: ProviderType) {
        scope.launch {
            selectProvider(providerType)
            load()
        }
    }

    override fun onSyncClick() {
        if (state.value.isSyncing) return
        scope.launch {
            setState { syncing(isSyncing = true).message(null) }
            runCatching { syncAccounts() }
                .onSuccess {
                    syncEventManager.dispatchEvent(SyncEvent.DataChanged)
                    setState { message("Saldos atualizados") }
                }
                .onFailure { setState { message(it.message ?: "Falha ao sincronizar") } }
            setState { syncing(isSyncing = false) }
            load()
        }
    }

    override fun onMessageDismiss() {
        setState { message(null) }
    }

    private fun load() {
        scope.launch {
            setState { loading() }
            runCatching {
                Triple(
                    providerRegistry.availableTypes,
                    getSettings().selectedProvider,
                    getProviderConnections(),
                )
            }.onSuccess { (availableTypes, selectedType, connections) ->
                setState { content(availableTypes, selectedType, connections) }
            }.onFailure { throwable ->
                setState {
                    copy(isLoading = false).message(
                        throwable.message ?: "Não foi possível carregar as configurações"
                    )
                }
            }
        }
    }
}
