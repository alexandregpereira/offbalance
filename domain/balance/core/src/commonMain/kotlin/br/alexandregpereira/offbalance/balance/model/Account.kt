package br.alexandregpereira.offbalance.balance.model

import br.alexandregpereira.offbalance.money.Money

enum class AccountType {
    CHECKING,
    SAVINGS,
    CREDIT_CARD,
    INVESTMENT,
    CASH,
}

data class Institution(
    val id: String,
    val name: String,
    val logoUrl: String? = null,
)

data class Account(
    val id: String,
    val institutionId: String,
    val connectionId: String,
    val name: String,
    val type: AccountType,
    val currency: String = Money.BRL,
)

data class AccountWithBalance(
    val account: Account,
    val institution: Institution,
    val balance: Money,
)
