package br.alexandregpereira.offbalance.feature.dashboard

import br.alexandregpereira.offbalance.balance.usecase.GetAccountsWithBalance
import br.alexandregpereira.offbalance.balance.usecase.GetNetWorth
import br.alexandregpereira.offbalance.provider.event.SyncEvent
import br.alexandregpereira.offbalance.provider.event.SyncEventManager
import br.alexandregpereira.offbalance.provider.usecase.SyncAccounts
import br.alexandregpereira.offbalance.state.StateHolder
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DashboardStateHolder internal constructor(
    private val getNetWorth: GetNetWorth,
    private val getAccountsWithBalance: GetAccountsWithBalance,
    private val syncAccounts: SyncAccounts,
    private val syncEventManager: SyncEventManager,
) : StateHolder<DashboardState>(DashboardState()), DashboardIntent {

    init {
        syncEventManager.events
            .onEach { if (it is SyncEvent.DataChanged) load() }
            .launchIn(scope)
        load()
    }

    override fun onSyncClick() {
        if (state.value.isSyncing) return
        scope.launch {
            setState { syncing(isSyncing = true) }
            runCatching { syncAccounts() }
                .onFailure { setState { failure(it.messageOrDefault()) } }
            setState { syncing(isSyncing = false) }
            syncEventManager.dispatchEvent(SyncEvent.DataChanged)
        }
    }

    private fun load() {
        scope.launch {
            setState { loading() }
            runCatching { getNetWorth() to getAccountsWithBalance() }
                .onSuccess { (netWorth, accounts) ->
                    setState { content(netWorth = netWorth, accounts = accounts) }
                }
                .onFailure { setState { failure(it.messageOrDefault()) } }
        }
    }
}

private fun Throwable.messageOrDefault(): String =
    message ?: "Não foi possível carregar os saldos"
