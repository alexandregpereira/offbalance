package br.alexandregpereira.offbalance.balance.repository

import br.alexandregpereira.offbalance.balance.model.Account
import br.alexandregpereira.offbalance.balance.model.AccountBalance
import br.alexandregpereira.offbalance.balance.model.Institution
import kotlinx.datetime.Instant

interface InstitutionRepository {

    suspend fun getInstitutions(): List<Institution>

    suspend fun saveInstitutions(institutions: List<Institution>)
}

interface AccountRepository {

    suspend fun getAccounts(): List<Account>

    suspend fun saveAccounts(accounts: List<Account>)
}

interface BalanceRepository {

    /**
     * The most recent balance of every account.
     */
    suspend fun getLatestBalances(): List<AccountBalance>

    suspend fun getBalances(from: Instant, to: Instant): List<AccountBalance>

    suspend fun saveBalances(balances: List<AccountBalance>)
}
