package br.alexandregpereira.offbalance.feature.history.di

import br.alexandregpereira.offbalance.feature.history.HistoryStateHolder
import org.koin.dsl.module

val historyModule = module {
    factory {
        HistoryStateHolder(
            getNetWorthHistory = get(),
            syncEventManager = get(),
        )
    }
}
