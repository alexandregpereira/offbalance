package br.alexandregpereira.offbalance.feature.dashboard

import br.alexandregpereira.offbalance.balance.model.AccountType
import br.alexandregpereira.offbalance.balance.model.AccountWithBalance
import br.alexandregpereira.offbalance.money.Money
import br.alexandregpereira.offbalance.money.format

data class DashboardState(
    val isLoading: Boolean = true,
    val isSyncing: Boolean = false,
    val netWorth: String = "",
    val institutions: List<InstitutionGroupState> = emptyList(),
    val errorMessage: String? = null,
)

data class InstitutionGroupState(
    val id: String,
    val name: String,
    val total: String,
    val accounts: List<AccountState>,
)

data class AccountState(
    val id: String,
    val name: String,
    val typeLabel: String,
    val balance: String,
    val isNegative: Boolean,
)

internal fun DashboardState.loading(): DashboardState = copy(isLoading = true, errorMessage = null)

internal fun DashboardState.syncing(isSyncing: Boolean): DashboardState = copy(isSyncing = isSyncing)

internal fun DashboardState.failure(message: String): DashboardState =
    copy(isLoading = false, isSyncing = false, errorMessage = message)

internal fun DashboardState.content(
    netWorth: Money,
    accounts: List<AccountWithBalance>,
): DashboardState = copy(
    isLoading = false,
    errorMessage = null,
    netWorth = netWorth.format(),
    institutions = accounts.groupBy { it.institution }
        .map { (institution, institutionAccounts) ->
            InstitutionGroupState(
                id = institution.id,
                name = institution.name,
                total = institutionAccounts.fold(Money.zero(netWorth.currency)) { total, item ->
                    total + item.balance
                }.format(),
                accounts = institutionAccounts.map { it.toAccountState() },
            )
        }
        .sortedBy { it.name },
)

private fun AccountWithBalance.toAccountState(): AccountState = AccountState(
    id = account.id,
    name = account.name,
    typeLabel = account.type.label(),
    balance = balance.format(),
    isNegative = balance.cents < 0,
)

private fun AccountType.label(): String = when (this) {
    AccountType.CHECKING -> "Conta corrente"
    AccountType.SAVINGS -> "Poupança"
    AccountType.CREDIT_CARD -> "Cartão de crédito"
    AccountType.INVESTMENT -> "Investimento"
    AccountType.CASH -> "Dinheiro"
}
