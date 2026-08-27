package br.alexandregpereira.offbalance.balance.data.di

import br.alexandregpereira.offbalance.balance.data.DefaultAccountRepository
import br.alexandregpereira.offbalance.balance.data.DefaultBalanceRepository
import br.alexandregpereira.offbalance.balance.data.DefaultInstitutionRepository
import br.alexandregpereira.offbalance.balance.data.usecase.DefaultGetAccountsWithBalance
import br.alexandregpereira.offbalance.balance.data.usecase.DefaultGetNetWorth
import br.alexandregpereira.offbalance.balance.data.usecase.DefaultGetNetWorthHistory
import br.alexandregpereira.offbalance.balance.repository.AccountRepository
import br.alexandregpereira.offbalance.balance.repository.BalanceRepository
import br.alexandregpereira.offbalance.balance.repository.InstitutionRepository
import br.alexandregpereira.offbalance.balance.usecase.GetAccountsWithBalance
import br.alexandregpereira.offbalance.balance.usecase.GetNetWorth
import br.alexandregpereira.offbalance.balance.usecase.GetNetWorthHistory
import org.koin.dsl.module

val balanceDataModule = module {
    factory<InstitutionRepository> { DefaultInstitutionRepository(get()) }
    factory<AccountRepository> { DefaultAccountRepository(get()) }
    factory<BalanceRepository> { DefaultBalanceRepository(get()) }

    factory<GetNetWorth> { DefaultGetNetWorth(get()) }
    factory<GetAccountsWithBalance> {
        DefaultGetAccountsWithBalance(get(), get(), get())
    }
    factory<GetNetWorthHistory> { DefaultGetNetWorthHistory(get()) }
}
