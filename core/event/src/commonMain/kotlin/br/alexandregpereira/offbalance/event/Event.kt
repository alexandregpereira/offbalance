package br.alexandregpereira.offbalance.event

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface EventDispatcher<Event> {

    fun dispatchEvent(event: Event)
}

interface EventListener<Event> {

    val events: Flow<Event>
}

interface EventManager<Event> : EventDispatcher<Event>, EventListener<Event>

/**
 * @param replay how many past events a new subscriber receives. Use 1 when a late subscriber
 * still needs to know that something already happened.
 */
fun <Event> EventManager(replay: Int = 0): EventManager<Event> = DefaultEventManager(replay)

private class DefaultEventManager<Event>(replay: Int) : EventManager<Event> {

    private val _events: MutableSharedFlow<Event> = MutableSharedFlow(
        replay = replay,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val events: Flow<Event> = _events

    override fun dispatchEvent(event: Event) {
        _events.tryEmit(event)
    }
}
