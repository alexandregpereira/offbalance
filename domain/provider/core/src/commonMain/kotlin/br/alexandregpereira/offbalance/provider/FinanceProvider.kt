package br.alexandregpereira.offbalance.provider

import br.alexandregpereira.offbalance.balance.model.Account
import br.alexandregpereira.offbalance.balance.model.AccountBalance
import br.alexandregpereira.offbalance.balance.model.Institution
import br.alexandregpereira.offbalance.provider.model.ProviderConnection
import br.alexandregpereira.offbalance.provider.model.ProviderCredentials
import br.alexandregpereira.offbalance.provider.model.ProviderType

/**
 * An open finance data source. Every supported aggregator (Pluggy, Belvo, ...) implements this
 * interface, so the rest of the app never depends on a specific vendor.
 */
interface FinanceProvider {

    val type: ProviderType

    suspend fun getConnections(): List<ProviderConnection>

    suspend fun getInstitutions(): List<Institution>

    suspend fun getAccounts(connectionId: String): List<Account>

    suspend fun getBalances(connectionId: String): List<AccountBalance>

    suspend fun connect(credentials: ProviderCredentials): ProviderConnection
}

/**
 * Resolves a [FinanceProvider] implementation by its [ProviderType].
 */
interface FinanceProviderRegistry {

    val availableTypes: List<ProviderType>

    fun get(type: ProviderType): FinanceProvider
}
