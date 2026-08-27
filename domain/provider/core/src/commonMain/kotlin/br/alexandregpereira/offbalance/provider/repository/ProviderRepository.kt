package br.alexandregpereira.offbalance.provider.repository

import br.alexandregpereira.offbalance.provider.model.ProviderConnection
import br.alexandregpereira.offbalance.provider.model.ProviderType

interface ProviderConnectionRepository {

    suspend fun getConnections(): List<ProviderConnection>

    suspend fun saveConnections(connections: List<ProviderConnection>)
}

/**
 * Which provider the app is currently pulling data from. Implemented by the settings data layer.
 */
interface SelectedProviderRepository {

    suspend fun getSelectedProvider(): ProviderType

    suspend fun setSelectedProvider(providerType: ProviderType)
}
