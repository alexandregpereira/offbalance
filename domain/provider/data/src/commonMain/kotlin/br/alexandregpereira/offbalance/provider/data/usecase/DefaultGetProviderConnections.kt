package br.alexandregpereira.offbalance.provider.data.usecase

import br.alexandregpereira.offbalance.provider.model.ProviderConnection
import br.alexandregpereira.offbalance.provider.repository.ProviderConnectionRepository
import br.alexandregpereira.offbalance.provider.usecase.GetProviderConnections

internal class DefaultGetProviderConnections(
    private val connectionRepository: ProviderConnectionRepository,
) : GetProviderConnections {

    override suspend fun invoke(): List<ProviderConnection> = connectionRepository.getConnections()
}
