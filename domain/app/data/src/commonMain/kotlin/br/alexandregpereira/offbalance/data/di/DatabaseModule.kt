package br.alexandregpereira.offbalance.data.di

import app.cash.sqldelight.db.SqlDriver
import br.alexandregpereira.offbalance.balance.data.local.dao.AccountDao
import br.alexandregpereira.offbalance.balance.data.local.dao.BalanceDao
import br.alexandregpereira.offbalance.balance.data.local.dao.InstitutionDao
import br.alexandregpereira.offbalance.data.database.dao.AccountDaoImpl
import br.alexandregpereira.offbalance.data.database.dao.BalanceDaoImpl
import br.alexandregpereira.offbalance.data.database.dao.InstitutionDaoImpl
import br.alexandregpereira.offbalance.data.database.dao.ProviderConnectionDaoImpl
import br.alexandregpereira.offbalance.database.OffbalanceDatabase
import br.alexandregpereira.offbalance.provider.data.local.ProviderConnectionDao
import org.koin.dsl.module

fun databaseModule(databaseName: String) = module {
    single<SqlDriver> { createSqlDriver(databaseName) }
    single { OffbalanceDatabase(driver = get()) }
    factory<InstitutionDao> { InstitutionDaoImpl(get()) }
    factory<AccountDao> { AccountDaoImpl(get()) }
    factory<BalanceDao> { BalanceDaoImpl(get()) }
    factory<ProviderConnectionDao> { ProviderConnectionDaoImpl(get()) }
}
