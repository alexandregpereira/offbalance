package br.alexandregpereira.offbalance.data.database.dao

import br.alexandregpereira.offbalance.database.OffbalanceDatabase
import br.alexandregpereira.offbalance.provider.data.local.ProviderConnectionDao
import br.alexandregpereira.offbalance.provider.data.local.ProviderConnectionEntity

internal class ProviderConnectionDaoImpl(
    private val database: OffbalanceDatabase,
) : ProviderConnectionDao {

    override suspend fun getAll(): List<ProviderConnectionEntity> =
        database.providerConnectionQueries.selectAll { id, providerType, institutionId, institutionName, status, createdAt ->
            ProviderConnectionEntity(
                id = id,
                providerType = providerType,
                institutionId = institutionId,
                institutionName = institutionName,
                status = status,
                createdAtEpochMillis = createdAt,
            )
        }.executeAsList()

    override suspend fun insert(connections: List<ProviderConnectionEntity>) {
        database.providerConnectionQueries.transaction {
            connections.forEach {
                database.providerConnectionQueries.insert(
                    id = it.id,
                    providerType = it.providerType,
                    institutionId = it.institutionId,
                    institutionName = it.institutionName,
                    status = it.status,
                    createdAtEpochMillis = it.createdAtEpochMillis,
                )
            }
        }
    }
}
