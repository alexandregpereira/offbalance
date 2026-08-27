package br.alexandregpereira.offbalance.provider.data.local

data class ProviderConnectionEntity(
    val id: String,
    val providerType: String,
    val institutionId: String,
    val institutionName: String,
    val status: String,
    val createdAtEpochMillis: Long,
)

/**
 * Implemented in `:domain:app:data`, which owns the generated database.
 */
interface ProviderConnectionDao {

    suspend fun getAll(): List<ProviderConnectionEntity>

    suspend fun insert(connections: List<ProviderConnectionEntity>)
}
