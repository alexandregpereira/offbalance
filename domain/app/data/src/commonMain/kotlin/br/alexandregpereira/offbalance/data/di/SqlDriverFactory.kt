package br.alexandregpereira.offbalance.data.di

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

internal expect fun Scope.createSqlDriver(databaseName: String): SqlDriver
