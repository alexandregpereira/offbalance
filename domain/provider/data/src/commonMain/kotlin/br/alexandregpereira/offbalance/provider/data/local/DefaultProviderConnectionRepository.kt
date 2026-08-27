package br.alexandregpereira.offbalance.provider.data.local

import br.alexandregpereira.offbalance.ktx.getDispatcherIO
import br.alexandregpereira.offbalance.provider.model.ProviderConnection
import br.alexandregpereira.offbalance.provider.model.ProviderConnectionStatus
import br.alexandregpereira.offbalance.provider.model.ProviderType
import br.alexandregpereira.offbalance.provider.repository.ProviderConnectionRepository
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant

internal class DefaultProviderConnectionRepository(
    private val dao: ProviderConnectionDao,
) : ProviderConnectionRepository {

    override suspend fun getConnections(): List<ProviderConnection> =
        withContext(getDispatcherIO()) {
            dao.getAll().map { it.toDomain() }
        }

    override suspend fun saveConnections(connections: List<ProviderConnection>) =
        withContext(getDispatcherIO()) {
            dao.insert(connections.map { it.toEntity() })
        }
}

private fun ProviderConnectionEntity.toDomain(): ProviderConnection = ProviderConnection(
    id = id,
    providerType = ProviderType.entries.firstOrNull { it.name == providerType } ?: ProviderType.FAKE,
    institutionId = institutionId,
    institutionName = institutionName,
    status = ProviderConnectionStatus.entries.firstOrNull { it.name == status }
        ?: ProviderConnectionStatus.CONNECTED,
    createdAt = Instant.fromEpochMilliseconds(createdAtEpochMillis),
)

private fun ProviderConnection.toEntity(): ProviderConnectionEntity = ProviderConnectionEntity(
    id = id,
    providerType = providerType.name,
    institutionId = institutionId,
    institutionName = institutionName,
    status = status.name,
    createdAtEpochMillis = createdAt.toEpochMilliseconds(),
)
