package br.alexandregpereira.offbalance.balance.data.usecase

import br.alexandregpereira.offbalance.balance.repository.BalanceRepository
import br.alexandregpereira.offbalance.balance.usecase.GetNetWorth
import br.alexandregpereira.offbalance.money.Money
import br.alexandregpereira.offbalance.money.sum

internal class DefaultGetNetWorth(
    private val balanceRepository: BalanceRepository,
) : GetNetWorth {

    override suspend fun invoke(): Money = balanceRepository.getLatestBalances()
        .map { it.amount }
        .filter { it.currency == CURRENCY }
        .sum(CURRENCY)

    private companion object {
        const val CURRENCY = Money.BRL
    }
}
