package br.alexandregpereira.offbalance.provider.model

import kotlinx.datetime.Instant

enum class ProviderType {
    FAKE,
    PLUGGY,
    BELVO,
}

enum class ProviderConnectionStatus {
    CONNECTED,
    UPDATING,
    ERROR,
}

data class ProviderConnection(
    val id: String,
    val providerType: ProviderType,
    val institutionId: String,
    val institutionName: String,
    val status: ProviderConnectionStatus,
    val createdAt: Instant,
)

/**
 * Credentials the user provides to open a connection. Which fields are required depends on the
 * provider, so it is kept as a free form map instead of a fixed shape.
 */
data class ProviderCredentials(
    val institutionId: String,
    val values: Map<String, String> = emptyMap(),
)
