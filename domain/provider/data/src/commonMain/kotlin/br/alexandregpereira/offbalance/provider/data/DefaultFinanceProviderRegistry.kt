package br.alexandregpereira.offbalance.provider.data

import br.alexandregpereira.offbalance.provider.FinanceProvider
import br.alexandregpereira.offbalance.provider.FinanceProviderRegistry
import br.alexandregpereira.offbalance.provider.model.ProviderType

internal class DefaultFinanceProviderRegistry(
    private val providers: List<FinanceProvider>,
) : FinanceProviderRegistry {

    override val availableTypes: List<ProviderType> = ProviderType.entries

    override fun get(type: ProviderType): FinanceProvider = providers.firstOrNull { it.type == type }
        ?: error("No FinanceProvider implementation registered for $type")
}
