package br.alexandregpereira.offbalance.feature.history

import br.alexandregpereira.offbalance.balance.usecase.GetNetWorthHistory
import br.alexandregpereira.offbalance.provider.event.SyncEvent
import br.alexandregpereira.offbalance.provider.event.SyncEventManager
import br.alexandregpereira.offbalance.state.UiModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HistoryStateHolder internal constructor(
    private val getNetWorthHistory: GetNetWorthHistory,
    syncEventManager: SyncEventManager,
) : UiModel<HistoryState>(HistoryState()), HistoryIntent {

    init {
        syncEventManager.events
            .onEach { if (it is SyncEvent.DataChanged) load() }
            .launchIn(scope)
        load()
    }

    override fun onRetryClick() = load()

    private fun load() {
        scope.launch {
            setState { loading() }
            runCatching { getNetWorthHistory() }
                .onSuccess { snapshots -> setState { content(snapshots) } }
                .onFailure { throwable ->
                    setState {
                        failure(throwable.message ?: "Não foi possível carregar o histórico")
                    }
                }
        }
    }
}
