package br.alexandregpereira.offbalance.provider.data.fake

import br.alexandregpereira.offbalance.balance.model.Account
import br.alexandregpereira.offbalance.balance.model.AccountBalance
import br.alexandregpereira.offbalance.balance.model.AccountType
import br.alexandregpereira.offbalance.balance.model.Institution
import br.alexandregpereira.offbalance.money.Money
import br.alexandregpereira.offbalance.provider.FinanceProvider
import br.alexandregpereira.offbalance.provider.model.ProviderConnection
import br.alexandregpereira.offbalance.provider.model.ProviderConnectionStatus
import br.alexandregpereira.offbalance.provider.model.ProviderCredentials
import br.alexandregpereira.offbalance.provider.model.ProviderType
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

/**
 * Offline provider used as the default until a real open finance connection is configured.
 * It produces a deterministic account list plus a synthetic monthly balance history, so the
 * dashboard and the history screen have something meaningful to render.
 */
internal class FakeFinanceProvider(
    private val clock: Clock = Clock.System,
) : FinanceProvider {

    override val type: ProviderType = ProviderType.FAKE

    override suspend fun getConnections(): List<ProviderConnection> = institutions.map { institution ->
        ProviderConnection(
            id = "fake-connection-${institution.id}",
            providerType = type,
            institutionId = institution.id,
            institutionName = institution.name,
            status = ProviderConnectionStatus.CONNECTED,
            createdAt = clock.now(),
        )
    }

    override suspend fun getInstitutions(): List<Institution> = institutions

    override suspend fun getAccounts(connectionId: String): List<Account> =
        accounts.filter { it.connectionId == connectionId }

    override suspend fun getBalances(connectionId: String): List<AccountBalance> {
        val timeZone = TimeZone.currentSystemDefault()
        val today = clock.now().toLocalDateTime(timeZone).date

        return getAccounts(connectionId).flatMap { account ->
            val currentCents = currentBalanceCents.getValue(account.id)
            (0 until HISTORY_MONTH_COUNT).map { monthsAgo ->
                val date: Instant = today.minus(monthsAgo, DateTimeUnit.MONTH)
                    .atStartOfDayIn(timeZone)
                AccountBalance(
                    accountId = account.id,
                    amount = Money(
                        cents = currentCents.projectBack(monthsAgo),
                        currency = account.currency,
                    ),
                    date = date,
                )
            }
        }
    }

    override suspend fun connect(credentials: ProviderCredentials): ProviderConnection =
        getConnections().first { it.institutionId == credentials.institutionId }

    /**
     * Walks a balance backwards in time by a fixed monthly rate, so older months are smaller
     * for assets and closer to zero for debts.
     */
    private fun Long.projectBack(monthsAgo: Int): Long {
        var value = this
        repeat(monthsAgo) { value = value * 100 / (100 + MONTHLY_GROWTH_PERCENT) }
        return value
    }

    private companion object {
        const val HISTORY_MONTH_COUNT = 12
        const val MONTHLY_GROWTH_PERCENT = 3

        val institutions = listOf(
            Institution(id = "nubank", name = "Nubank"),
            Institution(id = "itau", name = "Itaú"),
            Institution(id = "xp", name = "XP Investimentos"),
            Institution(id = "cash", name = "Dinheiro"),
        )

        val accounts = listOf(
            Account(
                id = "fake-checking",
                institutionId = "nubank",
                connectionId = "fake-connection-nubank",
                name = "Conta Corrente",
                type = AccountType.CHECKING,
            ),
            Account(
                id = "fake-savings",
                institutionId = "nubank",
                connectionId = "fake-connection-nubank",
                name = "Reserva de Emergência",
                type = AccountType.SAVINGS,
            ),
            Account(
                id = "fake-credit-card",
                institutionId = "itau",
                connectionId = "fake-connection-itau",
                name = "Cartão de Crédito",
                type = AccountType.CREDIT_CARD,
            ),
            Account(
                id = "fake-cdi",
                institutionId = "xp",
                connectionId = "fake-connection-xp",
                name = "CDB 110% CDI",
                type = AccountType.INVESTMENT,
            ),
            Account(
                id = "fake-cash",
                institutionId = "cash",
                connectionId = "fake-connection-cash",
                name = "Carteira",
                type = AccountType.CASH,
            ),
        )

        val currentBalanceCents = mapOf(
            "fake-checking" to 824_750L,
            "fake-savings" to 320_000L,
            "fake-credit-card" to -45_000L,
            "fake-cdi" to 2_500_000L,
            "fake-cash" to 18_500L,
        )
    }
}
