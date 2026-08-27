package br.alexandregpereira.offbalance.balance.usecase

import br.alexandregpereira.offbalance.balance.model.NetWorthSnapshot

/**
 * One net worth snapshot per month. A month snapshot carries forward the latest known balance of
 * every account up to the end of that month, so accounts that were not synced in a given month
 * still count towards the total.
 */
fun interface GetNetWorthHistory {

    suspend operator fun invoke(): List<NetWorthSnapshot>
}
