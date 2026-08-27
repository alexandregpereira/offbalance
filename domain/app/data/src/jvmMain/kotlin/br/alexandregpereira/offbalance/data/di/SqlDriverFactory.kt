package br.alexandregpereira.offbalance.data.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import br.alexandregpereira.offbalance.database.OffbalanceDatabase
import java.io.File
import org.koin.core.scope.Scope

internal actual fun Scope.createSqlDriver(databaseName: String): SqlDriver {
    val databaseFile = File(System.getProperty("user.home"), ".offbalance/$databaseName")
        .also { it.parentFile.mkdirs() }
    val alreadyExists = databaseFile.exists()

    return JdbcSqliteDriver("jdbc:sqlite:${databaseFile.absolutePath}").also { driver ->
        if (!alreadyExists) OffbalanceDatabase.Schema.create(driver)
    }
}
