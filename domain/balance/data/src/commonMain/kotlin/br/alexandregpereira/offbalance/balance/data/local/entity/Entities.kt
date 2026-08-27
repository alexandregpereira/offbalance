package br.alexandregpereira.offbalance.balance.data.local.entity

data class InstitutionEntity(
    val id: String,
    val name: String,
    val logoUrl: String?,
)

data class AccountEntity(
    val id: String,
    val institutionId: String,
    val connectionId: String,
    val name: String,
    val type: String,
    val currency: String,
)

data class BalanceEntryEntity(
    val accountId: String,
    val amountCents: Long,
    val currency: String,
    val timestampEpochMillis: Long,
)
