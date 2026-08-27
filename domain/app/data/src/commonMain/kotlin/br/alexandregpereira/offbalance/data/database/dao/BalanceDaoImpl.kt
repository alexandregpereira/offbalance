package br.alexandregpereira.offbalance.data.database.dao

import br.alexandregpereira.offbalance.balance.data.local.dao.AccountDao
import br.alexandregpereira.offbalance.balance.data.local.dao.BalanceDao
import br.alexandregpereira.offbalance.balance.data.local.dao.InstitutionDao
import br.alexandregpereira.offbalance.balance.data.local.entity.AccountEntity
import br.alexandregpereira.offbalance.balance.data.local.entity.BalanceEntryEntity
import br.alexandregpereira.offbalance.balance.data.local.entity.InstitutionEntity
import br.alexandregpereira.offbalance.database.OffbalanceDatabase

internal class InstitutionDaoImpl(
    private val database: OffbalanceDatabase,
) : InstitutionDao {

    override suspend fun getAll(): List<InstitutionEntity> =
        database.institutionQueries.selectAll { id, name, logoUrl ->
            InstitutionEntity(id = id, name = name, logoUrl = logoUrl)
        }.executeAsList()

    override suspend fun insert(institutions: List<InstitutionEntity>) {
        database.institutionQueries.transaction {
            institutions.forEach { database.institutionQueries.insert(it.id, it.name, it.logoUrl) }
        }
    }
}

internal class AccountDaoImpl(
    private val database: OffbalanceDatabase,
) : AccountDao {

    override suspend fun getAll(): List<AccountEntity> =
        database.accountQueries.selectAll { id, institutionId, connectionId, name, type, currency ->
            AccountEntity(
                id = id,
                institutionId = institutionId,
                connectionId = connectionId,
                name = name,
                type = type,
                currency = currency,
            )
        }.executeAsList()

    override suspend fun insert(accounts: List<AccountEntity>) {
        database.accountQueries.transaction {
            accounts.forEach {
                database.accountQueries.insert(
                    id = it.id,
                    institutionId = it.institutionId,
                    connectionId = it.connectionId,
                    name = it.name,
                    type = it.type,
                    currency = it.currency,
                )
            }
        }
    }
}

internal class BalanceDaoImpl(
    private val database: OffbalanceDatabase,
) : BalanceDao {

    override suspend fun getLatestByAccount(): List<BalanceEntryEntity> =
        database.balanceEntryQueries.selectLatestByAccount { accountId, amountCents, currency, timestamp ->
            BalanceEntryEntity(
                accountId = accountId,
                amountCents = amountCents,
                currency = currency,
                timestampEpochMillis = timestamp ?: 0L,
            )
        }.executeAsList()

    override suspend fun getInRange(
        fromEpochMillis: Long,
        toEpochMillis: Long,
    ): List<BalanceEntryEntity> = database.balanceEntryQueries.selectInRange(
        timestampEpochMillis = fromEpochMillis,
        timestampEpochMillis_ = toEpochMillis,
    ) { accountId, amountCents, currency, timestamp ->
        BalanceEntryEntity(
            accountId = accountId,
            amountCents = amountCents,
            currency = currency,
            timestampEpochMillis = timestamp,
        )
    }.executeAsList()

    override suspend fun insert(balances: List<BalanceEntryEntity>) {
        database.balanceEntryQueries.transaction {
            balances.forEach {
                database.balanceEntryQueries.insert(
                    accountId = it.accountId,
                    amountCents = it.amountCents,
                    currency = it.currency,
                    timestampEpochMillis = it.timestampEpochMillis,
                )
            }
        }
    }
}
