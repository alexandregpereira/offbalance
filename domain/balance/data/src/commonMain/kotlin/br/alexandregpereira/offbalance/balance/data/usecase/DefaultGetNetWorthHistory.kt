package br.alexandregpereira.offbalance.balance.data.usecase

import br.alexandregpereira.offbalance.balance.model.AccountBalance
import br.alexandregpereira.offbalance.balance.model.NetWorthSnapshot
import br.alexandregpereira.offbalance.balance.repository.BalanceRepository
import br.alexandregpereira.offbalance.balance.usecase.GetNetWorthHistory
import br.alexandregpereira.offbalance.money.Money
import br.alexandregpereira.offbalance.money.sum
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

internal class DefaultGetNetWorthHistory(
    private val balanceRepository: BalanceRepository,
    private val clock: Clock = Clock.System,
) : GetNetWorthHistory {

    override suspend fun invoke(): List<NetWorthSnapshot> {
        val timeZone = TimeZone.currentSystemDefault()
        val now = clock.now()
        val today = now.toLocalDateTime(timeZone).date

        val balances = balanceRepository.getBalances(from = Instant.DISTANT_PAST, to = now)
            .filter { it.amount.currency == CURRENCY }
        if (balances.isEmpty()) return emptyList()

        val firstMonth = today.minus(MONTH_COUNT - 1, DateTimeUnit.MONTH).startOfMonth()

        return (0 until MONTH_COUNT).map { monthOffset ->
            val monthStart = firstMonth.plus(monthOffset, DateTimeUnit.MONTH)
            val nextMonthStart = monthStart.plus(1, DateTimeUnit.MONTH)
            val snapshotDate = minOf(nextMonthStart.minus(1, DateTimeUnit.DAY), today)
            val cutoff = minOf(nextMonthStart, today.plus(1, DateTimeUnit.DAY))
                .atStartOfDayIn(timeZone)

            NetWorthSnapshot(
                date = snapshotDate,
                total = balances.latestBefore(cutoff).map { it.amount }.sum(CURRENCY),
            )
        }
    }

    private fun List<AccountBalance>.latestBefore(cutoff: Instant): List<AccountBalance> =
        filter { it.date < cutoff }
            .groupBy { it.accountId }
            .mapNotNull { (_, accountBalances) -> accountBalances.maxByOrNull { it.date } }

    private fun LocalDate.startOfMonth(): LocalDate = LocalDate(year, month, 1)

    private companion object {
        const val MONTH_COUNT = 12
        const val CURRENCY = Money.BRL
    }
}
