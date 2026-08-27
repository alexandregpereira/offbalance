package br.alexandregpereira.offbalance.provider.event

import br.alexandregpereira.offbalance.event.EventManager

sealed interface SyncEvent {

    /**
     * The local database has new data. Every screen showing balances should reload.
     */
    data object DataChanged : SyncEvent
}

interface SyncEventManager : EventManager<SyncEvent>

/**
 * Replays the last event so a screen that subscribes after the initial sync still reloads.
 */
fun SyncEventManager(): SyncEventManager = DefaultSyncEventManager()

private class DefaultSyncEventManager :
    SyncEventManager,
    EventManager<SyncEvent> by EventManager(replay = 1)
