package br.alexandregpereira.offbalance.provider.data.di

import br.alexandregpereira.offbalance.provider.FinanceProvider
import br.alexandregpereira.offbalance.provider.FinanceProviderRegistry
import br.alexandregpereira.offbalance.provider.data.DefaultFinanceProviderRegistry
import br.alexandregpereira.offbalance.provider.data.fake.FakeFinanceProvider
import br.alexandregpereira.offbalance.provider.data.local.DefaultProviderConnectionRepository
import br.alexandregpereira.offbalance.provider.data.pluggy.PluggyConfig
import br.alexandregpereira.offbalance.provider.data.pluggy.PluggyFinanceProvider
import br.alexandregpereira.offbalance.provider.data.usecase.DefaultGetProviderConnections
import br.alexandregpereira.offbalance.provider.data.usecase.DefaultSyncAccounts
import br.alexandregpereira.offbalance.provider.event.SyncEventManager
import br.alexandregpereira.offbalance.provider.repository.ProviderConnectionRepository
import br.alexandregpereira.offbalance.provider.usecase.GetProviderConnections
import br.alexandregpereira.offbalance.provider.usecase.SyncAccounts
import org.koin.core.qualifier.named
import org.koin.dsl.module

val FakeProviderQualifier = named("FakeFinanceProvider")
val PluggyProviderQualifier = named("PluggyFinanceProvider")

fun providerDataModule(pluggyConfig: PluggyConfig = PluggyConfig()) = module {
    single { pluggyConfig }
    single<FinanceProvider>(FakeProviderQualifier) { FakeFinanceProvider() }
    single<FinanceProvider>(PluggyProviderQualifier) { PluggyFinanceProvider(get(), get()) }
    single<FinanceProviderRegistry> {
        DefaultFinanceProviderRegistry(
            providers = listOf(
                get(FakeProviderQualifier),
                get(PluggyProviderQualifier),
            )
        )
    }
    single<SyncEventManager> { SyncEventManager() }
    factory<ProviderConnectionRepository> { DefaultProviderConnectionRepository(get()) }

    factory<SyncAccounts> {
        DefaultSyncAccounts(get(), get(), get(), get(), get(), get())
    }
    factory<GetProviderConnections> { DefaultGetProviderConnections(get()) }
}
