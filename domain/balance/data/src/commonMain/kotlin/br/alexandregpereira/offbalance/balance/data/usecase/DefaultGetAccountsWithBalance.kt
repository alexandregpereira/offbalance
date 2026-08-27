package br.alexandregpereira.offbalance.balance.data.usecase

import br.alexandregpereira.offbalance.balance.model.AccountWithBalance
import br.alexandregpereira.offbalance.balance.model.Institution
import br.alexandregpereira.offbalance.balance.repository.AccountRepository
import br.alexandregpereira.offbalance.balance.repository.BalanceRepository
import br.alexandregpereira.offbalance.balance.repository.InstitutionRepository
import br.alexandregpereira.offbalance.balance.usecase.GetAccountsWithBalance
import br.alexandregpereira.offbalance.money.Money

internal class DefaultGetAccountsWithBalance(
    private val accountRepository: AccountRepository,
    private val institutionRepository: InstitutionRepository,
    private val balanceRepository: BalanceRepository,
) : GetAccountsWithBalance {

    override suspend fun invoke(): List<AccountWithBalance> {
        val institutions = institutionRepository.getInstitutions().associateBy { it.id }
        val balances = balanceRepository.getLatestBalances().associateBy { it.accountId }

        return accountRepository.getAccounts().map { account ->
            AccountWithBalance(
                account = account,
                institution = institutions[account.institutionId] ?: Institution(
                    id = account.institutionId,
                    name = account.institutionId,
                ),
                balance = balances[account.id]?.amount ?: Money.zero(account.currency),
            )
        }
    }
}
