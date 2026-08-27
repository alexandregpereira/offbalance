package br.alexandregpereira.offbalance.state

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface ActionHandler<Action : Any> {

    val action: SharedFlow<Action>
}

interface MutableActionHandler<Action : Any> : ActionHandler<Action> {

    fun sendAction(action: Action)
}

fun <Action : Any> MutableActionHandler(): MutableActionHandler<Action> = MutableActionHandlerImpl()

private class MutableActionHandlerImpl<Action : Any> : MutableActionHandler<Action> {

    private val _action = MutableSharedFlow<Action>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val action: SharedFlow<Action> = _action.asSharedFlow()

    override fun sendAction(action: Action) {
        _action.tryEmit(action)
    }
}
