package br.alexandregpereira.offbalance.provider.event

import br.alexandregpereira.offbalance.event.EventManager

sealed interface SyncEvent {

    /**
     * The local database has new data. Every screen showing balances should reload.
     */
    data object DataChanged : SyncEvent
}

interface SyncEventManager : EventManager<SyncEvent>

fun SyncEventManager(): SyncEventManager = DefaultSyncEventManager()

private class DefaultSyncEventManager :
    SyncEventManager,
    EventManager<SyncEvent> by EventManager()
