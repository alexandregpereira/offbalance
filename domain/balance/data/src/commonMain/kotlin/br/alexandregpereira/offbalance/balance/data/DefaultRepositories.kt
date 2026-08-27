package br.alexandregpereira.offbalance.balance.data

import br.alexandregpereira.offbalance.balance.data.local.dao.AccountDao
import br.alexandregpereira.offbalance.balance.data.local.dao.BalanceDao
import br.alexandregpereira.offbalance.balance.data.local.dao.InstitutionDao
import br.alexandregpereira.offbalance.balance.data.local.mapper.toDomain
import br.alexandregpereira.offbalance.balance.data.local.mapper.toEntity
import br.alexandregpereira.offbalance.balance.model.Account
import br.alexandregpereira.offbalance.balance.model.AccountBalance
import br.alexandregpereira.offbalance.balance.model.Institution
import br.alexandregpereira.offbalance.balance.repository.AccountRepository
import br.alexandregpereira.offbalance.balance.repository.BalanceRepository
import br.alexandregpereira.offbalance.balance.repository.InstitutionRepository
import br.alexandregpereira.offbalance.ktx.getDispatcherIO
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant

internal class DefaultInstitutionRepository(
    private val institutionDao: InstitutionDao,
) : InstitutionRepository {

    override suspend fun getInstitutions(): List<Institution> = withContext(getDispatcherIO()) {
        institutionDao.getAll().map { it.toDomain() }
    }

    override suspend fun saveInstitutions(institutions: List<Institution>) =
        withContext(getDispatcherIO()) {
            institutionDao.insert(institutions.map { it.toEntity() })
        }
}

internal class DefaultAccountRepository(
    private val accountDao: AccountDao,
) : AccountRepository {

    override suspend fun getAccounts(): List<Account> = withContext(getDispatcherIO()) {
        accountDao.getAll().map { it.toDomain() }
    }

    override suspend fun saveAccounts(accounts: List<Account>) = withContext(getDispatcherIO()) {
        accountDao.insert(accounts.map { it.toEntity() })
    }
}

internal class DefaultBalanceRepository(
    private val balanceDao: BalanceDao,
) : BalanceRepository {

    override suspend fun getLatestBalances(): List<AccountBalance> = withContext(getDispatcherIO()) {
        balanceDao.getLatestByAccount().map { it.toDomain() }
    }

    override suspend fun getBalances(from: Instant, to: Instant): List<AccountBalance> =
        withContext(getDispatcherIO()) {
            balanceDao.getInRange(
                fromEpochMillis = from.coerceAtLeast(Instant.fromEpochMilliseconds(0))
                    .toEpochMilliseconds(),
                toEpochMillis = to.toEpochMilliseconds(),
            ).map { it.toDomain() }
        }

    override suspend fun saveBalances(balances: List<AccountBalance>) =
        withContext(getDispatcherIO()) {
            balanceDao.insert(balances.map { it.toEntity() })
        }
}
