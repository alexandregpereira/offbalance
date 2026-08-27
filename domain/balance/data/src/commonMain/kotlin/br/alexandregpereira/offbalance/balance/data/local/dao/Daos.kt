package br.alexandregpereira.offbalance.balance.data.local.dao

import br.alexandregpereira.offbalance.balance.data.local.entity.AccountEntity
import br.alexandregpereira.offbalance.balance.data.local.entity.BalanceEntryEntity
import br.alexandregpereira.offbalance.balance.data.local.entity.InstitutionEntity

/**
 * The implementations live in the `:domain:app:data` module, which owns the single generated
 * database, so this module never sees the persistence framework.
 */
interface InstitutionDao {

    suspend fun getAll(): List<InstitutionEntity>

    suspend fun insert(institutions: List<InstitutionEntity>)
}

interface AccountDao {

    suspend fun getAll(): List<AccountEntity>

    suspend fun insert(accounts: List<AccountEntity>)
}

interface BalanceDao {

    suspend fun getLatestByAccount(): List<BalanceEntryEntity>

    suspend fun getInRange(fromEpochMillis: Long, toEpochMillis: Long): List<BalanceEntryEntity>

    suspend fun insert(balances: List<BalanceEntryEntity>)
}
