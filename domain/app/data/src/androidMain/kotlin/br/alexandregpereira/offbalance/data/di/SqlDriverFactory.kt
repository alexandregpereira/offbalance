package br.alexandregpereira.offbalance.data.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import br.alexandregpereira.offbalance.database.OffbalanceDatabase
import org.koin.core.scope.Scope

internal actual fun Scope.createSqlDriver(databaseName: String): SqlDriver = AndroidSqliteDriver(
    schema = OffbalanceDatabase.Schema,
    context = get<Context>(),
    name = databaseName,
)
