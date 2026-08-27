package br.alexandregpereira.offbalance.provider.data.usecase

import br.alexandregpereira.offbalance.balance.repository.AccountRepository
import br.alexandregpereira.offbalance.balance.repository.BalanceRepository
import br.alexandregpereira.offbalance.balance.repository.InstitutionRepository
import br.alexandregpereira.offbalance.provider.FinanceProviderRegistry
import br.alexandregpereira.offbalance.provider.repository.ProviderConnectionRepository
import br.alexandregpereira.offbalance.provider.repository.SelectedProviderRepository
import br.alexandregpereira.offbalance.provider.usecase.SyncAccounts

internal class DefaultSyncAccounts(
    private val providerRegistry: FinanceProviderRegistry,
    private val selectedProviderRepository: SelectedProviderRepository,
    private val connectionRepository: ProviderConnectionRepository,
    private val institutionRepository: InstitutionRepository,
    private val accountRepository: AccountRepository,
    private val balanceRepository: BalanceRepository,
) : SyncAccounts {

    override suspend fun invoke() {
        val provider = providerRegistry.get(selectedProviderRepository.getSelectedProvider())

        val connections = provider.getConnections()
        connectionRepository.saveConnections(connections)
        institutionRepository.saveInstitutions(provider.getInstitutions())

        connections.forEach { connection ->
            accountRepository.saveAccounts(provider.getAccounts(connection.id))
            balanceRepository.saveBalances(provider.getBalances(connection.id))
        }
    }
}
