package br.alexandregpereira.offbalance.balance.model

import br.alexandregpereira.offbalance.money.Money
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

data class AccountBalance(
    val accountId: String,
    val amount: Money,
    val date: Instant,
)

/**
 * Total net worth at a given date, which is the sum of the latest balance of every account
 * up to that date.
 */
data class NetWorthSnapshot(
    val date: LocalDate,
    val total: Money,
)
