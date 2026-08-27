package br.alexandregpereira.offbalance.balance.usecase

import br.alexandregpereira.offbalance.balance.model.AccountWithBalance

/**
 * Every account with its institution and its latest balance attached.
 */
fun interface GetAccountsWithBalance {

    suspend operator fun invoke(): List<AccountWithBalance>
}
