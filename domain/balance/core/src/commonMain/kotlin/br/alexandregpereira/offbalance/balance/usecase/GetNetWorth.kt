package br.alexandregpereira.offbalance.balance.usecase

import br.alexandregpereira.offbalance.money.Money

/**
 * The sum of the latest known balance of every account.
 */
fun interface GetNetWorth {

    suspend operator fun invoke(): Money
}
