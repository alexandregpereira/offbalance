package br.alexandregpereira.offbalance

import br.alexandregpereira.offbalance.provider.event.SyncEvent
import br.alexandregpereira.offbalance.provider.event.SyncEventManager
import br.alexandregpereira.offbalance.provider.usecase.GetProviderConnections
import br.alexandregpereira.offbalance.provider.usecase.SyncAccounts
import br.alexandregpereira.offbalance.state.StateHolder
import kotlinx.coroutines.launch

interface MainIntent {

    fun onTabClick(tab: AppTab)
}

class MainStateHolder internal constructor(
    private val getProviderConnections: GetProviderConnections,
    private val syncAccounts: SyncAccounts,
    private val syncEventManager: SyncEventManager,
) : StateHolder<MainState>(MainState()), MainIntent {

    init {
        syncOnFirstLaunch()
    }

    override fun onTabClick(tab: AppTab) {
        setState { copy(selectedTab = tab) }
    }

    /**
     * The app reads only from the local database, so an empty database means there is nothing to
     * show. Pull once from the selected provider to seed it.
     */
    private fun syncOnFirstLaunch() {
        scope.launch {
            runCatching {
                if (getProviderConnections().isEmpty()) {
                    syncAccounts()
                    syncEventManager.dispatchEvent(SyncEvent.DataChanged)
                }
            }
        }
    }
}
