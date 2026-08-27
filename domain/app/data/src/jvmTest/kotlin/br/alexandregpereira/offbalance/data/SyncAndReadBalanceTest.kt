package br.alexandregpereira.offbalance.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import br.alexandregpereira.offbalance.balance.usecase.GetAccountsWithBalance
import br.alexandregpereira.offbalance.balance.usecase.GetNetWorth
import br.alexandregpereira.offbalance.balance.usecase.GetNetWorthHistory
import br.alexandregpereira.offbalance.data.di.dataModules
import br.alexandregpereira.offbalance.database.OffbalanceDatabase
import br.alexandregpereira.offbalance.provider.usecase.SyncAccounts
import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

/**
 * Exercises the whole offline path: the fake provider writes into SQLDelight and the read use
 * cases build the dashboard and the history out of the database.
 */
class SyncAndReadBalanceTest {

    private val koin = startKoin {
        modules(dataModules(databaseName = "unused-in-memory"))
        modules(
            module {
                single<SqlDriver>(createdAtStart = true) {
                    JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also {
                        OffbalanceDatabase.Schema.create(it)
                    }
                }
                single<Settings> { MapSettings() }
            }
        )
    }.koin

    @AfterTest
    fun tearDown() = stopKoin()

    @Test
    fun `syncs the fake provider and reads the balances back from the database`() = runTest {
        koin.get<SyncAccounts>().invoke()

        val accounts = koin.get<GetAccountsWithBalance>().invoke()
        assertEquals(5, accounts.size)
        assertTrue(accounts.any { it.account.name == "CDB 110% CDI" })
        assertTrue(accounts.single { it.account.name == "Cartão de Crédito" }.balance.cents < 0)

        val netWorth = koin.get<GetNetWorth>().invoke()
        assertEquals(
            expected = accounts.sumOf { it.balance.cents },
            actual = netWorth.cents,
        )

        val history = koin.get<GetNetWorthHistory>().invoke()
        assertEquals(12, history.size)
        assertEquals(netWorth.cents, history.last().total.cents)
        assertTrue(
            history.first().total.cents < history.last().total.cents,
            "The synthetic history should grow over time",
        )
    }
}
