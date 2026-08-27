package br.alexandregpereira.offbalance.settings.usecase

import br.alexandregpereira.offbalance.provider.model.ProviderType

/**
 * Changes which provider the next sync pulls from, keeping the rest of the settings untouched.
 */
fun interface SelectProvider {

    suspend operator fun invoke(providerType: ProviderType)
}
