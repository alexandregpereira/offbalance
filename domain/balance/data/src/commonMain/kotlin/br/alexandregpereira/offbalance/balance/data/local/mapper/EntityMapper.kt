package br.alexandregpereira.offbalance.balance.data.local.mapper

import br.alexandregpereira.offbalance.balance.data.local.entity.AccountEntity
import br.alexandregpereira.offbalance.balance.data.local.entity.BalanceEntryEntity
import br.alexandregpereira.offbalance.balance.data.local.entity.InstitutionEntity
import br.alexandregpereira.offbalance.balance.model.Account
import br.alexandregpereira.offbalance.balance.model.AccountBalance
import br.alexandregpereira.offbalance.balance.model.AccountType
import br.alexandregpereira.offbalance.balance.model.Institution
import br.alexandregpereira.offbalance.money.Money
import kotlinx.datetime.Instant

internal fun InstitutionEntity.toDomain(): Institution = Institution(
    id = id,
    name = name,
    logoUrl = logoUrl,
)

internal fun Institution.toEntity(): InstitutionEntity = InstitutionEntity(
    id = id,
    name = name,
    logoUrl = logoUrl,
)

internal fun AccountEntity.toDomain(): Account = Account(
    id = id,
    institutionId = institutionId,
    connectionId = connectionId,
    name = name,
    type = AccountType.entries.firstOrNull { it.name == type } ?: AccountType.CHECKING,
    currency = currency,
)

internal fun Account.toEntity(): AccountEntity = AccountEntity(
    id = id,
    institutionId = institutionId,
    connectionId = connectionId,
    name = name,
    type = type.name,
    currency = currency,
)

internal fun BalanceEntryEntity.toDomain(): AccountBalance = AccountBalance(
    accountId = accountId,
    amount = Money(cents = amountCents, currency = currency),
    date = Instant.fromEpochMilliseconds(timestampEpochMillis),
)

internal fun AccountBalance.toEntity(): BalanceEntryEntity = BalanceEntryEntity(
    accountId = accountId,
    amountCents = amount.cents,
    currency = amount.currency,
    timestampEpochMillis = date.toEpochMilliseconds(),
)
