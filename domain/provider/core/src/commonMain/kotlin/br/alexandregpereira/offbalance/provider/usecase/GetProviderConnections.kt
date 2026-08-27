package br.alexandregpereira.offbalance.provider.usecase

import br.alexandregpereira.offbalance.provider.model.ProviderConnection

/**
 * The institutions already connected locally, regardless of the provider that imported them.
 */
fun interface GetProviderConnections {

    suspend operator fun invoke(): List<ProviderConnection>
}
