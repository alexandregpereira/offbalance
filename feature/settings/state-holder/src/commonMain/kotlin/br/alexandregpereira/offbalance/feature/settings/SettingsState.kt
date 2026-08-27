package br.alexandregpereira.offbalance.feature.settings

import br.alexandregpereira.offbalance.provider.model.ProviderConnection
import br.alexandregpereira.offbalance.provider.model.ProviderConnectionStatus
import br.alexandregpereira.offbalance.provider.model.ProviderType

data class SettingsState(
    val isLoading: Boolean = true,
    val isSyncing: Boolean = false,
    val providers: List<ProviderOptionState> = emptyList(),
    val connections: List<ConnectionState> = emptyList(),
    val message: String? = null,
)

data class ProviderOptionState(
    val type: ProviderType,
    val name: String,
    val description: String,
    val isSelected: Boolean,
    val isAvailable: Boolean,
)

data class ConnectionState(
    val id: String,
    val institutionName: String,
    val statusLabel: String,
)

internal fun SettingsState.loading(): SettingsState = copy(isLoading = true, message = null)

internal fun SettingsState.syncing(isSyncing: Boolean): SettingsState = copy(isSyncing = isSyncing)

internal fun SettingsState.message(message: String?): SettingsState = copy(message = message)

internal fun SettingsState.content(
    availableTypes: List<ProviderType>,
    selectedType: ProviderType,
    connections: List<ProviderConnection>,
): SettingsState = copy(
    isLoading = false,
    providers = availableTypes.map { type ->
        ProviderOptionState(
            type = type,
            name = type.displayName(),
            description = type.description(),
            isSelected = type == selectedType,
            isAvailable = type in SUPPORTED_PROVIDERS,
        )
    },
    connections = connections.map { connection ->
        ConnectionState(
            id = connection.id,
            institutionName = connection.institutionName,
            statusLabel = connection.status.label(),
        )
    },
)

private val SUPPORTED_PROVIDERS = setOf(ProviderType.FAKE, ProviderType.PLUGGY)

private fun ProviderType.displayName(): String = when (this) {
    ProviderType.FAKE -> "Dados de exemplo"
    ProviderType.PLUGGY -> "Pluggy"
    ProviderType.BELVO -> "Belvo"
}

private fun ProviderType.description(): String = when (this) {
    ProviderType.FAKE -> "Contas fictícias para navegar pelo app sem conectar um banco"
    ProviderType.PLUGGY -> "Open finance brasileiro. Requer client id e secret"
    ProviderType.BELVO -> "Ainda não implementado"
}

private fun ProviderConnectionStatus.label(): String = when (this) {
    ProviderConnectionStatus.CONNECTED -> "Conectado"
    ProviderConnectionStatus.UPDATING -> "Atualizando"
    ProviderConnectionStatus.ERROR -> "Erro na conexão"
}
